package uz.ictschool.chat.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.navigation.HOME_ROUTE
import uz.ictschool.chat.navigation.NavGraph
import uz.ictschool.chat.navigation.SIGNUP_ROUTE
import uz.ictschool.chat.ui.theme.LoginButton
import uz.ictschool.chat.ui.theme.SignupButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController){
    val context = LocalContext.current

    var userName by remember {
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Log in",
                fontSize = 30.sp)

            Spacer(modifier = Modifier.height(17.dp))

            OutlinedTextField(
                value = userName,
                label = { Text(text = "Username")},
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    userName = it

            })

            Spacer(modifier = Modifier.height(17.dp))

            OutlinedTextField(
                value = password,
                label = { Text(text = "Password")},
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    password = it
            })

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                loginBtn(userName, password, context, navController)
            },
                modifier = Modifier.width(250.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LoginButton)) {
                Text(text = "Log In",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
            }

            Spacer(modifier = Modifier.height(17.dp))

            Button(onClick = {
                  navController.navigate(SIGNUP_ROUTE)
                             },
                modifier = Modifier.width(250.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SignupButton)) {
                Text(text = "SignUp",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
            }

        }
    }

}

fun loginBtn(userName:String,
             password:String,
             context: Context,
             navController: NavHostController){
    FireBaseHelper.logIn(userName, password){key ->
        if (key == null){
            Toast.makeText(context,
                "Check username or password", Toast.LENGTH_SHORT).show()
        }else{
            SharedPrefHelper.getInstance(context).setUserKey(key)
            navController.navigate(HOME_ROUTE){
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
            Toast.makeText(context,
                "You logged in", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun logtest(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    LoginScreen(navController)
}