package net.zenconsult.android

import android.content.ContentValues
import net.zenconsult.android.crypto.Crypto

class StoreData {

    companion object {

        fun store(crypto: Crypto, db: ContactsDb, contact: Contact): Long {
            val values = ContentValues()
            values.apply {
                put("FIRSTNAME", crypto.armorEncrypt(contact.firstName.toByteArray()))
                put("LASTNAME", crypto.armorEncrypt(contact.lastName.toByteArray()))
                put("EMAIL", crypto.armorEncrypt(contact.email.toByteArray()))
                put("PHONE", crypto.armorEncrypt(contact.phone.toByteArray()))
                put("ADDRESS1", crypto.armorEncrypt(contact.address1.toByteArray()))
                put("ADDRESS2", crypto.armorEncrypt(contact.address2.toByteArray()))
            }
            return db.writableDatabase.insert(ContactsDb.TABLE_NAME, null, values)
        }
    }
}