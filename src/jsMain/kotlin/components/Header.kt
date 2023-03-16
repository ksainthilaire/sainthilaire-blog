package components

import Component
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.document
import kotlinx.browser.window
import models.Article
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import styles.AppStylesheet.style


@Composable

fun cover(src: String?) {
    val isHover = remember { mutableStateOf(false) }

    Div(
        attrs = {

            style {
                marginTop((-30).px)
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.SpaceBetween)
                alignItems(AlignItems.Center)
            }
        }
    ) {
        Img(
            src = src ?: "",
            attrs = {

                addEventListener("mouseleave") {
                    console.log("isHover", isHover)
                    isHover.value = false
                }


                addEventListener("mouseover") {
                    console.log("isHover", isHover)
                    isHover.value = true
                }

                style {

                    borderRadius(20.px)
                    cursor("pointer")
                    property("transition", "0.3s")
                    maxWidth(720.px)
                    property("margin-right", "auto")
                    property("margin-left", "auto")
                }
            }
        )
    }
}


@Composable
private fun roundButton(color: String, callback: (() -> Unit), content: Component) = A(
    attrs = {
        onClick {
            callback()
        }
        style {
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)
            position(Position.Relative)
            width(40.px)
            height(40.px)
            borderRadius(50.percent)
            cursor("pointer")
            textDecoration("none")
            property("transition", "all 0.15s ease")
            property("color", "white")
            property("background-color", color)
        }
    }) {
    content()
}

@Composable
fun Header(article: Article) = Div({
    style {
        minHeight(220.px)
        property("background-color", "#092856")
        property("color", "white")
    }
}) {


    Div({

        style {
            paddingTop(30.px)
            maxWidth(720.px)
            property("margin-right", "auto")
            property("margin-left", "auto")
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            justifyContent(JustifyContent.SpaceBetween)
        }
    }) {
        Div({
            style {
                width(50.percent)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                fontFamily("Sofia Sans Semi Condensed")

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
                    fontSize(50.px)
                    fontFamily("Sofia Sans Semi Condensed")

                }
            }) {
                Text(article.name ?: "")
            }
        }
        Div({
            style {
                width(50.percent)
                alignSelf(AlignSelf.FlexEnd)
//                justifyContent(JustifyContent.FlexEnd)
            }
        }) {

            Div(
                {
                    style {
                        gap(5.px)
                        display(DisplayStyle.Flex)
                        flexDirection(FlexDirection.Row)
                        justifyContent(JustifyContent.FlexEnd)
                    }
                }
            ) {

                article.author?.socials?.let { socials ->

                    socials.instagram?.let {
                        roundButton(Colors.Instagram, {
                            window.open(it, "_blank")?.focus()
                        }) {
                            I({ classes("fa-brands", "fa-instagram") })
                        }
                    }

                    socials.twitter?.let {
                        roundButton(Colors.Twitter, {
                            window.open(it, "_blank")?.focus()
                        }) {
                            I({ classes("fa-brands", "fa-twitter") })
                        }
                    }


                    socials.linkedin?.let {
                        roundButton(Colors.Linkedin, {
                            window.open(it, "_blank")?.focus()
                        }) {
                            I({ classes("fa-brands", "fa-linkedin") })
                        }
                    }
                }

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
                        alignItems(AlignItems.Center)
                        justifyContent(JustifyContent.Center)
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
}

@Composable
fun simpleText(text: String?, isBold: Boolean = false) = Span({
    style {
        if (isBold) {
            fontWeight("bold")
        }
        display(DisplayStyle.Block)
        fontFamily("Sofia Sans Semi Condensed")
    }
}) { Text(text ?: "") }


@Composable
fun content(text: String?) = Div({
    id("article-content")
    style {
        fontFamily("Sofia Sans Semi Condensed")
        marginTop(30.px)
        maxWidth(720.px)
        property("color", "black")
        property("margin-right", "auto")
        property("margin-left", "auto")
    }
}) {
    Text(text ?: "test")
}

@Composable
fun article(article: Article, loader: MutableState<Boolean>)  {

    val isDisplayed = if (loader.value) "none" else "block"

    if (loader.value) {
        Loader()
    }

    Div({

        style {
            property("color", "white")
            property("display", isDisplayed)
        }
    }) {


        Header(article)
        Div(attrs = {

        }) {

            cover(article.cover)
            content(article.content)
        }

        window.setTimeout({
            eval("""
               
                const converter = new showdown.Converter()
                const articleContent = document.getElementById("article-content")
                hljs.highlightAll();
                const html = converter.makeHtml(articleContent.innerHTML)
                articleContent.innerHTML = html 
            """.trimIndent())
            loader.value = false

        }, 400)

    }
}


