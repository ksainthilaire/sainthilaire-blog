import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.*
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import models.articles
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.events.WheelEvent
import styles.AppStylesheet
import styles.AppStylesheet.default


typealias Component = @Composable () -> Unit

@Composable
fun Logo() = Div({
    style {
        fontSize(16.px)
        fontWeight("bold")
        letterSpacing(4.px)
    }
}) {


}


fun main() {


    window.addEventListener("wheel", { it ->
        val event = it as WheelEvent
        console.log("test", it.deltaY)
        event.preventDefault()
        window.document.getElementById("root")?.let {
            it.scrollLeft += event.deltaY
        }
    })


    val composition = renderComposable(rootElementId = "root") {
    }

    Router.composition = composition
    Router.register {
        it.path("/") { Home(it) }

        it.path("/search/:text") {
            val text = it.params["text"]
        }

        it.path("/tags") {
            Text("Tags")
        }

        it.path("/tags/:tag") {
            val tag = it.params["tag"]
            Text("Article name ${it.params["tag"]}")
        }

        it.path("/articles/:name") { Article(it) }
        it.path("/about") { About(it) }
    }
}

