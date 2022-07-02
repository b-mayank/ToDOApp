package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.DailyTask
import kotlinx.android.synthetic.main.activity_insert_data.*

class InsertData : AppCompatActivity() {


    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

        dbHelper = DbHelper(this)



        init()
    }

    private fun init(){
        button_create.setOnClickListener {
            var id = System.currentTimeMillis().toString()
            var title = edit_text_title.text.toString()
            var description = edit_text_desc.text.toString()
            var dailyTask = DailyTask(id, title, description)

            dbHelper.addDailyTask(dailyTask)

            Toast.makeText(applicationContext,"Created", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@InsertData,MainActivity::class.java))
            finish()

        }
    }
}