package uz.ictschool.chat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column {
            Text(text = "Texts")
        }
    }

}

@Preview
@Composable
fun homeTest(){
    HomeScreen()
}