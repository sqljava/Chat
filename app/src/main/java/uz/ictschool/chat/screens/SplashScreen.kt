package uz.ictschool.chat.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.ictschool.chat.R
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.navigation.HOME_ROUTE
import uz.ictschool.chat.navigation.LOGIN_ROUTE
import uz.ictschool.chat.navigation.NavGraph


@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        delay(2000)
        val userkey = SharedPrefHelper.getInstance(context).getUserKey()
        if (userkey == ""){
            navController.navigate(LOGIN_ROUTE)
        }else{
            navController.navigate(HOME_ROUTE)
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun splashTest(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    SplashScreen(navController)
}