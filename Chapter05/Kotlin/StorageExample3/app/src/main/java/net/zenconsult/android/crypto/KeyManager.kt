package net.zenconsult.android.crypto

import android.content.Context
import java.io.ByteArrayOutputStream

class KeyManager(
    val mContext: Context
) {

    fun setId(data: ByteArray) {
        writer(data, FILE1)
    }

    fun setIv(data: ByteArray) {
        writer(data, FILE2)
    }

    fun getId(): ByteArray {
        return reader(FILE1)
    }

    fun getIv(): ByteArray {
        return reader(FILE2)
    }

    private fun writer(data: ByteArray, file: String) {
        val fos = mContext.openFileOutput(file, Context.MODE_PRIVATE)
        fos.write(data)
        fos.flush()
        fos.close()
    }

    private fun reader(file: String): ByteArray {
        val fis = mContext.openFileInput(file)
        val bos = ByteArrayOutputStream()
        val b = ByteArray(1024)
        var bytesRead = fis.read(b)
        while (bytesRead != -1) {
            bos.write(b, 0, bytesRead)
            bytesRead = fis.read(b)
        }
        return bos.toByteArray()
    }

    companion object {
        const val TAG = "KeyManager"
        const val FILE1 = "id_value"
        const val FILE2 = "iv_value"
    }
}