package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText


class save_Contacts : AppCompatActivity() {

    lateinit var contact_name_add : TextInputEditText
    lateinit var contact_number_add : TextInputEditText
    lateinit var contact_save : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.save_contacts)

        contact_name_add=findViewById(R.id.contact_name_add)
        contact_number_add=findViewById(R.id.contact_number_add)
        contact_save=findViewById(R.id.contact_save)

        var id = Splash.share.getInt("id",0)

        contact_save.setOnClickListener {

            var data = MyDatabase(this)
            data.contectdata(id,contact_name_add.text.toString(),contact_number_add.text.toString())

            startActivity(Intent(this@save_Contacts,Homepage::class.java))

            finish()

        }
    }
}