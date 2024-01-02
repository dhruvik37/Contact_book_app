package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class edit_number : AppCompatActivity() {

    lateinit var nam:EditText
    lateinit var num:EditText
    lateinit var update:Button
    lateinit var delete:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_number)

        nam=findViewById(R.id.nam)
        num=findViewById(R.id.num)
        update=findViewById(R.id.update)
        delete=findViewById(R.id.delete)

        var nam2=intent.getStringExtra("contect_name")
        var num2=intent.getStringExtra("contect_number")

        nam.setText(nam2)
        num.setText(num2)

        update.setOnClickListener {
            var data=MyDatabase(this)
            data.edit_name_and_number(nam2,nam.text.toString(),num.text.toString())
            nam2= nam.text.toString()

            startActivity(Intent(this@edit_number,Homepage::class.java))
            finish()
        }

        delete.setOnClickListener {

            var data=MyDatabase(this)
            data.delete_number(num.text.toString())
            startActivity(Intent(this@edit_number,Homepage::class.java))
            finish()
        }

    }
}