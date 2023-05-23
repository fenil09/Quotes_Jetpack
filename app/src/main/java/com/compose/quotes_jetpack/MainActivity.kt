package com.compose.quotes_jetpack

import Screens.QuoteMain
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}



@Preview(showBackground = true, widthDp = 500)
@Composable
fun defaultPreview() {
    QuoteMain()
}