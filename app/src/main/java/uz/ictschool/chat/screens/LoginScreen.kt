package uz.ictschool.chat.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(){

    var name by remember {
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

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = name,
                label = { Text(text = "Name")},
                onValueChange = {
                    name = it

            })

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                label = { Text(text = "Password")},
                onValueChange = {
                    password = it
            })

            Spacer(modifier = Modifier.height(30.dp))
            
            Button(
//                colors = ButtonColors(containerColor = Color(0xFF7D5260)),
                onClick = { loginBtn() }) {
                
                Text(text = "Log in")
                
            }
        }


    }

}

fun loginBtn(){

}

@Preview(showBackground = true)
@Composable
fun logtest(){
    LoginScreen()
}