package net.zenconsult.android

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.net.URI

class OAuth(
    private val act: Activity
) {

    private val clientId = BasicNameValuePair("client_id", "200744748489.apps.googleusercontent.com")
    private val clientSecret = BasicNameValuePair("client_secret", "edxCTl_L8_SFl1rz2klZ4DbB")
    private val redirectURI = BasicNameValuePair("redirect_uri", "urn:ietf:wg:oauth:2.0:oob")
    private val httpReqPost = "https://accounts.google.com/o/oauth2/token"
    private var token = readToken()

    public fun readToken(): Token {
        var token = Token()
        var fis: FileInputStream? = null
        try {
            fis = act.openFileInput(FILENAME)
            fis?.let {
                val ois = ObjectInputStream(BufferedInputStream(it))
                token = ois.readObject() as Token
                if (token == null) {
                    token = Token()
                    writeToken(token)
                }
                ois.close()
            }
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "readToken=>error: ", e)
            writeToken(Token())
        } catch (e: Exception) {
            Log.e(TAG, "readToken=>error: ", e)
        } finally {
            fis?.close()
        }
        return token
    }

    public fun writeToken(token: Token) {
        try {
            val f = File(FILENAME)
            if (f.exists()) {
                f.delete()
            }
            val fos = act.openFileOutput(FILENAME, Context.MODE_PRIVATE)

            val out = ObjectOutputStream(BufferedOutputStream(fos))
            out.writeObject(token)
            out.close()
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "Error creating settings file")
        } catch (e: Exception) {
            Log.e(TAG, "writeToken=>error: ", e)
        }
    }

    public fun getRequestToken() {
        val httpClient = DefaultHttpClient()
        val post = HttpPost(httpReqPost)
        val nvPairs = ArrayList<NameValuePair>()
        nvPairs.add(clientId)
        nvPairs.add(clientSecret)
        nvPairs.add(BasicNameValuePair("code", token.getAuthCode()))
        nvPairs.add(redirectURI)
        nvPairs.add(BasicNameValuePair("grant_type", "authorization_code"))
        try {
            post.entity = UrlEncodedFormEntity(nvPairs)
            val response = httpClient.execute(post)
            val line = EntityUtils.toString(response.entity)
            val jObj = JSONObject(line)
            token.buildToken(jObj)
            writeToken(token)
        } catch (e: IOException) {
            Log.e(TAG, "getRequestToken=>error: ", e)
            if (e.message =="No peer certificate") {
                Toast.makeText(act, "Possible HTC Error for Android 2.3.3", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getRequestToken=>error: ", e)
        }
    }

    public fun getToken(): Token {
        return token
    }

    public fun setToken(token: Token) {
        this.token = token
    }

    companion object {
        const val TAG = "OAuth"
        const val FILENAME = ".oauth_settings"
    }
}