package uz.ictschool.chat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.ictschool.chat.R
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.model.User
import uz.ictschool.chat.navigation.Screens
import uz.ictschool.chat.ui.theme.LoginButton

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(navController: NavController){
    var context = LocalContext.current

    var a = mutableListOf<User>()
    var contacts by remember {
        mutableStateOf(a)
    }
    val key = SharedPrefHelper.getInstance(context).getUserKey()
    FireBaseHelper.getAllContacts(key){c->
        contacts = c

    }

    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier
            .fillMaxSize()
        ){

            HomeTopBar(navController = navController)

            LazyColumn {
                items(contacts){contact->
                    ChatItem(contact = contact,
                        navController = navController)
                }
            }
        }

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.size(50.dp).align(Alignment.BottomEnd),
            shape = RoundedCornerShape(50.dp)
        ) {

        }

    }


}

@Composable
fun HomeTopBar(navController: NavController){

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(LoginButton),
        verticalAlignment = Alignment.CenterVertically){

        Icon(imageVector = Icons.Default.Settings,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.navigate(Screens.Settings.route)
                },
            tint = Color.White)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatItem(contact: User,
             navController: NavController){


    Card(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .combinedClickable(
            onClick = {
                navController.navigate(Screens.Chat.passKey(contact.key!!))
            },
            onLongClick = {

            }
        )
        ) {
        Row (modifier = Modifier
            .padding(10.dp)){

            Image(painter = painterResource(id = R.drawable.telegram_logo),
                contentDescription = null)

            Column(modifier = Modifier.padding(start = 20.dp),
                verticalArrangement = Arrangement.Center) {
                Text(text = contact.firstName!!,
                    fontSize = 20.sp)
                Text(text = contact.username!!,
                    fontSize = 15.sp)
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Preview(showBackground = true)
@Composable
fun homeTest(){
    HomeScreen(rememberNavController())
}