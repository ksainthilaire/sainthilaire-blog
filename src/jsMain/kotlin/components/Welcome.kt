package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val MS_TIMEOUT = 200

@Composable
fun PopupRecruiter(callback: (() -> Unit)) {


    val showAnimation = remember { mutableStateOf(false) }
    if (!showAnimation.value) {
        window.setTimeout({
            showAnimation.value = true
        }, MS_TIMEOUT)
    }

    Div(
        attrs = {
            style {
                position(Position.Fixed)
                display(DisplayStyle.Block)
                width(100.percent)
                height(100.percent)
                property("top", 0)
                property("left", 0)
                property("right", 0)
                property("bottom", 0)
                backgroundColor(rgba(0, 0, 0, 0.5))
                property("z-index", 2)
                cursor("pointer")
            }
        }
    )

    Div(
        attrs = {
            style {
                position(Position.Fixed)
                bottom(50.px)
                right(-(500.px))
                if (showAnimation.value) right(20.px)
                else right(-(500.px))
                maxWidth(345.px)
                width(100.percent)
                background("#fff")
                borderRadius(8.px)
                padding(12.px, 25.px, 22.px)
                property("transition", "right 0.3s ease")
                property("box-shadow", "0 5px 10px rgba(0, 0, 0, 0.1)")
                property("z-index", 2)
                property("transition", "right 0.3 ease")
            }
        }
    ) {
        Header(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    alignItems("center")
                    columnGap(15.px)
                }
            }
        ) {
            I(attrs = {
                style {
                    property("color", "#4070f4")
                    fontSize(32.px)
                }
            })
            H2(
                attrs = {
                    style {
                        fontFamily("Sofia Sans Semi Condensed")
                    }
                }
            ) {
                Text("\uD83D\uDC4B Bonjour! ")
            }

        }

        Div(attrs = {
            style {
                marginTop(20.px)
                fontFamily("Sofia Sans Semi Condensed")
            }
        }) {
            P() {
            }


            Span(attrs = {
                style {
                    display(DisplayStyle.Block)
                }
            }) {
                Text(
                    """
            Je vous ai fourni ce lien qui permet d'afficher cette pop-up."""
                )
            }


            Span(attrs = {
                style {
                    display(DisplayStyle.Block)
                    marginTop(20.px)
                }
            }) {
                Text(
                    """Je suis ouvert à toutes opportunités de recrutement.
            Vous pouvez notamment me proposer un entretien en ligne directement via Calendly
            en cliquant sur le bouton situé en haut "Me contacter"
            """
                )
            }

            Span(attrs = {
                style {
                    display(DisplayStyle.Block)
                    fontWeight("bold")
                    marginTop(20.px)
                }
            }) {
                Text("À noter que si mon profil vous intéresse, j'ai un préavis d'une durée de 3 mois.")
            }


        }


        Button(
            attrs = {
                onClick {
                    callback()
                }
                style {
                    property("border", "none")
                    property("color", "#fff")
                    property("padding", "8px 0")
                    marginTop(10.px)
                    borderRadius(4.px)
                    padding(10.px)
                    marginTop(20.px)
                    background("#4070f4")
                    cursor("pointer")
                    width(100.percent)
                    fontSize(15.px)
                }
            }
        ) { Text("Continuer!") }

    }
}