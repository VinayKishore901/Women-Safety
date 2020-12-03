package com.example.ws

import android.R.attr.key
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {

    lateinit var btn_submit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        loaddata()
        btn_submit = findViewById<Button>(R.id.sub)
        btn_submit.setOnClickListener(View.OnClickListener {

            var number1 = etv1.text.toString()
            var number2 =etv2.text.toString()
            var number3 = etv3.text.toString()


            val mPrefs = getSharedPreferences("numbers", 0)
            val editor = mPrefs.edit()
            editor.putString("number1",number1 )
            editor.putString("number2",number2 )
            editor.putString("number3",number3 )
            editor.commit()
            Toast.makeText(this , "Data Saved", Toast.LENGTH_SHORT).show()
        })



    }
    private fun loaddata(){
        val sharedPreference = getSharedPreferences("numbers",0)
        val number1 = sharedPreference.getString("number1","0")
        val number2 = sharedPreference.getString("number2","0")
        val number3 = sharedPreference.getString("number3","0")

        etv1.hint = number1
        etv2.hint =number2
        etv3.hint = number3


    }
}