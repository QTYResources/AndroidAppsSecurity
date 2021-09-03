package net.zenconsult.android.crypto

import android.content.Context
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Crypto(
    val mContext: Context
) {

    fun encrypt(data: ByteArray): ByteArray {
        return cipher(data, Cipher.ENCRYPT_MODE)
    }

    fun decrypt(data: ByteArray): ByteArray {
        return cipher(data, Cipher.DECRYPT_MODE)
    }

    fun armorEncrypt(data: ByteArray): String {
        return Base64.encodeToString(encrypt(data), Base64.DEFAULT)
    }

    fun armorDecrypt(data: String): String {
        return String(decrypt(Base64.decode(data, Base64.DEFAULT)))
    }

    private fun cipher(data: ByteArray, mode: Int): ByteArray {
        val km = KeyManager(mContext)
        val sks = SecretKeySpec(km.getId(), ENGINE)
        val iv = IvParameterSpec(km.getIv())
        val c = Cipher.getInstance(CRYPTO)
        c.init(mode, sks, iv)
        return c.doFinal(data)
    }

    companion object {
        const val ENGINE = "AES"
        const val CRYPTO = "AES/CBC/PKCS5Padding"
    }
}