package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.DailyTask
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        dbHelper = DbHelper(this)

        var title = intent.getStringExtra("title")
        var desc = intent.getStringExtra("desc")

        init()
    }

    private fun init(){
        button_create.setOnClickListener {
            var id = intent.getStringExtra("id").toString()

            var title = edit_text_title.text.toString()
            var description = edit_text_desc.text.toString()
            var dailyTask = DailyTask(id, title, description)

            dbHelper.updateDailyTask(dailyTask)

            Toast.makeText(applicationContext,"Updated", Toast.LENGTH_LONG).show()

            startActivity(Intent(this@UpdateActivity,MainActivity::class.java))
            finish()

        }
    }
}