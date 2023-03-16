import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.PopupRecruiter
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.*
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import styles.AppStylesheet

typealias Handler = @Composable (path: Router.Context) -> Unit

object Router {

    var composition: Composition? = null

    private val routes: HashMap<String, Handler> = HashMap()
    private var currentContext: Context? = null

    class Context(
        val path: String? = null,
        private val search: String? = null,
        val params: HashMap<String, String> = HashMap(),
        var previous: Context? = null,
        var next: Context? = null
    ) {


        fun navigate(path: String) {
            if (resolve(path)) {
                val newUrl = window.location.origin + path
                window.history.pushState(null, "", newUrl)
            }
        }

        fun resume() = this.navigate(this.search ?: "/")

    }

    private fun parseUrl(url: String): List<String> {
        return url.substring(1).split('/').filter { it.isNotEmpty() }
            .map { path -> path.replace("\\s".toRegex(), "") }
    }

    private fun getContext(search: String): Context? {
        val keys = routes.keys

        searchPath@ for (key in keys) {

            val scheme = parseUrl(key)
            val searchUrl = parseUrl(search)
            val params: HashMap<String, String> = HashMap()

            if (scheme.isEmpty() && searchUrl.size <= 1) return Context(search, search, params)

            if (scheme.size != searchUrl.size) continue@searchPath

            searchParams@ for (index in scheme.indices) {
                if (scheme[index][0] == ':') {

                    val paramName = scheme[index].substring(1)
                    val paramValue = searchUrl[index]
                    params[paramName] = paramValue

                    break@searchParams
                } else if (scheme[index] != searchUrl[index]) continue@searchPath
            }
            return Context(key, search, params)
        }
        return null

    }

    private fun resolve(path: String): Boolean {
        val context = getContext(path)
        context?.let {

            val handler = routes[context.path] ?: routes[PATH_NOT_FOUND]
            handler?.let {

                currentContext?.let {
                    context.previous = it
                    it.next = context
                }
                currentContext = context
                MainScope().launch {
                    window.scrollTo(0.0, 1000.0)
                    composition?.setContent {
                        Style(AppStylesheet)



                        val showPopupRecruiter = remember { mutableStateOf(!document.cookie.contains("ksainthi_popup_recruiter")) }
                        if (showPopupRecruiter.value) {
                            PopupRecruiter {
                                document.cookie = "ksainthi_popup_recruiter=ksainthi; max-age=" + 60 * 60 * 24 * 30;
                                showPopupRecruiter.value = false
                            }
                        }


                        return@setContent Div({ classes(AppStylesheet.Body) }) {
                            it(context)
                        }
                    }
                }
            }
            return true
        }
        return false
    }


    fun register(defaultPath: String? = null, registerAllRoutes: (router: Router) -> Unit) {
        registerAllRoutes(this)

        var innerDocumentClick = false

        window.document.onmouseover = {
            innerDocumentClick = true
            false
        }

        window.document.onmouseout = {
            innerDocumentClick = false
            false
        }

        window.addEventListener("popstate", {
            if (!innerDocumentClick) {
                val previous = currentContext?.previous
                previous?.let {
                    currentContext = previous.previous
                    previous.resume()
                } ?: kotlin.run { }
            }
        }, false)

        var path = window.location.hash
        path = if (path.isNullOrBlank()) defaultPath ?: PATH_DEFAULT else path

        resolve(path)
    }

    fun path(path: String, render: Handler) {
        routes[path] = {
            render(it)
        }
    }

    private const val PATH_DEFAULT = "/"
    private const val PATH_NOT_FOUND = "404"
}