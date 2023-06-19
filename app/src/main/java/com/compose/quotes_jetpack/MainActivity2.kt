package com.compose.quotes_jetpack

import Models.favouritQuote
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
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity2 : ComponentActivity() {

    var docid: String = ""
    val reff = Firebase.firestore.collection("favouriteQuotes")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetFromFirestore()
        }
    }

    @Composable
    fun GetFromFirestore() {
        val context = LocalContext.current
        val data = remember { mutableStateOf<List<favouritQuote>>(emptyList()) }
        val opendialog = remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            val reff = Firebase.firestore.collection("favouriteQuotes")
            reff.addSnapshotListener { querysnapshot, FirebaseFireStoreException ->
                FirebaseFireStoreException?.let {
                    Toast.makeText(this@MainActivity2, it.message, Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
                querysnapshot?.let {
                    val datalist = mutableListOf<favouritQuote>()
                    for (i in querysnapshot.documents) {
                        val item = i.toObject<favouritQuote>()
                        item?.let {
                            datalist.add(it)
                        }
                    }
                    data.value = datalist
                }
            }

        }

        LazyColumn {
            items(data.value) {
                Card(
                    elevation = 4.dp,
                    border = BorderStroke(2.dp, Color.Black),
                    modifier = Modifier.padding(16.dp)
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

                            Image(
                                painter = painterResource(R.drawable.baseline_cancel_24),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.Black),
                                modifier = Modifier.padding(start = 280.dp)
                                    .clickable {
                                        opendialog.value = true
                                        val data1 = it.favauthor
                                        val data2 = it.favquote
                                        CoroutineScope(Dispatchers.IO).launch {
                                            try {
                                                val querySnapshot = reff.whereEqualTo("favauthor", data1)
                                                    .whereEqualTo("favquote", data2).get().await()
                                                if (querySnapshot.documents.isNotEmpty()) {
                                                    for (i in querySnapshot.documents) {
                                                        docid = i.id
                                                    }


                                                }

                                            } catch (e: Exception) {
                                                withContext(Dispatchers.Main) {
                                                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                                }
                                            }

                                        }


                                    }
                            )
                        }
                    }
                }
            }
        }
        if (opendialog.value) {
            AlertDialog(
                onDismissRequest = { opendialog.value = false },
                title = {
                    Text(text = "Quotes App", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                },
                text = {
                    Text(
                        text = "Do you want to remove the quote from favourites",
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        reff.addSnapshotListener { querySnapshot, FirebaseFireStoreException ->
                            FirebaseFireStoreException?.let {
                                Toast.makeText(this@MainActivity2, it.message, Toast.LENGTH_LONG).show()
                                return@addSnapshotListener
                            }
                            querySnapshot?.let {
                                CoroutineScope(Dispatchers.IO).launch {
                                    reff.document(docid).delete().await()
                                    opendialog.value = false
                                }
                            }
                        }

                    }) {
                        Text(text = "Remove Quote", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        opendialog.value = false
                    }) {
                        Text(text = "Cancel", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                    }
                }
            )
        }

    }
}





