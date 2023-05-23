package Screens

import Models.QuotesData
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


@Composable
fun QuoteList(data:Array<QuotesData>){
    LazyColumn (content = {
        items(data){
            QuoteMain(quotesData = it)
        }
    })
}