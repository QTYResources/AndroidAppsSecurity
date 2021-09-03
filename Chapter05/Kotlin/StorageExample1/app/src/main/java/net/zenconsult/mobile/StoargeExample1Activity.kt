package net.zenconsult.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import java.util.*

class StoargeExample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val data = Hashtable<String, Any?>()
        data["hostname"] = "smtp.gmail.com"
        data["port"] = 587
        data["ssl"] = true

        if (StoreData.storeData(data, this)) {
            Log.i(TAG, "onResume=>Successfully wrote data")
        } else {
            Log.e(TAG, "onResume=>Failed to write data to Shared Prefs")
        }

        val ed = findViewById<EditText>(R.id.editText1)
        ed.setText(RetrieveData.get(this).toString())
    }

    companion object {
        const val TAG = "StoargeExample1Activity"
    }
}