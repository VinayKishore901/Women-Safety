package com.example.ws

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    lateinit var button: Button
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
          loaddata()

          button = findViewById<Button>(R.id.save)
          button.setOnClickListener(View.OnClickListener {


               val myprefs = getSharedPreferences("Details",0)
             val editor = myprefs.edit()
             editor.putString("Name", etv_name.text.toString())
              editor.putString("address",etv_ad.text.toString())
              editor.putString("blood",etv_bl.text.toString())
              editor.putString("home",etv_ho.text.toString())
              editor.putString("number",etv_num.text.toString())
              editor.putString("condotion",etv_con.text.toString())
              editor.commit()

             Toast.makeText(this,"SAVED",Toast.LENGTH_SHORT).show()

        })




    }
    private  fun loaddata(){
        val sharedPreference = getSharedPreferences("Details",0)
        val name = sharedPreference.getString("Name","--Na--")
        val address = sharedPreference.getString("address","--Na--")
        val blood = sharedPreference.getString("blood","--Na--")
        val home = sharedPreference.getString("home","--Na--")
        val number = sharedPreference.getString("number","--Na--")
        val condition = sharedPreference.getString("condotion","--Na--")




        etv_name.hint =  name
        etv_ad.hint = address
        etv_bl.hint = blood
        etv_ho.hint = home
        etv_num.hint = number
        etv_con.hint = condition

    }
}