package com.example.loginui

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper2(context: Context): SQLiteOpenHelper(context, dbname, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("create table Products(id integer primary key," +
                " name varchar(30), description varchar(50), qty integer); ")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       /* db?.execSQL("DROP TABLE IF EXISTS products")
        onCreate(db) */
    }
    fun insertProducts(id: Int, name:String, description:String, qty: Int){
        val db:SQLiteDatabase= this.writableDatabase
        val values: ContentValues = ContentValues()
        values.put("id",id)
        values.put("name",name)
        values.put("description",description)
        values.put("qty",qty)
        db.insert("Products",null,values)
        db.close()
    }
fun viewProducts(): MutableList<Product>
{
    var list : MutableList<Product> = ArrayList()
    val db=writableDatabase
    val query = "Select * from Products"
    val cursor=db.rawQuery(query, null)
    if(cursor.moveToFirst())
    {
        do {
            var product=Product()
            product.pId=cursor.getString(0).toInt()
            product.PName=cursor.getString(1)
            product.pDesc=cursor.getString(2)
            product.pQty =cursor.getString(3).toInt()
            list.add(product)
        }
        while(cursor.moveToNext())
    }
    cursor.close()
    db.close()
    return list
}

    companion object{
        internal val dbname="Products"
        internal val factory=null
        internal val version=1
    }
}