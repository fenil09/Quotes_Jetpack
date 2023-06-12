package Screens

import Models.QuotesData
import Screens.QuoteList
import android.content.Intent
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.quotes_jetpack.MainActivity2
import com.compose.quotes_jetpack.R


@Composable
fun SetupScreen(data:Array<QuotesData>,onclick:(quotesData:QuotesData)->Unit) {
    val context= LocalContext.current
    Column (){

        Box(modifier = Modifier.padding(20.dp), contentAlignment = Alignment.Center){
            Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 120.dp)){
                Text(
                    text = "QuotesAPP",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(50.dp))
                Image(
                    painter = painterResource(R.drawable.saving),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                        .clickable {
                            val intent=Intent(context,MainActivity2::class.java)
                            context.startActivity(intent)
                        }
                )
            }
        }
        QuoteList(data=data,onclick)
    }
    }

