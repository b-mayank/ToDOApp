package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.R
import kotlinx.android.synthetic.main.activity_read_to_do.*

class ReadToDo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_to_do)

        var title = intent.getStringExtra("TITLE")
        var description = intent.getStringExtra("DESCRIPTION")

        text_title.text = title
        text_description.text = description

        init()
    }

    private fun init(){
        button_back.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}