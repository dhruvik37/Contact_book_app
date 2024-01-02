package com.example.database

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Splash : AppCompatActivity() {

    companion object{
        lateinit var share:SharedPreferences
        lateinit var edit:SharedPreferences.Editor
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        share=getSharedPreferences("login", MODE_PRIVATE)
        edit= share.edit()

        Handler(Looper.getMainLooper()).postDelayed({

            if (share.getBoolean("status",false)){
                startActivity(Intent(this@Splash,Homepage::class.java))
                finish()
            }else{
                startActivity(Intent(this@Splash,MainActivity::class.java))
                finish()
            }

        },1500)
    }
}