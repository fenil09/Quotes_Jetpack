package Screens

import Models.DataManager
import Models.QuotesData
import Models.favouritQuote
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.quotes_jetpack.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


@Composable
fun QuoteDetail(quote:QuotesData){
    val context= LocalContext.current
    val reff=Firebase.firestore.collection("favouriteQuotes")

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

                Image(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier.padding(start = 280.dp)
                        .clickable {
                          val FavouriteQuote=favouritQuote(favquote = quote.quote, favauthor = quote.author)
                          CoroutineScope(Dispatchers.IO).launch {
                              try{
                                  reff.add(FavouriteQuote).await()
                                  withContext(Dispatchers.Main){
                                      Toast.makeText(context,"Quote added to Favourites succesfully",Toast.LENGTH_LONG).show()
                                  }
                              }catch (e:Exception){
                                  withContext(Dispatchers.Main){
                                      Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
                                  }
                              }
                          }
                        }
                )

            }
        }
    }
}