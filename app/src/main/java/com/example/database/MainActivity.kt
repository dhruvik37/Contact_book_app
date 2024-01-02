package com.example.database

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CursorAdapter
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var forgot_passeord: Button
    lateinit var sign_up :Button
    lateinit var login :Button
    lateinit var login_email : TextInputEditText
    lateinit var login_Password : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forgot_passeord=findViewById(R.id.forgot_passeord)
        sign_up=findViewById(R.id.singup)
        login=findViewById(R.id.login)
        login_email=findViewById(R.id.login_email)
        login_Password=findViewById(R.id.login_Password)

        sign_up.setOnClickListener {

            var createaccount=Intent(this@MainActivity,create_account::class.java)
            startActivity(createaccount)

            finish()
        }
        login.setOnClickListener {

//            var emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[com]+".toRegex()
//            var passwordpattern="[A-Z]+[a-z9._-]+".toRegex()
//
//            var txt=login_email.text.toString()
//            var pass=login_Password.text.toString()
//
//            if (txt.isEmpty() && pass.isEmpty()){
//                login_email.setError("fill blank")
//                login_Password.setError("fill blank")
//            }
//            else if (txt.matches(emailpattern) && pass.matches(passwordpattern)){

                var data = MyDatabase(this)
                var cursor2 : Cursor
                cursor2 = data.selectdata(login_email.text.toString(),login_Password.text.toString())

                while (cursor2.moveToNext())
                {
                    var id =cursor2.getInt(0)
                    Splash.edit.putInt("id",id)
                    Splash.edit.apply()
                    Splash.edit.putBoolean("status",true)
                    Splash.edit.apply()
                    startActivity(Intent(this@MainActivity,Homepage::class.java))
                    finish()
                }
//            }
//            else{
//                if (!txt.matches(emailpattern)){
//                    login_email.setError("invalid")
//                }else if(!txt.matches(passwordpattern)) {
//                    login_Password.setError("invalid")
//                }
//
//            }

        }
        forgot_passeord.setOnClickListener {

            startActivity(Intent(this@MainActivity,passeord_deta_get::class.java))

        }


    }


}