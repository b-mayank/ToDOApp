package com.example.todoapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.activities.ReadToDo
import com.example.todoapp.activities.UpdateActivity
import com.example.todoapp.database.DbHelper
import com.example.todoapp.models.DailyTask
import kotlinx.android.synthetic.main.single_row_design.view.*

class DailyTaskAdapter(var mContext: Context): RecyclerView.Adapter<DailyTaskAdapter.ViewHolder>() {

    var mList: ArrayList<DailyTask> = ArrayList()
    lateinit var dbHelper: DbHelper

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(dailyTask: DailyTask){

            dbHelper = DbHelper(mContext)
            itemView.text_view_title.text = dailyTask.title
            itemView.button_update.setOnClickListener{
                var intent = Intent(mContext, UpdateActivity::class.java)
                intent.putExtra("id",dailyTask.id)
                intent.putExtra("title",dailyTask.title)
                intent.putExtra("desc",dailyTask.description)
                mContext.startActivity(intent)
            }

            itemView.button_delete.setOnClickListener{

                var id = dailyTask.id

                dbHelper.deleteDailyTask(id)
                mList = dbHelper.getAllDailyTask()
                notifyDataSetChanged()
                Toast.makeText(mContext,"Deleted", Toast.LENGTH_LONG).show()

            }

            itemView.text_view_title.setOnClickListener {
                var intent = Intent(mContext, ReadToDo::class.java)
                intent.putExtra("TITLE",dailyTask.title)
                intent.putExtra("DESCRIPTION",dailyTask.description)
                mContext.startActivity(intent)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.single_row_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dailyTask = mList[position]
        holder.bind(dailyTask)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<DailyTask>){
        mList = list
        notifyDataSetChanged()
    }
}