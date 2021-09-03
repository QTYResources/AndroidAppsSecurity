package net.zenconsult.mobile

import android.content.Context
import androidx.preference.PreferenceManager
import java.util.*

class RetrieveData {

    companion object {
        fun get(context: Context): Hashtable<String, Any?> {
            val hostname = "hostname"
            val port = "port"
            val ssl = "ssl"

            val data = Hashtable<String, Any?>()
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            data[hostname] = prefs.getString(hostname, null)
            data[port] = prefs.getInt(port, 0)
            data[ssl] = prefs.getBoolean(ssl, true)
            return data
        }
    }

}