package net.zenconsult.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import net.zenconsult.android.crypto.Crypto
import net.zenconsult.android.crypto.KeyManager

class StorageExample3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val key = "12345678909876543212345678909876"
        val iv = "1234567890987654"

        val km = KeyManager(this)
        km.setIv(iv.toByteArray())
        km.setId(key.toByteArray())

        // Store data
        val contact = Contact()
        contact.firstName = "Sheran"
        contact.lastName = "Gunasekera"
        contact.email = "sheran@zenconsult.net"
        contact.phone = "+12120031337"

        val db = ContactsDb(this, "ContactsDb", null, 1)
        Log.i(TAG, StoreData.store(Crypto(this,), db, contact).toString())

        val c = RetrieveData.get(Crypto(this), db)

        db.close()

        val et = findViewById<EditText>(R.id.editText1)
        et.setText(c.toString())
    }

    companion object {
        const val TAG = "StorageExample3Activity"
    }
}