package com.example.whatshappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.LiveFolders.INTENT
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number: String = "0"
        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if (number.isDigitsOnly()) {
            startwhatshapp(number)
        } else {
            Toast.makeText(this, "please check the number", Toast.LENGTH_SHORT).show()
        }
    }
        private fun startwhatshapp(number:String){
            val intent=Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.whatsapp")
           val data:String= if(number[0]=='+') {
            number.substring(1)
            }
            else if(number.length==10){
                "91" + number
            }else{
               number
           }
            intent.data= Uri.parse("https://wa.me/$data")
            if(packageManager.resolveActivity(intent,0)!=null){
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"please install whatshapp",Toast.LENGTH_SHORT).show()
            }
            finish()
        }


}
