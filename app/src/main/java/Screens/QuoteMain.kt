package Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.quotes_jetpack.R


@Composable
fun QuoteMain(){

    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(12.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ){
            Image(
                painter = painterResource(androidx.core.R.drawable.notification_bg),
                contentDescription = null,
                alignment = Alignment.TopStart,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column (modifier = Modifier.weight(1f)){
                Text(
                    text = "Life isn't about getting and having,its about giving and being.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Box(
                    modifier = Modifier.fillMaxWidth(.4f)
                        .height(1.dp)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.padding(top=5.dp))
                Text(
                    text = "Kevin Kruse",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Image(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier.padding(start=280.dp)
                )
            }
        }
    }
}