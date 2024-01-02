package com.example.database

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class passeord_deta_get : AppCompatActivity() {

    lateinit var gmail_match: TextInputEditText
    lateinit var number_match: TextInputEditText
    lateinit var next:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.passeord_deta_get)

        gmail_match=findViewById(R.id.gmail_match)
        number_match=findViewById(R.id.number_match)
        next=findViewById(R.id.next)

        next.setOnClickListener {


            var data=MyDatabase(this)
            var cursor_selsectdata:Cursor
            cursor_selsectdata=data.select_account(gmail_match.text.toString(),number_match.text.toString())

            while (cursor_selsectdata.moveToNext()){
                Log.e("$$", "++:")
                var gmail1=cursor_selsectdata.getString(3)
                var number1=cursor_selsectdata.getString(2)
                if (gmail1.contains(gmail_match.text.toString()) && number1.contains(number_match.text.toString())){

                    startActivity(Intent(this@passeord_deta_get,password_forgot_last::class.java).putExtra("oldgmail",gmail1))
                    finish()
                }
                else{
                    Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}