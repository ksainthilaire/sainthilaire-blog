package components

import androidx.compose.runtime.*
import models.Article
import models.articles
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.Element


@Composable
fun Cards(articles: List<Article>, listener: (article: Article) -> Unit) {
    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            justifyContent(JustifyContent.Start)
            alignItems(AlignItems.Start)
            flexWrap(FlexWrap.Wrap)
            marginTop(30.px)
            padding(10.px)
            gap(30.px)
        }
    }) {
        articles.forEach {
            Card(it, listener)
        }
    }
}

@Composable
fun ClickAnimation() = TagElement<Element>("lottie-player", {
    attr("src", "https://assets7.lottiefiles.com/packages/lf20_rbufxdej.json")
    attr("background", "transparent")
    attr("speed", "1")

    attr("loop", "")
    attr("autoplay", "")

    style {
        overflow("visible")
        maxWidth(100.percent)
        height(300.px)
        alignSelf(AlignSelf.Center)
        flexGrow(1)
    }
}) {

}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun Card(article: Article, listener: (article: Article) -> Unit) = Div(attrs = {
    style {
        flex("0 0 33.333333%")
        cursor("pointer")
    }
}) {
    val isMouseOver = remember { mutableStateOf(false) }

    val image = Div(

        attrs = {
            addEventListener("mouseleave") { isMouseOver.value = false }
            addEventListener("mouseover") { isMouseOver.value = true }
            addEventListener("click") { listener(article) }
            style {
                display(DisplayStyle.Grid)
                alignItems(AlignItems.Start)
                justifyContent(JustifyContent.Start)
            }
        }
    ) {


        Img(
            src = article.cover ?: "",
            attrs = {
                style {
                    maxWidth(100.percent)
                    height(300.px)
                    borderRadius(20.px)
                    cursor("pointer")
                    gridArea("1 / 1")
                    justifySelf("center")
                    alignSelf("center")
                    property("transition", "0.3s")
                }
            }
        )

        Div(attrs = {
            style {
                justifySelf("center")
                alignSelf("center")
                gridArea("1 / 1")
            }
        }) {

            if (isMouseOver.value) {
                ClickAnimation()
            }
        }
    }




    image

    Div(attrs = {
        style {
            paddingRight(20.px)
            paddingLeft(20.px)
        }
    })
    {


        // Description
        Div(attrs = {
            style {
                flex(1)
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Start)
                justifyContent(JustifyContent.Start)
                flexDirection(FlexDirection.Column)
                marginTop(10.px)
                gap(10.px)
            }
        }) {
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
                    fontSize(25.px)
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
        }


        // Author
        Div({
            style {
                display(DisplayStyle.Flex)
                marginTop(10.px)
                gap(32.px)
                alignItems(AlignItems.Start)
                justifyContent(JustifyContent.Start)
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