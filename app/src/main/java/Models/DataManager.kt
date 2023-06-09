package Models

import Screens.QuoteMain
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.compose.quotes_jetpack.Pages
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.nio.charset.Charset


object DataManager {

    var data= emptyArray<QuotesData>()
    var currentpage= mutableStateOf(Pages.QUOTELIST)
    var currentquote:QuotesData?=null
    var isdataloaded= mutableStateOf(false)
    var reff=Firebase.firestore.collection("favouriteQuotes")

    fun loadassetfromfile(context: Context){

        val inputStream=context.assets.open("quotes.json")
        val size=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json=String(buffer, Charsets.UTF_8)
        val gson=Gson()
        data=gson.fromJson(json,Array<QuotesData>::class.java)
        isdataloaded.value=true
    }

    fun SwitchPages(quote:QuotesData?){
        if(currentpage.value==Pages.QUOTELIST){
            currentquote=quote
            currentpage.value=Pages.QUOTEDETAIL
        }
        else{
            currentpage.value=Pages.QUOTELIST
        }
    }





}