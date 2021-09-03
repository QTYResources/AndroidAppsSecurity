package net.zenconsult.android;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.zenconsult.android.crypto.Crypto;

public class StoreData {

    private static final String TAG = "StoreData";

    public static long store(Crypto crypto, ContactsDb db, Contact contact) {
        // Prepare values
        ContentValues values = new ContentValues();
        try {
            values.put("FIRSTNAME", crypto.armorEncrypt(contact.getFirstName().getBytes()));
            values.put("LASTNAME", crypto.armorEncrypt(contact.getLastName().getBytes()));
            values.put("EMAIL", crypto.armorEncrypt(contact.getEmail().getBytes()));
            values.put("PHONE", crypto.armorEncrypt(contact.getPhone().getBytes()));
            values.put("ADDRESS1", crypto.armorEncrypt(contact.getAddress1().getBytes()));
            values.put("ADDRESS2ADDRESS2", crypto.armorEncrypt(contact.getAddress2().getBytes()));
        } catch (Exception e) {
            Log.e(TAG, "store=>error: ", e);
        }

        SQLiteDatabase wdb = db.getWritableDatabase();
        return wdb.insert(ContactsDb.TABLE_NAME, null, values);
    }
}
