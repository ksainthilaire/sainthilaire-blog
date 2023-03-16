package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import models.articles
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.TagElement
import org.w3c.dom.Element


@Composable
fun Loader() = TagElement<Element>("lottie-player", {
    attr("src", "https://assets3.lottiefiles.com/packages/lf20_mrx08zqq.json")
    attr("background", "transparent")
    attr("speed", "0.5")

    attr("loop", "")
    attr("autoplay", "")

    style {
        overflow("visible")
        maxWidth(100.percent)
        height(300.px)
        marginTop(200.px)
        alignSelf(AlignSelf.Center)
        flexGrow(1)
    }
}) {

}

@Composable
fun Article(context: Router.Context) {

    val loader = remember { mutableStateOf(true) }



    Navbar(context)
    article(articles[0], loader)

    if (!loader.value) Footer()
}