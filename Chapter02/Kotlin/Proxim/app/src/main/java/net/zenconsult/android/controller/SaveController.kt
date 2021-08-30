package net.zenconsult.android.controller

import android.content.Context
import android.os.Binder
import android.os.Environment
import android.util.Log
import net.zenconsult.android.model.Contact
import net.zenconsult.android.model.Location
import java.io.*
import java.lang.Exception
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class SaveController {

    companion object {
        private const val TAG = "SaveController"

        public fun saveContact(context: Context, contact: Contact, key: ByteArray) {
            if (isReadWrite()) {
                try {
                    val outputFile = File(context.getExternalFilesDir(null), contact.firstName)
                    val outputStream = FileOutputStream(outputFile)
                    val data = encrypt(key, contact.getBytes())
                    outputStream.write(data)
                    outputStream.close()
                } catch (e: FileNotFoundException) {
                    Log.e(TAG, "File not found")
                } catch (e: IOException) {
                    Log.e(TAG, "IO Exception")
                }
            } else {
                Log.e(TAG, "Error opening media card in read/write mode!");
            }
        }

        public fun readContact(context: Context, fileName: String, key: ByteArray): ByteArray? {
            var data: ByteArray? = null
            if (isReadWrite()) {
                try {
                    val inputFile = File(context.getExternalFilesDir(null), fileName)
                    val inputStream = FileInputStream(inputFile)
                    val encryptData = ByteArray(inputStream.available())
                    inputStream.read(encryptData)
                    data = decrypt(key, encryptData)
                    inputStream.close()
                } catch (e: FileNotFoundException) {
                    Log.e(TAG, "File not found")
                } catch (e: IOException) {
                    Log.e(TAG, "IO Exception")
                }
            } else {
                Log.e(TAG, "Error opening media card in read/write mode!");
            }
            return data
        }

        public fun saveLocation(context: Context, location: Location, key: ByteArray) {
            if (isReadWrite()) {
                try {
                    val outputFile = File(context.getExternalFilesDir(null), location.identifier)
                    val outputStream = FileOutputStream(outputFile)
                    val data = encrypt(key, location.getBytes())
                    outputStream.write(data)
                    outputStream.close()
                } catch (e: FileNotFoundException) {
                    Log.e(TAG, "File not found")
                } catch (e: IOException) {
                    Log.e(TAG, "IO Exception")
                } catch (e: Exception) {
                    Log.e(TAG, "Other Exception: ", e)
                }
            } else {
                Log.e(TAG, "Error opening media card in read/write mode!");
            }
        }

        public fun readLocation(context: Context, fileName: String, key: ByteArray): ByteArray? {
            var data: ByteArray? = null
            if (isReadWrite()) {
                try {
                    val inputFile = File(context.getExternalFilesDir(null), fileName)
                    val inputStream = FileInputStream(inputFile)
                    val encryptData = ByteArray(inputStream.available())
                    inputStream.read(encryptData)
                    data = decrypt(key, encryptData)
                    inputStream.close()
                } catch (e: FileNotFoundException) {
                    Log.e(TAG, "File not found")
                } catch (e: IOException) {
                    Log.e(TAG, "IO Exception")
                } catch (e: Exception) {
                    Log.e(TAG, "Other Exception: ", e)
                }
            } else {
                Log.e(TAG, "Error opening media card in read/write mode!");
            }
            return data
        }

        private fun isReadOnly(): Boolean {
            Log.d(TAG, Environment.getExternalStorageState())
            return Environment.MEDIA_MOUNTED_READ_ONLY == Environment.getExternalStorageState()
        }

        private fun isReadWrite(): Boolean {
            Log.d(TAG, Environment.getExternalStorageState())
            return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
        }

        private fun encrypt(key: ByteArray, data: ByteArray): ByteArray {
            val sKeySpec = SecretKeySpec(key, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec)
            return cipher.doFinal(data)
        }

        private fun decrypt(key: ByteArray, data: ByteArray): ByteArray {
            val sKeySpec = SecretKeySpec(key, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec)
            return cipher.doFinal(data)
        }
    }
}