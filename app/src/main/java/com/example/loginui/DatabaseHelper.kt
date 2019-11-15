package com.example.loginui

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

class DatabaseHelper(context: Context):SQLiteOpenHelper(context, dbname,factory,version ) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user(id integer primary key autoincrement, name varchar(30), email varchar(50), " +
                "password varchar(20)); ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
fun insertUserData(name:String, email:String ,password:String){
val db: SQLiteDatabase=writableDatabase
    val values: ContentValues = ContentValues()
    values .put("name",name)
    values.put("email",email)
    values.put("password",password)

    db.insert("user",null,values)
    db.close()
}
    fun userPresent(email:String,password: String):Boolean{
        val db=writableDatabase
        val query="Select * from user where email= '$email' and password= '$password' "
        val cursor=db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        else
        cursor.close()
        return true
    }
    companion object {
            internal const val dbname="userDB"
            internal val factory=null
            internal const val version=1
    }
}