package net.zenconsult.android

import android.content.Context

class StoreData {

    companion object {

        const val file = "contacts"

        fun storeData(data: ByteArray, context: Context) {
            val fos = context.openFileOutput(file, Context.MODE_PRIVATE)
            fos.write(data)
            fos.flush()
            fos.close()
        }
    }
}