package uz.ictschool.chat.navigation

val SPLASH_ROUTE = "splash_screen"
val LOGIN_ROUTE = "login_screen"
val SIGNUP_ROUTE = "signup_screen"
val HOME_ROUTE = "home_screen"
val CHAT_ROUTE = "chat_route"
val CHAT_KEY = "chatKey"

sealed class Screens(val route: String) {

    object Splash: Screens(SPLASH_ROUTE)
    object Login: Screens(LOGIN_ROUTE)
    object SignUp: Screens(SIGNUP_ROUTE)
    object Home: Screens(HOME_ROUTE)
    object Settings: Screens("settings")
    object Chat: Screens("chat_route/{$CHAT_KEY}"){
        fun passKey(key: String):String{
            return "$CHAT_ROUTE/$key"
        }
    }


}