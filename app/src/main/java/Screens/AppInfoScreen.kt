package Screens

import Models.QuotesData
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SetupScreen(data:Array<QuotesData>) {

     Column() {
         Text(
             text="Quotes App",
             textAlign = TextAlign.Center,
             fontStyle = FontStyle.Italic,
             fontWeight = FontWeight.Bold,
             fontSize = 15.sp,
             modifier = Modifier.padding(8.dp,24.dp)
                 .fillMaxWidth(1f)
         )
         QuoteList(data)
     }


    }
