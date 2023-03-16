package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import models.articles
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text


@Composable
private fun Cover(src: String?, isMouseOver: MutableState<Boolean>) {
    Div(
        attrs = {
            style {

                display(DisplayStyle.Grid)
                justifyContent(JustifyContent.SpaceBetween)
                alignItems(AlignItems.Center)
                flex(1)
            }
        }
    ) {
        Img(
            src = src ?: "",
            attrs = {
                style {
                    gridArea("1 / 1")
                    borderRadius(20.px)
                    maxWidth(100.percent)
                    cursor("pointer")
                    property("transition", "0.3s")
                }
            }
        )


        if (true)
            Div(attrs = {

                style {
                    gridArea("1/1")
                }
            }) {
                ClickAnimation()
            }
    }
}


@Composable
fun Home(context: Router.Context)  {


    val article = articles[0]

    val isMouseOver = remember { mutableStateOf<Boolean>(false) }

    Navbar(context)
    //Header(article)
    SearchView()
    Div(attrs = {
        addEventListener("mouseleave") { isMouseOver.value = false }
        addEventListener("mouseover") { isMouseOver.value = true }
        addEventListener("click") {
            context.navigate("/articles/${article.path}")
        }

        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            padding(10.px)
            marginTop(30.px)
        }
    }) {
        Cover(articles[0].cover, isMouseOver)
        Div(attrs = {
            style {
                flex(1)
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Start)
                justifyContent(JustifyContent.Start)
                flexDirection(FlexDirection.Column)
                paddingLeft(30.px)

            }
        }) {
            val article = articles[0]
            Span({
                style {
                    display(DisplayStyle.Block)
                    fontFamily("Sofia Sans Semi Condensed")
                    fontWeight("bold")
                    article.category?.color?.let {
                        property("color", it)
                    }
                }
            }) {
                Text(article.category?.name ?: "")
            }

            Span({
                style {
                    display(DisplayStyle.Block)
                    fontSize(45.px)
                    fontWeight("bold")
                    fontFamily("Sofia Sans Semi Condensed")

                }
            }) {
                Text(article.name ?: "")
            }


            Span({
                style {
                    display(DisplayStyle.Block)
                    fontSize(20.px)
                    fontWeight("bold")
                    property("color", "#8a8798")
                    fontFamily("Sofia Sans Semi Condensed")

                }
            }) {
                Text(article.description ?: "")
            }

            Div({
                style {
                    display(DisplayStyle.Flex)
                    marginTop(10.px)
                    gap(32.px)
                    justifyContent(JustifyContent.FlexEnd)
                }
            }) {

                Img(article.author?.avatar ?: "", attrs = {
                    style {
                        borderRadius(50.percent)
                        gap(32.px)
                        width(50.px)
                        height(50.px)
                    }
                }
                )

                Div({
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Start)
                        justifyContent(JustifyContent.Start)
                        flexDirection(FlexDirection.Column)
                    }
                }) {

                    simpleText(article.author?.fullName, isBold = true)
                    simpleText(
                        article.createdAt?.toLocaleDateString(
                            "fr-FR",
                            dateLocaleOptions { year = "numeric"; month = "long"; day = "numeric" })
                    )
                }
            }
        }
    }

    Cards(articles) { article ->
        article.path?.let { context.navigate("/articles/${it}") }
    }

    Footer()
}