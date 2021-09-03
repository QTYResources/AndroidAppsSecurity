package net.zenconsult.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class StorageExample2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        // Store data
        val contact = Contact("Sheran", "Gunasekera",
            "", "", "sheran@zenconsult.net", "+12120031337")
        StoreData.storeData(contact.getBytes(), this)

        // Retrieve data
        val et = findViewById<EditText>(R.id.editText1)
        et.setText(String(RetrieveData.get(this)))
    }

}