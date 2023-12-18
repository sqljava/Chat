package uz.ictschool.chat.navigation

val SPLASH_ROUTE = "splash_screen"
val LOGIN_ROUTE = "login_screen"
val REGISTER_ROUTE = "register_screen"
val HOME_ROUTE = "home_screen"

sealed class Screens(val route: String) {

    object Splash: Screens(SPLASH_ROUTE)
    object Login: Screens(LOGIN_ROUTE)
    object Register: Screens(REGISTER_ROUTE)
    object Home: Screens(HOME_ROUTE)


}