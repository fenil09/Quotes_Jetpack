package com.compose.quotes_jetpack

import Models.DataManager
import Models.QuotesData
import Models.favouritQuote
import Screens.QuoteMain
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.quotes_jetpack.ui.theme.Quotes_JetpackTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
       GetFromFirestore()
    }
}
    @Composable
    fun GetFromFirestore(){
        val data= remember { mutableStateOf<List<favouritQuote>>(emptyList()) }

        LaunchedEffect(Unit){
            val reff=Firebase.firestore.collection("favouriteQuotes")
            val querysnapshot=reff.get().await()

            val datalist= mutableListOf<favouritQuote>()
            for(i in querysnapshot.documents){
                val item=i.toObject<favouritQuote>()
                item?.let{
                    datalist.add(it)
                }
            }
            data.value=datalist
        }

        LazyColumn {
            items(data.value){
                Card(
                    elevation = 4.dp,
                    border = BorderStroke(2.dp, Color.Black),
                    modifier = Modifier.padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.quotes),
                            contentDescription = null,
                            alignment = Alignment.TopStart,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = it.favquote,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.padding(top = 15.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(.4f)
                                    .height(1.dp)
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.padding(top = 5.dp))
                            Text(
                                text = it.favauthor,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }    }



