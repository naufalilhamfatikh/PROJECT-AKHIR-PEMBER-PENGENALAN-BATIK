// UserDAO.kt
package com.example.projectakhirpraktikum

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDAO(context: Context) {
    private var database: SQLiteDatabase
    private var dbHelper: SQLiteOpenHelper

    init {
        dbHelper = object : SQLiteOpenHelper(context, "user_db", null, 2) {
            override fun onCreate(db: SQLiteDatabase) {
                // Membuat tabel user
                val createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, umur TEXT, alamat TEXT, username TEXT, password TEXT)"
                db.execSQL(createTableQuery)
            }

            override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                // Menghapus tabel user jika versi database berubah
                val dropTableQuery = "DROP TABLE IF EXISTS users"
                db.execSQL(dropTableQuery)
                onCreate(db)
            }
        }
        database = dbHelper.writableDatabase
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("name", user.name)
            put("umur", user.umur)
            put("alamat", user.alamat)
            put("username", user.username)
            put("password", user.password)
        }
        database.insert("users", null, values)
    }

    @SuppressLint("Range")
    fun getUserByUsername(username: String): User? {
        val query = "SELECT * FROM users WHERE username = ?"
        val cursor: Cursor = database.rawQuery(query, arrayOf(username))

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val umur = cursor.getString(cursor.getColumnIndex("umur"))
            val alamat = cursor.getString(cursor.getColumnIndex("alamat"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            User(id, name, umur, alamat, username, password)
        } else {
            null
        }.also {
            cursor.close()
        }
    }

    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }
}
