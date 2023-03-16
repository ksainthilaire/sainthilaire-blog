package models

import kotlin.js.Date

data class Article(
    val id: Int? = null,
    val category: Category? = null,
    val name: String? = null,
    val path: String? = null,
    val author: Author? = null,
    val description: String? = null,
    val content: String? = null,
    val cover: String? = null,
    val createdAt: Date? = null
)


val users: List<Author> = listOf(
    Author(fullName = "SAINT HILAIRE Keny", socials = Socials(
        twitter = "https://twitter.com/ksainthilaire",
        linkedin = "https://linkedin.com/ksainthi"
    )),
    Author(fullName = "Atan Take"),
    Author(fullName = "Amanda Bjornson")
)

val categories: List<Category> = listOf(
    Category(id = 0, "Sécurité Android"),
    Category(id = 1, "Fantasy"),
    Category(id = 2, "Anime")
)

val articles: List<Article> = listOf(
    Article(
        id = 0,
        category = categories[0],
        name = "Un WebGL extraordinaire",
        author = users[0],
        path = "webgl-extraordine",
        description = "Back in 2019, Gucci brought video games to its app with a new section called Gucci Arcade, inspired by creative",
        content = """
            # 6stem 

            # Déploiement sur AWS

            ## Upload l'image Docker

            ```sh
            # S'authentifier avec ses identifiants
            aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 508378123524.dkr.ecr.eu-west-1.amazonaws.com

            # Construire l'image
            docker build -t stem-node .

            # Tagger l'image
            docker tag stem-node:latest 508378123524.dkr.ecr.eu-west-1.amazonaws.com/stem-node:latest

            # Upload l'image sur AWS
            docker push 508378123524.dkr.ecr.eu-west-1.amazonaws.com/stem-node:latest
            ```

            ## Lancement des conteneurs 

            1. Se rendre sur la rubrique Amazon ECS.
            2. Cliquer sur le menu `Clusters`
            3. Choisir `ECSDemoApp`
            4. Cliquer sur l'onglet `Tâches`
            5. `Créer une nouvelle tâches`
            6. Spécifier le type de lancement `Fargate`, et choisir `Linux` pour la famille de systèmes d'exploitations.


            ![Image 1](https://i.ibb.co/n07smnw/screenshot-1.png)

            Il faut ouvrir les ports nécessaires, 6379 pour le port du conteneur redis, et 443 pour le port HTTPS du serveur web.
            En cliquant sur "Modifier" à droite du groupe de sécurité.

            ![Image 2](https://i.ibb.co/vLpWtyh/screenshot-2.png)



            ![Image 3](https://i.ibb.co/3WJcRZm/screenshot-3.png)

            Une fois la tâche crée, et le conteneur exécuter, l'adresse IP publique générer automatiquement est
            `3.249.206.190`.

            ![Image 4](https://i.ibb.co/YLCN4Bc/screenshot-4.png)

            # Organisation

            ├── assets          
            │   ├── css        (CSS)
            │   ├── fonts (Polices d'écritures)
            │   ├── images (Images du jeu)
            │   ├── img (Anciennes images du jeu)
            │   └── js  (Code JavaScript côté client)  
            ├── certs (Certificats HTTPS)
            ├── data  (Ressources JSON du jeu)
            ├── game 
            │   ├── api (Nownodes)
            │   ├── managers (Classe principale pour le jeu, et partie de jeu)
            │   ├── model (Contient une classe User avec ses propriétés)
            │   └── resources   (Contient les ressources du jeu)
            ├── settings (Configuration du jeu)
            └── templates (Templates EJS)

            ## La configuration (config/settings.json)

            ```json

            {
            	"game": {
            		"hitmax": "2",
            		"autostart": "false"
            	},
            	"http": {
            		"port": 443
            	},
            	"redis": {
            		"host": "127.0.0.1",
            		"port": "6379",
            		"password": "eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81",
            		"db": "0"
            	},
            	"firebase": {
            	  "type": "service_account",
            	  "project_id": "stem-f3fd9",
            	  "private_key_id": "410d95a5935d8f0ea68b20a4912f765af1fb6229",
            	  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZ1t2z5wGBqcE3\nMUgcrdUiAWhGgZ0YMtT1WIsbjUP09S8DrEUU10FcqxsVh90X4TmCxIxNt806GF/p\nhxjEDh+3hTzCKWvZS5bXs/6USvFzdDfImmQn7NGWhbTMTe83j+9qtGVIXO7UAsTF\n+KcVAD9cjfZo/MiJOq9SX4R4k254K2Ud7oPemMMOhT6mTKh/9CbY+tNyJ0+8vv18\n0mn2NdVzly8XN5GRZuLE6iBdC3qxZfeQgniNPElk6/V1VL0xvkR07nYfWXMsfdpt\nXWF6qhMKgq4ZVstiPx3zHgS8W0da+EQhjzuw/oImOdV87t8JOqM2l1m5yMXYOu8+\nNb6CcWchAgMBAAECggEABsW6CRZgXOUToOscFsnp2Hmkqb08poaJSc7CvaCyV0Gm\ngF/DGqwb4+EdDZn2j/gWWBojImUJjR3qELAUv07g+h0rFb5Y3Dm4GPj3XBnxvGsd\nczvnS2lWrV5HGANP4a1cqiedRtr+iO5keqMFswK5zY9bbOF3vLIzllkKTM840+UX\nCNhdGaTyeM7JwXvMs9Ba3NztYe0tS9Uob7UmVG1Cdri0wh401T/FTDDua9oECmar\nIM8OLXMfNo5TZbCO1x/JUCKcxSENXniZU6/NSgC7cnx/CylGKj1ofuBdMW28S1YI\n4i0fVg6yg/+T/x/86ecwKq1jv+K8NLBGeH4GtTwYAQKBgQD10LvEwW3n/tTxtTSz\nloQcF/IQat9NDHWXiT5tciPxjy4Dx/CDUKK1XrTjvZ06YSg9E/2CaG5ZlLuZ+xu0\n8dr9nLUo2UtsAb/7dS3kNL6zqWzl8dShOfij7cGOu0P+kvJZzvwFwkj101If+Ver\nkYkfiwfO7EbKl5Kdtfuq3Ly/IQKBgQDi3WbVbkohg0YjJ2OonV4RcNzxfR874kWq\nbgZr9lRxAMYSQOxKVkkv/9orJF1CZKPkCfJDd24feTymKAlGa+cp+Ll5B+3u+ibu\nvM0xpp38GmeL1Q92uZ94o28KPNw/7xXPOCU6wU20ST//5wSVON2MZ1z2j4zota/7\nhnHUomeoAQKBgAEfGaw1IbY8QGZ16C6lkia6qT0P78LfOq4dTy6apbVVbOVRhTRk\nKsnfC746QDxi8xcMP0zYAyDff9edAKxbqh8cFut/yktM2r5f83ymDPSu4jNOXiT6\nKbs9u3NWQWIo83gbbKTuFP0iIK7KqW679D8zmC6Gf3AvLtkPWn2WEjphAoGBAMcf\nk6oUEucVZRNn+FZAQhfG23bu3uJd+c3corBSVs2U40WUTJH26GhEBodFdMHYue1n\nOYg0JKllbXFdxL5V6WFymcD8z7+pO1Di0Kwa1aI4vtgCW39W7/0gXk87GgrX3Gm0\nmyDWGFD81jrT17buYa9h7jvPxdtf1aZM3E7JeXgBAoGAFHaQnitn6hFQ+PBL3Lbn\npZldI95UiBh09Gp2mAX6dRwM5iGnj351fAOiUjMLNt8z88VB8Ka+oPK1YQ3YSLzA\nFcAJgIwqeQCAggT7An51jLVzxPAFwF3/yeOQtioqJPPoGCGrlxIJS7VJa3UXaK1F\nx3rBlW0DvX6r6mSod8njSLk=\n-----END PRIVATE KEY-----\n",
            	  "client_email": "firebase-adminsdk-yj4ss@stem-f3fd9.iam.gserviceaccount.com",
            	  "client_id": "116136761518860328766",
            	  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
            	  "token_uri": "https://oauth2.googleapis.com/token",
            	  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
            	  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-yj4ss%40stem-f3fd9.iam.gserviceaccount.com"
            	},
            	"nownodes": {
            		"apikey": "b976bd1e-d254-4cee-b9b5-3f726d3837be"
            	}
            }

            ```

            # Classes
            | Nom de classe | Description                                                                                                                                                                                                                        | Emplacement           | Propriétés                                                                                                                                                                                      | 
            |---------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
            | Player        | Classe représentant un joueur connecté au jeu                                                                                                                                                                                      | game/model/Player.js  | ID: id Firebase<br /> InstanceID: id du joueur<br /> name: Nom du joueur<br /> score: Score du joueur dans la partie actuel<br /> wallet: Adresse du wallet<br /> token: Token de session<br /> |   
            | Game          | Classe représentant la globalité du jeu : gestion des différents joueurs, <br />des différentes parties, envoie du jeton.                                                                                                          | game/managers/Game.js | rooms: Liste des parties de jeu en cours<br /> players: Liste des joueurs<br />                                                                                                                 |   
            | Room          | Classe représentant une partie en cours : Contenant la liste des joueurs, avec les mécanismes de fonctionnement du jeu. <br /> Lorsqu'un joueur gagne le jeu, la fonction `setWinner` et `Room.js` est appelé avec l'ID du joueur. | game/managers/Room.js | id: ID/Référence de la partie de jeu<br /> players: Liste des joueurs<br /> nbpopit: Liste des boules qui éclatent <br />                                                                       |   |         
            # Persistence du wallet avec Redis
            - La fonction `saveWalletById` (Game.js) permet de sauvegarder l'adresse wallet associé à un joueur sur le serveur Redis.
            - La fonction `getWalletById` (Game.js) permet de récupérer l'adresse wallet associé à un joueur.
            - L'instance Redis est accessible directement avec la propriété `this.#cacheServer` (où toutes les méthodes Redis sont accessibles).
            Exemple:
            ```js
            this.#cacheServer.set("mykey", "value")
            ```
            
        """.trimIndent(),
        createdAt = Date(),
        cover = "https://lexica-serve-encoded-images2.sharif.workers.dev/full_jpg/03c51eb8-aae1-4d78-bd17-69e494b43b7b"
    ),

    Article(
        id = 0,
        category = categories[0],
        name = "Un test pomme",
        author = users[0],
        path = "webgl-extraordine",
        description = "Back in 2019, Gucci brought video games to its app with a new section called Gucci Arcade, inspired by creative",
        content = "test",
        createdAt = Date(),
        cover = "https://blogapp.bitdefender.com/hotforsecurity/content/images/size/w1000/wordpress/2019/10/android-4412596_1920.jpg"
    )

)