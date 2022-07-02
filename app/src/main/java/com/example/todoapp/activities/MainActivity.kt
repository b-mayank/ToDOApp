package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.DailyTaskAdapter
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.DailyTask
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper
    lateinit var dailyTaskAdapter: DailyTaskAdapter
    var mList: ArrayList<DailyTask> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        dbHelper = DbHelper(this)
        init()
    }

    private fun init(){
        button.setOnClickListener {
            var intent = Intent(applicationContext,InsertData::class.java)
            startActivity(intent)
        }

        dailyTaskAdapter = DailyTaskAdapter(this)
        recycler_view.adapter = dailyTaskAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)


        mList = dbHelper.getAllDailyTask()
        dailyTaskAdapter.setData(mList)


    }

    override fun onRestart() {
        super.onRestart()
        mList = dbHelper.getAllDailyTask()
        dailyTaskAdapter.setData(mList)
    }
}