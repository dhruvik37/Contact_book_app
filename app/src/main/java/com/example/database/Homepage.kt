package com.example.database

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class Homepage : AppCompatActivity() {

    lateinit var add_Contacts: FloatingActionButton
    lateinit var list_item: ListView
    lateinit var logoutuser :ImageView
    lateinit var search: SearchView

    var userlist = ArrayList<Userdata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        search = findViewById(R.id.search)

        Log.e("$$", "++:")


//        var animation: Animation = AnimationUtils.loadAnimation(this, R.anim.blank)
//        animation.duration = 800

        add_Contacts = findViewById(R.id.add_Contacts)
        list_item = findViewById(R.id.list_item)


        userlist.clear()

        viewdatat(userlist, this@Homepage);

        search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {


                return false;
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var searchlist = ArrayList<Userdata>()
                Log.e("______", "onQueryTextSubmit: $searchlist")

               // Toast.makeText(this@Homepage, "+++", Toast.LENGTH_LONG).show()


                for (i in 0..userlist.size - 1) {


                    Log.e("=====", "onQueryTextSubmit: $i")

                    var name = userlist.get(i).name
                    var number = userlist.get(i).number


                    if (name.toString().toLowerCase().contains(newText.toString().toLowerCase())) {


                        searchlist.add(userlist.get(i))

                    }
                    else if  (number.toString().toLowerCase().contains(newText.toString().toLowerCase())){

                        searchlist.add(userlist.get(i))
                    }

                }
                var adapter = Class1(this@Homepage, searchlist)
                list_item.adapter = adapter
               // list_item.animation = animation


                return false

            }

        })
        

        var adapter = Class1(this, userlist)
        list_item.adapter = adapter
        //list_item.animation = animation


        list_item.setOnItemClickListener { adapterView, view, i, l ->
            startActivity(
                Intent(this@Homepage, edit_number::class.java).putExtra(
                    "contect_name",
                    userlist.get(i).name
                ).putExtra("contect_number", userlist.get(i).number)
            )
        }

        var Dailog = Dialog(this)

        add_Contacts.setOnClickListener {

            Dailog.window!!.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            Dailog.setContentView(R.layout.save_contacts)

            lateinit var contact_name_add: TextInputEditText
            lateinit var contact_number_add: TextInputEditText
            lateinit var contact_save: Button

            contact_name_add = Dailog.findViewById(R.id.contact_name_add)
            contact_number_add = Dailog.findViewById(R.id.contact_number_add)
            contact_save = Dailog.findViewById(R.id.contact_save)

            var id = Splash.share.getInt("id", 0)

            contact_save.setOnClickListener {

                var data = MyDatabase(this)
                data.contectdata(
                    id,
                    contact_name_add.text.toString().capitalize(),
                    contact_number_add.text.toString()
                )

                Dailog.dismiss()
                viewdatat(userlist, this)

            }

            Dailog.show()
        }

        logoutuser=findViewById(R.id.logoutuser)

        logoutuser.setOnClickListener {

            var logoutpop = PopupMenu(this,logoutuser)

            logoutpop.menuInflater.inflate(R.menu.mysecondpop,logoutpop.menu)

            logoutpop.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.Profile->{
                        startActivity(Intent(this@Homepage,profile_page::class.java))
                    }
                    R.id.confirm_logout->{

                        var use_logout=Dialog(this)
                        use_logout.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                        use_logout.setContentView(R.layout.user__logout)

                        var oky_logout:Button
                        var No:Button
                        oky_logout=use_logout.findViewById(R.id.oky_logout)
                        No=use_logout.findViewById(R.id.No)

                        oky_logout.setOnClickListener {
                            startActivity(Intent(this@Homepage,MainActivity::class.java))
                            Splash.edit.putBoolean("status",false)
                            Splash.edit.apply()
                            use_logout.dismiss()
                            finish()
                        }
                        No.setOnClickListener {
                            use_logout.dismiss()
                        }
                        use_logout.show()
                    }
                }
                true
            }
            logoutpop.show()
        }

    }

    companion object {
        public fun viewdatat(userlist: ArrayList<Userdata>, homepage: Homepage) {
            userlist.clear()

            var id = Splash.share.getInt("id", 0)
            var data = MyDatabase(homepage)
            var cursor_data: Cursor
            cursor_data = data.selectdata_table(id)

            while (cursor_data.moveToNext()) {

                var name = cursor_data.getString(1)
                var number = cursor_data.getString(2)
                var udata = Userdata(name, number)
                userlist.add(udata)

            }
        }
    }

}