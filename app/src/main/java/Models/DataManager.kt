package Models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.compose.quotes_jetpack.Pages
import com.google.gson.Gson
import java.nio.charset.Charset


object DataManager {

    var data= emptyArray<QuotesData>()
    var currentpage= mutableStateOf(Pages.QUOTELIST)
    var currentquote:QuotesData?=null
    var isdataloaded= mutableStateOf(false)

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