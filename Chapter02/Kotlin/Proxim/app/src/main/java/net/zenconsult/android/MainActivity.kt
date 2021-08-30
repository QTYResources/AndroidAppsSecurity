package net.zenconsult.android

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.zenconsult.android.controller.SaveController
import net.zenconsult.android.crypto.Crypto
import net.zenconsult.android.model.Contact
import net.zenconsult.android.model.Location

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val contact = Contact("Sheran", "Gunasekera", "", "", "sheran@zenconsult.net", "12120031337")
        val key = Crypto.generateKey("randomtext".toByteArray())
        SaveController.saveContact(this, contact, key)
        val contactData = SaveController.readContact(this, contact.firstName!!, key)
        if (contactData != null) {
            var contactStr = String(contactData)
            Log.d(TAG, "onResume=>Contact: $contactStr")
        } else {
            Log.e(TAG, "onResume=>Data is null.")
        }
        val location = Location("北京", 116.2, 39.56)
        SaveController.saveLocation(this, location, key)
        val locationData = SaveController.readLocation(this, location.identifier!!, key)
        if (locationData != null) {
            var locationStr = String(locationData)
            Log.d(TAG, "onResume=>Location: $locationStr")
        } else {
            Log.e(TAG, "onResume=>Data is null.")
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}