package com.example.royxatdanotish.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.royxatdanotish.Mudels.User

class DbHelper2(val context: Context): SQLiteOpenHelper(context, DB_NAME, null, VERSION), DbHelper {

    companion object{
        const val DB_NAME = "my_images_db"
        const val TABLE = "images_table"
        const val VERSION = 1
        const val ID = "id"
        const val NAME = "name"
        const val IMAGE_URI = "image_uri"
        const val PHONE = "phone"
        const val COUNTRY = "country"
        const val MANZIL = "manzil"
        const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $IMAGE_URI text not null, $PHONE text not null, $COUNTRY text not null, $MANZIL text not null, $PASSWORD text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun getAll(): ArrayList<User> {
        val list = ArrayList<User>()
        val dataBase = this.readableDatabase
        val cursor = dataBase.rawQuery("select * from $TABLE", null)
        if (cursor.moveToFirst()){
            do {
                list.add(User(cursor.getInt(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(2)))
            }while (cursor.moveToNext())
        }

        return list
    }

    override fun addAll(user: User) {
        val database =this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, user.nameSourname)
        contentValues.put(IMAGE_URI, user.image)
        contentValues.put(PHONE, user.phone)
        contentValues.put(PASSWORD, user.password)
        contentValues.put(COUNTRY, user.country)
        contentValues.put(MANZIL, user.manzil)
        database.insert(TABLE, null, contentValues)
        database.close()
    }

}