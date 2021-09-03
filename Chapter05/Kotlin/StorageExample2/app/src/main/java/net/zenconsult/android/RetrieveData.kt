package net.zenconsult.android

import android.content.Context
import java.io.ByteArrayOutputStream

class RetrieveData {

    companion object {

        const val file = "contacts"

        fun get(context: Context): ByteArray {
            var bytesRead = 0
            val fis = context.openFileInput(file)
            val bos = ByteArrayOutputStream()
            val b = ByteArray(1024)
            bytesRead = fis.read(b)
            while (bytesRead != -1) {
                bos.write(b, 0, bytesRead)
                bytesRead = fis.read(b)
            }
            return bos.toByteArray()
        }

    }
}