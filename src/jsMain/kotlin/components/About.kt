package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Icons() = Div(
    attrs = {
        style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {

}

@Composable
fun About(context: Router.Context) {
    Navbar(context)
    Div(attrs = {
        style {
            fontFamily("Sofia Sans Semi Condensed")
            marginTop(30.px)
            maxWidth(720.px)
            fontSize(20.px)
            property("color", "black")
            property("margin-right", "auto")
            property("margin-left", "auto")
        }
    }) {
        Div(attrs = {
            style {
                marginTop(20.px)
                marginBottom(20.px)
            }
        }) {
            Text("Bonjour,")
        }
        Text(
            """
            Je suis un passionné de développement mobile, basé à Paris, je suis actuellement développeur Android dans une startup.
            Sur mon blog vous trouverez une veille constante de Jetpack Compose, Kotlin Multiplatform et tout l'écosystème Android 📈
        """.trimIndent()
        )

        Div(
            attrs = {
                style {
                    marginTop(20.px)
                    fontSize(20.px)
                }
            }
        ) {
            Text(
                """Vous pouvez consulter 👨‍🎨 mes maquettes UX/UI sur """
            )
            A(href = "https://www.behance.net/kenysainth1", attrs = {
                target(ATarget.Blank)
                style {
                    color(rgb(9, 40, 86))
                }
            }) { Text("Behance") }
        }

        Div(
            attrs = {
                style {
                    marginTop(20.px)
                    fontSize(20.px)
                }
            }
        ) {
            Text(
                """Voici les applications Android que j'ai développé et qui sont maintenant disponible sur Google Play. 
                    |Vous pouvez également vous rendre sur """.trimMargin()
            )
            A(href = "https://github.com/ksainthilaire", attrs = {
                target(ATarget.Blank)
                style {
                    color(rgb(9, 40, 86))
                }
            }) { Text("mon Github") }
            Text(" où vous pourrais trouver tout le travail open source que j'ai crée au fil du temps")
        }

        Div(
            attrs = {
                style {
                    marginTop(20.px)
                    fontSize(20.px)
                }
            }
        ) {
            Text(
                """Je suis disponible 🟢 pour des missions (d'une durée de 6 mois maximum) en freelance à distance. 
                    Vous pouvez me contacter à """
            )
            A(href = "mailto:sainthilaire.paris@gmail.com", attrs = {
                style {
                    color(rgb(9, 40, 86))
                }
            }) { Text("sainthilaire.paris@gmail.com") }
            Text(" ou directement consulter mon profil ")
            A(href = "https://www.malt.fr/profile/kenysainthilaire1", attrs = {
                target(ATarget.Blank)
                style {
                    color(rgb(9, 40, 86))
                }
            }) {
                Text("Malt")
            }
        }
    }
}