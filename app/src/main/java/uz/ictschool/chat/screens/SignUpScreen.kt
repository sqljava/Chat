package uz.ictschool.chat.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.model.User
import uz.ictschool.chat.navigation.NavGraph
import uz.ictschool.chat.navigation.Screens
import uz.ictschool.chat.ui.theme.LoginButton
import uz.ictschool.chat.ui.theme.SignupButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController){
    val context = LocalContext.current

    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var nullError by remember {
        mutableStateOf(true)
    }
    
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = firstName,
            label = { Text(text = "Firstname") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            onValueChange = {
                firstName = it
                nullError = firstName == ""
            })
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = lastName,
            label = { Text(text = "Lastname") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            onValueChange = {
                lastName = it
                nullError = lastName == ""
            })

        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = userName,
            label = { Text(text = "Username") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            onValueChange = {
                userName = it
                nullError = userName == ""
            })

        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            value = password,
            label = { Text(text = "Password") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                password = it
                nullError = password == ""
            })

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {

            if (nullError){
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                if (FireBaseHelper.checkUsername(userName)){
                    Toast.makeText(context, "This username exists. Try other one", Toast.LENGTH_SHORT).show()
                }else{
                    if (password.length<7){
                        Toast.makeText(context, "Password must be more than 8 characters", Toast.LENGTH_SHORT).show()
                    }else{
                        val user = User(userName, password,firstName,lastName,null)
                        FireBaseHelper.signUp(user, context){success->
                            if (success){
                                Toast.makeText(context,
                                    "Successfully signed up",
                                    Toast.LENGTH_SHORT).show()
                                navController.navigate(Screens.Home.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
                         },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SignupButton)) {
            Text(text = "SignUp",
                fontSize = 17.sp,
                modifier = Modifier.padding(vertical = 10.dp))
        }
        Spacer(modifier = Modifier.height(17.dp))
        Button(onClick = {
                         navController.popBackStack()
                         },
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LoginButton)) {
            Text(text = "LogIn",
                fontSize = 17.sp,
                modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun signuptest(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
    SignUpScreen(navController)
}