package net.zenconsult.android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.zenconsult.android.crypto.Crypto;

public class RetrieveData {

    private static final String TAG = "RetrieveData";

    public static Contact get(Crypto crypto, ContactsDb db) {
        SQLiteDatabase rdb = db.getReadableDatabase();
        String[] cols = { "FIRSTNAME", "LASTNAME", "EMAIL", "PHONE" };
        Cursor results = rdb.query(ContactsDb.TABLE_NAME, cols, "", null, "", "", "");

        Contact c = new Contact();
        results.moveToFirst();

        try {
            c.setFirstName(crypto.armorDecrypt(results.getString(results.getColumnIndex("FIRSTNAME"))));
            c.setLastName(crypto.armorDecrypt(results.getString(results.getColumnIndex("LASTNAME"))));
            c.setEmail(crypto.armorDecrypt(results.getString(results.getColumnIndex("EMAIL"))));
            c.setPhone(crypto.armorDecrypt(results.getString(results.getColumnIndex("PHONE"))));
        } catch (Exception e) {
            Log.e(TAG, "get=>error: ", e);
        }
        return c;
    }
}
