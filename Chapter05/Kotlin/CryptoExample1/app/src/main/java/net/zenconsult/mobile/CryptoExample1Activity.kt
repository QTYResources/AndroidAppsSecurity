package net.zenconsult.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoExample1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val key = "3106166021166038"
        val message = "This is a secret message"
        val encryptData = encryptData(key, message)
        val decryptStr = decryptData(key, encryptData)
        Log.d(TAG, "onResume=>decryptStr: $decryptStr")
    }

    fun encryptData(key: String, data: String): ByteArray {
        val sks = SecretKeySpec(key.toByteArray(), "AES")
        Log.d(TAG, "encryptData=>sks: ${sks.toString()}")
        val c = Cipher.getInstance("AES/CBC/ZeroBytePadding")
        c.init(Cipher.ENCRYPT_MODE, sks, IvParameterSpec(key.toByteArray()))
        return c.doFinal(data.toByteArray())
    }

    fun decryptData(key: String, data: ByteArray): String {
        val sks = SecretKeySpec(key.toByteArray(), "AES")
        Log.d(TAG, "decryptData=>sks: ${sks.toString()}")
        val c = Cipher.getInstance("AES/CBC/ZeroBytePadding")
        c.init(Cipher.DECRYPT_MODE, sks, IvParameterSpec(key.toByteArray()))
        return String(c.doFinal(data))
    }

    companion object {
        const val TAG = "CryptoExample1Activity"
    }
}