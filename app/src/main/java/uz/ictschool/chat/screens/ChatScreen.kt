package uz.ictschool.chat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.ictschool.chat.R
import uz.ictschool.chat.helpers.FireBaseHelper
import uz.ictschool.chat.helpers.SharedPrefHelper
import uz.ictschool.chat.model.Message
import uz.ictschool.chat.model.User
import uz.ictschool.chat.ui.theme.LoginButton
import uz.ictschool.chat.ui.theme.ToUserMessage

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController,
               key:String){

    val context = LocalContext.current
    val fromKey = SharedPrefHelper.getInstance(context).getUserKey()

    var toUser by remember{
        mutableStateOf(User())
    }
    var message by remember {
        mutableStateOf("")
    }
    val m = mutableListOf<Message>()

    var messages by remember {
        mutableStateOf(m)
    }


    FireBaseHelper.getUser(key){u->
        toUser = u
    }

    toUser.key?.let {
        FireBaseHelper.getMessagesInChat(it, fromKey){ rt->
        messages = rt
    }
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){

        ChatTopBar(user = toUser,navController)

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.BottomCenter){

            LazyColumn{
                items(messages){message->
                    MessageItem(message = message, fromKey = fromKey)
                }
            }
        }

        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            TextField(value = message,
                label = { Text(text = "Message")},
                onValueChange ={
                    message = it
                }, modifier = Modifier.weight(1f),
                singleLine = false,
            )

            Icon(imageVector = Icons.Rounded.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        FireBaseHelper.sendMessage(message, toUser.key!!, context)
                        message = ""
                    },
                tint = LoginButton)
        }
    }
}

@Composable
fun ChatTopBar(user: User,
               navController: NavController){
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(LoginButton)
        .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Image(painter = painterResource(id = R.drawable.telegram_logo),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp))

        Spacer(modifier = Modifier.width(30.dp))

        Column {
            user.firstName?.let {
                Text(text = it,
                    fontSize = 20.sp,
                    color = Color.White)
            }
            Spacer(modifier = Modifier.height(5.dp))
            user.username?.let {
                Text(text = it,
                    fontSize = 15.sp,
                    color = Color.White)
            }
        }
    }
}

@Composable
fun MessageItem(message: Message, fromKey: String){
    var tcolor = ToUserMessage
    var tAlignment = Alignment.CenterStart
    if (message.from == fromKey){
        tcolor = LoginButton
        tAlignment = Alignment.CenterEnd
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp),
        contentAlignment = tAlignment){

        Card(modifier = Modifier, ) {
            Text(text = message.text!!,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp).drawBehind {
                    drawCircle(color = tcolor, radius = this.size.maxDimension)
                })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun chattest(){
//    ChatScreen(navController = rememberNavController(), key = "-NmRS0ILKaUyRr1UNhrd")
//    //ChatTopBar(User("sdkfjh","","sdfgsdfg","",null),
//        //rememberNavController())
//}