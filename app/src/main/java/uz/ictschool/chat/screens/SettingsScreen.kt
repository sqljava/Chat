package uz.ictschool.chat.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.model.User
import uz.ictschool.chat.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController){
    val context = LocalContext.current

    val key = SharedPrefHelper.getInstance(context).getUserKey()

    var user by remember {
        mutableStateOf(User("","","",""))
    }
    Log.d("TAG", "SettingsScreen: $user")

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var dataCopied by remember {
        mutableStateOf(false)
    }
        if (!dataCopied){
            FireBaseHelper.getUser(key){u ->
                firstName = u.firstName!!
                lastName = u.lastName!!
                userName = u.username!!
                password = u.password!!
                dataCopied = true
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        contentAlignment = Alignment.Center){
//        Text(text = "Log out",
//            modifier = Modifier.align(Alignment.TopEnd)
//                .padding(20.dp),
//            fontSize = 30.sp)

        Icon(imageVector = Icons.Default.ExitToApp,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    SharedPrefHelper
                        .getInstance(context)
                        .setUserKey("")
                    navController.navigate(Screens.Login.route)
                },
            tint = Color.Red
            )

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Settings",
                fontSize = 30.sp)

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = firstName!!,
                label = { Text(text = "Firstname") },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    firstName = it
                })
            Spacer(modifier = Modifier.height(18.dp))
            OutlinedTextField(
                value = lastName!!,
                label = { Text(text = "Lastname") },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    lastName = it
                })

            Spacer(modifier = Modifier.height(18.dp))
            OutlinedTextField(
                value = userName!!,
                label = { Text(text = "Username") },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    userName = it
                })

            Spacer(modifier = Modifier.height(18.dp))
            OutlinedTextField(
                value = password!!,
                label = { Text(text = "Password") },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                onValueChange = {
                    password = it
                })

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                val u = User(userName, password, firstName, lastName, key)
                FireBaseHelper.updateUser(u)
                Toast.makeText(context,
                    "Data updated", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens.Home.route)
            },
                modifier = Modifier.width(250.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))) {
                Text(text = "Save",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun settingstest(){
    SettingsScreen(navController = rememberNavController())
}