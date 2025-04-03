package com.example.btth4_002.theme

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

data class Contact(val id: Int, val name: String, val phone: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "contacts.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS contacts")
        onCreate(db)
    }

    fun insertContact(name: String, phone: String): Boolean {
        val db = writableDatabase
        return try {
            val values = ContentValues().apply {
                put("name", name)
                put("phone", phone)
            }
            val result = db.insert("contacts", null, values)
            result != -1L
        } finally {
            db.close()
        }
    }

    fun getAllContacts(): List<Contact> {
        val list = mutableListOf<Contact>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM contacts", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val phone = cursor.getString(2)
            list.add(Contact(id, name, phone))
        }
        cursor.close()
        db.close()
        return list
    }

    fun deleteContactById(id: Int): Boolean {
        val db = writableDatabase
        return try {
            db.delete("contacts", "id = ?", arrayOf(id.toString())) > 0
        } finally {
            db.close()
        }
    }
}
