package uz.ictschool.chat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.w3c.dom.Text
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.model.User
import uz.ictschool.chat.ui.theme.LoginButton

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewChatsScreen(navController: NavController){
    val context = LocalContext.current

    var searchText by remember {
        mutableStateOf("")
    }
    val c = mutableListOf<User>()
    var contacts by remember {
        mutableStateOf(c)
    }
    val key = SharedPrefHelper.getInstance(context).getUserKey()
    FireBaseHelper.getAllContacts(key){
        contacts = it
    }

    Column(modifier = Modifier.fillMaxSize()) {
        
        NewChatsTopBar(navController = navController)

        TextField(value = searchText,
            modifier = Modifier,
            onValueChange ={
            searchText = it
        } )

        LazyColumn{
            items(contacts){contact->
                ChatItem(contact = contact,
                    navController = navController)

            }

        }

    }
}

@Composable
fun NewChatsTopBar(navController: NavController){
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(LoginButton)
        .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = Color.White)
        Spacer(modifier = Modifier.width(20.dp))

        Text(text = "New Chats",
            fontSize = 25.sp,
            color = Color.White)


    }
}
