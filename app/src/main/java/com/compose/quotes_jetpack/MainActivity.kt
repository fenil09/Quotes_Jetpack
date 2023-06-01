package com.compose.quotes_jetpack

import Models.DataManager
import Screens.QuoteDetail
import Screens.QuoteList
import Screens.QuoteMain
import Screens.SetupScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            DataManager.loadassetfromfile(applicationContext)
        }
        setContent {
          App()
        }
    }
}




@Composable
fun App(){
    if(DataManager.isdataloaded.value) {

        if(DataManager.currentpage.value==Pages.QUOTELIST){
            SetupScreen(data = DataManager.data){
               DataManager.SwitchPages(it)
            }
        }
        else{
            DataManager.currentquote?.let { QuoteDetail(quote = it) }
        }
    }
    else{
        Box(
          contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)

        ){

            Text(
               text="Please wait getting your quotes",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

enum class Pages{
    QUOTELIST,
    QUOTEDETAIL
}