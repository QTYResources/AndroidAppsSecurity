package net.zenconsult.android

import net.zenconsult.android.crypto.Crypto

class RetrieveData {

    companion object {

        fun get(crypto: Crypto, db: ContactsDb): Contact {
            val cols = arrayOf( "FIRSTNAME", "LASTNAME", "EMAIL", "PHONE" )
            val result = db.readableDatabase.query(ContactsDb.TABLE_NAME, cols, "", null, "", "", "")

            val c = Contact()
            result.moveToFirst()

            c.firstName = crypto.armorDecrypt(result.getString(result.getColumnIndex("FIRSTNAME")))
            c.lastName = crypto.armorDecrypt(result.getString(result.getColumnIndex("LASTNAME")))
            c.email = crypto.armorDecrypt(result.getString(result.getColumnIndex("EMAIL")))
            c.phone = crypto.armorDecrypt(result.getString(result.getColumnIndex("PHONE")))

            return c
        }
    }
}