package components

import Component
import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import styles.AppStylesheet

@Composable
fun NavButtons() = Nav({
    style {
        gap(32.px)
        property("margin-left", "auto")
        order(3)
    }
}) {
    Button(

        {
            onClick {
                window.location.href = "https://calendly.com/sainthilaire-keny/30min"
            }
            style {
                width(200.px)
                fontSize(16.px)
                fontWeight(600)
                property("border", "none")
                property("color", "#fff")
                cursor("pointer")
                margin(20.px)
                height(55.px)
                textAlign("center")
                backgroundSize("300% 100%")
                borderRadius(50.px)
                property("moz-transition", "all .4s ease-in-out")
                property("-o-transition", "all .4s ease-in-out")
                property("-webkit-transition", "all .4s ease-in-out")
                property("transition", "all .4s ease-in-out")
                backgroundImage("linear-gradient(to right, #25aae1, #4481eb, #04befe, #3f86ed)")
                property("box-shadow", "0 4px 15px 0 rgba(65, 132, 234, 0.75)")
            }
        }) {
        Text("Me contacter")
    }
}

@Composable
fun Navbar(context: Router.Context) = Nav({
    style {
        display(DisplayStyle.Flex);
        justifyContent(JustifyContent.SpaceBetween)
        alignItems(AlignItems.Center)
        property("background-color", "white")
    }
}) {
    val isSelected = context.path.equals("/about")
    NavMain() {
        NavLinks {
            NavLink("Blog", !isSelected) {
                context.navigate("/")
            }
            NavLink("À propos de moi", isSelected) {
                context.navigate("/about")
            }
        }
    }
    NavButtons()
}

@Composable
fun NavMain(content: Component) = Div({
    style {
        display(DisplayStyle.Flex);
        gap(32.px)
        property("margin-left", "auto")
        order(2)
    }
}) {
    content()
}

@Composable
fun NavLinks(content: Component) = Ul({
    style {
        display(DisplayStyle.Flex)
        listStyle("none")
        gap(40.px)
    }
}) {
    content()
}

@Composable
fun NavLink(text: String, isSelected: Boolean = false, onClick: (() -> Unit)) = Li(
    attrs = {
        classes(AppStylesheet.NavLink)
    }
) {
    A(href = "#", {
        style {
            style {
                if (isSelected) {
                    color(rgb(9, 40, 86))
                    property("border-bottom", "2px solid rgb(9, 40, 86)")
                }
            }
        }
        onClick {
            onClick()
        }

    }) {
        Text(text)
    }
}
