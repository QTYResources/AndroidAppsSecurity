package net.zenconsult.android

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactsDb(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createSQL = "CREATE TABLE " + TABLE_NAME +
                " ( FIRSTNAME TEXT, LASTNAME TEXT, EMAIL TEXT," +
                " PHONE TEXT, ADDRESS1 TEXT, ADDRESS2 TEXT);";
        db?.execSQL(createSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        const val TABLE_NAME = "Contacts"
    }
}