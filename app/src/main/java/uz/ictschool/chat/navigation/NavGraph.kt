package uz.ictschool.chat.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.ictschool.chat.screens.ChatScreen
import uz.ictschool.chat.screens.HomeScreen
import uz.ictschool.chat.screens.LoginScreen
import uz.ictschool.chat.screens.NewChatsScreen
import uz.ictschool.chat.screens.SettingsScreen
import uz.ictschool.chat.screens.SignUpScreen
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
            LoginScreen(navController)
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Chat.route,
            arguments = listOf(navArgument(CHAT_KEY){
                type = NavType.StringType
            })
        ){navBackStackEntry ->
            val key = navBackStackEntry.arguments?.getString(CHAT_KEY)
            ChatScreen(navController = navController, key = key!!)
        }
        composable(route = Screens.Settings.route){
            SettingsScreen(navController = navController)
        }
        composable(route = Screens.NewChats.route){
            NewChatsScreen(navController)
        }

    }
}