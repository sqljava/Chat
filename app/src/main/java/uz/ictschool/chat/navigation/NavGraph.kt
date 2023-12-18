package uz.ictschool.chat.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.ictschool.chat.screens.LoginScreen
import uz.ictschool.chat.screens.SplashScreen

@Composable
fun NavGraph(navController: NavHostController){
    
    NavHost(navController = navController,
        startDestination = Screens.Splash.route,
        ){
        composable(route = Screens.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Login.route){
            LoginScreen()
        }

    }
}