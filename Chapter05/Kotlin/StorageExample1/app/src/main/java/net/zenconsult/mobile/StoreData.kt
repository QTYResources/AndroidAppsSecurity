package net.zenconsult.mobile

import android.content.Context
import androidx.preference.PreferenceManager
import java.util.*

class StoreData {

    companion object {

        fun storeData(data: Hashtable<String, Any?>, context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val hostname = data["hostname"] as String
            val port = data["port"] as Int
            val useSSL = data["ssl"] as Boolean
            val ed = prefs.edit()
            ed.apply {
                putString("hostname", hostname)
                putInt("port", port)
                putBoolean("ssl", useSSL)
            }
            return ed.commit()
        }

    }
}