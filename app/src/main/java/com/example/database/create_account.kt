package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class create_account : AppCompatActivity() {

    lateinit var create_to_login_page :Button

    lateinit var create_name : TextInputEditText
    lateinit var create_number : TextInputEditText
    lateinit var create_email : TextInputEditText
    lateinit var create_password : TextInputEditText
    lateinit var create_account : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)

        create_to_login_page=findViewById(R.id.create_to_login_page)

        create_account = findViewById(R.id.create_account)

        create_name = findViewById(R.id.create_name)
        create_number = findViewById(R.id.create_number)
        create_email = findViewById(R.id.create_email)
        create_password = findViewById(R.id.create_password)

        create_account.setOnClickListener {

            var data = MyDatabase(this)
            data.insertdata(create_name.text.toString(),create_number.text.toString(),create_email.text.toString(),create_password.text.toString())

            var login=Intent(this@create_account,MainActivity::class.java)
            startActivity(login)
            finish()

        }

        create_to_login_page.setOnClickListener {

            var login=Intent(this@create_account,MainActivity::class.java)
            startActivity(login)
            finish()

        }

    }
}