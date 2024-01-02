package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class password_forgot_last : AppCompatActivity() {

    lateinit var New_password: TextInputEditText
    lateinit var confirm_password: TextInputEditText
    lateinit var Reset_Password: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_forgot_last)

        New_password = findViewById(R.id.New_password)
        confirm_password = findViewById(R.id.confirm_password)
        Reset_Password = findViewById(R.id.Reset_Password)

        var old_gmial = intent.getStringExtra("oldgmail")

        Reset_Password.setOnClickListener {
            if (New_password.text.toString().equals(confirm_password.text.toString())) {
                var data = MyDatabase(this)
                data.updatepassword(New_password.text.toString(), old_gmial)
                startActivity(Intent(this@password_forgot_last, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

}