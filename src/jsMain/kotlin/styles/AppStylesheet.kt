package styles

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

object AppStylesheet : StyleSheet() {


    val line by keyframes {
        0.percent {
            property("background-position-x", 390.px)
        }
    }

    private val loadedView by keyframes {
        from {
            opacity(0)
        }
        to {
            opacity(1)
        }
    }

    val Body by style {
        animation(loadedView) {
            duration(5.s)
            delay(1.s)
            direction(AnimationDirection("foward"))
            fillMode(AnimationFillMode("linear"))
        }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val NavLink by style {


        padding(0.px)
        margin(0.px)
        boxSizing("inherit")
        letterSpacing((1.1).px)
        property("transition", "background-position-x 0.9s linear")


        onlyChild {

            self + hover style {
                color(rgb(9, 40, 86))
                property("border-bottom", "2px solid rgb(9, 40, 86)")
            }

            padding(16.px, 32.px);
            fontFamily("Sofia Sans Semi Condensed")
            letterSpacing((-0.5).px)
            textDecoration("none")
            fontWeight(200)
            color(Color.black)
        }


    }


}

