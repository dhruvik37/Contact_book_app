package com.example.database

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlin.collections.ArrayList


class Class1(var homepage: Homepage,var userlist: ArrayList<Userdata>) :
    BaseAdapter() {

        var  a_to_z=ArrayList<Userdata>()

    override fun getCount(): Int {
        return userlist.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(posiotion: Int, p1: View?, p2: ViewGroup?): View {

        var listt=ArrayList<String>()
        listt.clear()
        for (i in 0 until userlist.size){
            listt.add(userlist[i].name)
        }
        listt.sort()

        a_to_z.clear()

        for (i in 0 until userlist.size){

            for (i1 in 0 until userlist.size){

                if (listt[i]==userlist[i1].name){

                    a_to_z.add(userlist[i1])
                }
            }
        }

        var list_name: TextView
        var list_number: TextView
        var popup: ImageView

        var view = LayoutInflater.from(homepage).inflate(R.layout.number_list, p2, false)

        list_name = view.findViewById(R.id.name)
        list_number = view.findViewById(R.id.number)

        list_name.setText(a_to_z[posiotion].name)
        list_number.setText(a_to_z[posiotion].number)

        popup = view.findViewById(R.id.popup)


        popup.setOnClickListener {

            var pop = PopupMenu(homepage, popup)

            pop.menuInflater.inflate(R.menu.mymenu, pop.menu)

            pop.setOnMenuItemClickListener {

                when (it.itemId) {
                    R.id.edit -> {


                        var dialogbox =Dialog(homepage)
                        dialogbox.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                        dialogbox.setContentView(R.layout.edit_popoup)

                        lateinit var nam: EditText
                        lateinit var num: EditText
                        lateinit var update: Button


                        nam=dialogbox.findViewById(R.id.nam)
                        num=dialogbox.findViewById(R.id.num)
                        update=dialogbox.findViewById(R.id.update)


                        var nam2 = userlist.get(posiotion).name
                        var num2 = userlist.get(posiotion).number

                        nam.setText(nam2)
                        num.setText(num2)

                        update.setOnClickListener {

                            var data=MyDatabase(homepage)
                            data.edit_name_and_number(nam2,nam.text.toString(),num.text.toString())

                            Homepage.viewdatat(userlist,homepage)

                            nam2= nam.text.toString()
                            dialogbox.dismiss()
                        }


                        dialogbox.show()

                    }

                    R.id.deletee -> {

                        var dialog=Dialog(homepage)
                        dialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                        dialog.setContentView(R.layout.delete_popup)

                        lateinit var nam: MaterialAutoCompleteTextView
                        lateinit var num: MaterialAutoCompleteTextView
                        lateinit var delete: Button

                        nam =dialog.findViewById(R.id.nam)
                        num=dialog.findViewById(R.id.num)
                        delete=dialog.findViewById(R.id.delete)

                        var nam2=userlist.get(posiotion).name
                        var num2=userlist.get(posiotion).number

                        nam.setText(nam2)
                        num.setText(num2)

                        delete.setOnClickListener {

                            var data=MyDatabase(homepage)
                            data.delete_number(num.text.toString())

                            userlist.removeAt(posiotion)
                            notifyDataSetChanged()

                            dialog.dismiss()
                        }

                        dialog.show()

                    }
                    R.id.share->{

                        var pop = PopupMenu(homepage, popup)

                        pop.menuInflater.inflate(R.menu.mymenu1, pop.menu)

                        pop.setOnMenuItemClickListener {

                            when(it.itemId){

                                R.id.sharee->{

                                    val sendIntent :Intent=Intent().apply {
                                        action=Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT,userlist.get(posiotion).number)
                                        type="text/plain"
                                    }
                                    var shareIntent=Intent.createChooser(sendIntent,null)
                                    homepage.startActivity(shareIntent)
                                }
                                R.id.share_on_WhatsApp->{
                                    val WhatsApp = userlist.get(posiotion).number
                                    val massage=WhatsApp

                                    sendmassge(massage)
                                }
                            }


                            true
                        }
                        pop.show()

                    }
                    R.id.copy->{
                        val textcopy= userlist.get(posiotion).number
                        val clipboardManager = homepage.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData=ClipData.newPlainText("text",textcopy)
                        clipboardManager.setPrimaryClip(clipData)
                        Toast.makeText(homepage,"Text copied to clipboard",Toast.LENGTH_SHORT).show()
                    }
                }

                true
            }
            pop.show()

        }

        return view

    }

    private fun sendmassge(massage: String) {

        val intent= Intent(Intent.ACTION_SEND)

        intent.type="text/plain"

        intent.setPackage("com.whatsapp")

        intent.putExtra(Intent.EXTRA_TEXT,massage)

        if (intent.resolveActivity(homepage.packageManager)==null){
            Toast.makeText(homepage,"Please install whatsapp first.",Toast.LENGTH_SHORT).show()
            return
        }
        homepage.startActivity(intent)
    }

}
