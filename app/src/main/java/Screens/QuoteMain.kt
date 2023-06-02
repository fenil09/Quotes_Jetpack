package Screens

import Models.QuotesData
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import com.compose.quotes_jetpack.R
import kotlin.text.Typography.quote


@Composable
fun QuoteMain(quotesData: QuotesData, onclick:(quotesData:QuotesData)-> Unit) {


        Card(
            elevation = 4.dp,
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier.padding(12.dp)
                .clickable {
                    onclick(quotesData)
                }


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
                        text = quotesData.quote,
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
                        text = quotesData.author,
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
                                ColorFilter.tint(Color.Red)
                            }
                    )

                }
            }
        }
    }

