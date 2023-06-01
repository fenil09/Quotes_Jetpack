package Screens

import Models.DataManager
import Models.QuotesData
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.quotes_jetpack.R


@Composable
fun QuoteDetail(quote:QuotesData){

    BackHandler() {
        DataManager.SwitchPages(null)
    }

    Box(
      contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()


    ){
        Card(
           elevation = 4.dp,
            modifier = Modifier.padding(32.dp)
        ){
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,24.dp)
                    .align(Alignment.Center)
            ){
                Image(
                    painter = painterResource(R.drawable.quotes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                )
                Text(
                   text=quote.quote,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text=quote.author,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

            }
        }
    }
}