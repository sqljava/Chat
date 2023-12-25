package uz.ictschool.chat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.model.User

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
    var key = SharedPrefHelper.getInstance(context)!!
    FireBaseHelper.getAllContacts(key){

    }

    Column(modifier = Modifier.fillMaxSize()) {

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
