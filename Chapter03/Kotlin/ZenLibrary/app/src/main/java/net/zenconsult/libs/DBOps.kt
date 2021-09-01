package net.zenconsult.libs

import android.util.Log

class DBOps {

    companion object {
        const val TAG = "DBOps"

        fun purgeDatabase() {
            Log.v(TAG, "Purge DB called");
        }
    }
}