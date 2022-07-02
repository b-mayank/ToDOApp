package com.example.todoapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.todoapp.models.DailyTask

class DbHelper(var mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "mydb"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "daily_task"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("abc", "onCreate")
        val createTable =
            "create table $TABLE_NAME ($COLUMN_ID char(100), $COLUMN_TITLE char(50), $COLUMN_DESCRIPTION char(200))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("abc", "onUpgrade")
        val dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }
    // inset into employee(id , email , name ) values(1,'','')
    fun addDailyTask(dailyTask: DailyTask) {
        var sqLiteDatabase = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COLUMN_ID, dailyTask.id)
        contentValues.put(COLUMN_TITLE, dailyTask.title)
        contentValues.put(COLUMN_DESCRIPTION, dailyTask.description)
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        Log.d("abc", "addEmployee")
    }

    // update employee set name='', email = '' where id = 1

    fun updateDailyTask(dailyTask: DailyTask){
        var sqLiteDatabase = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE,dailyTask.title)
        contentValues.put(COLUMN_DESCRIPTION,dailyTask.description)

        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(dailyTask.id.toString())
        sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs)
        Log.d("abc", "updateDailyTask")
    }

    // delete from employee where id = 1
    fun deleteDailyTask(id: String){
        var sqLiteDatabase = writableDatabase
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(id.toString())
        sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs)
        Log.d("abc", "deleteEmployee")
    }

    // select * from employees
    // select id, name, email from employee
    @SuppressLint("Range")
    fun getAllDailyTask(): ArrayList<DailyTask> {
        var sqLiteDatabase = readableDatabase
        var list: ArrayList<DailyTask> = ArrayList()
        var columns = arrayOf(
            COLUMN_ID,
            COLUMN_TITLE,
            COLUMN_DESCRIPTION
        )
        var cursor = sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                var title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                var description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

                var dailyTask = DailyTask(id, title, description)
                list.add(dailyTask)
            } while (cursor.moveToNext())
        }
        return list
    }

}