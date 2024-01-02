package com.example.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabase(context: Context) : SQLiteOpenHelper(context,"mydata.db",null ,1) {
    override fun onCreate(p0: SQLiteDatabase) {

        var table = "CREATE TABLE mydataaa (id INTEGER PRIMARY Key AUTOINCREMENT, NAME text UNIQUE , NUMBER text UNIQUE,EMAIL text UNIQUE,PASSWORD text UNIQUE )"
        p0.execSQL(table)

        var contact ="CREATE TABLE My_contact (id INTEGER,CONTACT_NAME text UNIQUE,CONTACT_NUMBER text UNIQUE )"
        p0.execSQL(contact)

    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
  }
    fun insertdata(name: String, number: String, email: String, password: String) {

        var insert = "INSERT INTO mydataaa (NAME,NUMBER,EMAIL,PASSWORD) VALUES ('$name','$number','$email','$password')"
        try {
            writableDatabase.execSQL(insert)

        }catch (e : Exception)
        {
            Log.e("===", "insertdata:not$e ", )

        }

    }
    fun selectdata(login_email: String, login_Password: String) : Cursor{

        var select = "SELECT * FROM mydataaa WHERE EMAIL = '$login_email' AND PASSWORD = '$login_Password'"

        var cursor2 : Cursor

            cursor2= readableDatabase.rawQuery(select,null)

        return  cursor2
    }
    fun contectdata(id: Int, contact_name: String, contact_number: String) {

        var contact_insert="INSERT INTO My_contact (id,CONTACT_NAME,CONTACT_NUMBER) VALUES ('$id','$contact_name','$contact_number')"
        try {
            writableDatabase.execSQL(contact_insert)

        }catch (e : Exception)
        {
        }
    }
    fun selectdata_table(email_Id: Int) :Cursor {

        var select_data="SELECT * FROM My_contact WHERE id='$email_Id'"

        var cursor_data : Cursor

        cursor_data=writableDatabase.rawQuery(select_data,null)

        return cursor_data
    }
    fun edit_name_and_number(old_nam: String?, new_name: String, new_number: String) {

        var update="update My_contact set CONTACT_NAME='$new_name' , CONTACT_NUMBER='$new_number' where CONTACT_NAME='$old_nam'"

        writableDatabase.execSQL(update)

    }
    fun delete_number(nummmm: String) {

        var delete="delete from My_contact where CONTACT_NUMBER='$nummmm'"

        try {
            writableDatabase.execSQL(delete)

        }catch (e:Exception) {

        }
    }

    fun select_account(gmail: String, number: String) :Cursor {

        var data_select="SELECT * FROM mydataaa WHERE EMAIL='$gmail' AND NUMBER='$number'"

        var data_change :Cursor
        data_change=readableDatabase.rawQuery(data_select,null)

        return data_change
    }

    fun updatepassword(new_password: String, old_gmial: String?) {

        var update="UPDATE mydataaa SET PASSWORD ='$new_password' where EMAIL='$old_gmial'"
        writableDatabase.execSQL(update)
    }
}
