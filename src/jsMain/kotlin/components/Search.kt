package components

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.css.CSSMarginRule

@Composable
fun SearchView() = Div({
    style {
        minHeight(220.px)
        borderRadius(20.px)
        marginTop(50.px)
        marginRight(10.px)
        marginLeft(10.px)
        property("background-color", rgb(47, 91, 127))
        property("color", "white")
    }
}) {


    Div({

        style {
            paddingTop(30.px)
            maxWidth(720.px)
            property("margin-right", "auto")
            property("margin-left", "auto")
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
        }
    }) {
        Div({
            style {
                width(50.percent)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
                fontFamily("Sofia Sans Semi Condensed")

            }
        }) {

            Span({
                style {
                    display(DisplayStyle.Block)
                    fontFamily("Sofia Sans Semi Condensed")
                    fontWeight("bold")
                    fontSize(40.px)
                }
            }) {
                Text("Mon blog")
            }

        }

    }
    Search()
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun Search() = Div(
    attrs = {
        style {
            position(Position.Relative)
            maxWidth(720.px)
            borderRadius(5.px)
            property("position", "relative")
            top(100.percent)
            transform {
                translateY(170.percent)
            }
            property("background-color", "white")
            property("max-width", 720.px)
            property("margin-right", "auto")
            property("margin-left", "auto")
            property(
                "box-shadow", """
                 inset 0 0 0.5px 1px hsla(0, 0%, 100%, 0.1),
              0 0 0 1px hsla(230, 13%, 9%, 0.075),
              0 0.3px 0.4px hsla(230, 13%, 9%, 0.02),
              0 0.9px 1.5px hsla(230, 13%, 9%, 0.045),
              0 3.5px 6px hsla(230, 13%, 9%, 0.09)
            """.trimIndent()
            )
        }
    }
) {
    I({
        classes("fa-solid", "fa-magnifying-glass")

        style {
            position(Position.Absolute)
            top(50.percent)
            property("left", "clamp(0.3125rem, -0.3852rem + 3.4884vw, 1.25rem)")
            property("font-size", "clamp(1.25rem, 0.9709rem + 1.3953vw, 1.625rem)")
            transform { translateY(-(50).percent) }
            property("color", "#7a90bb")
        }
    })
    Input(InputType.Text, attrs = {
        placeholder("Rechercher")
        style {
            property("height", "clamp(2.5rem, 1.5698rem + 4.6512vw, 3.75rem)")
            background("white")
            maxWidth(100.percent)
            property("padding-left", "clamp(1.875rem, 0.7122rem + 5.814vw, 3.4375rem)")
            property("color", "#7a90bb")
            borderRadius(10.px)
            property("border", "none")
            property("box-shadow", "none")
            outline("none")
        }
    })
}