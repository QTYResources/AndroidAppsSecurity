package net.zenconsult.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDb extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Contacts";

    public ContactsDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE " + TABLE_NAME
                + " ( FIRSTNAME TEXT, LASTNAME TEXT, EMAIL TEXT,"
                + " PHONE TEXT, ADDRESS1 TEXT, ADDRESS2 TEXT);";
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
