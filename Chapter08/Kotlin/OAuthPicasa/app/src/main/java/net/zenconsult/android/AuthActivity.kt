package net.zenconsult.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import org.apache.http.message.BasicNameValuePair
import java.lang.Exception
import java.net.URI

class AuthActivity : AppCompatActivity() {

    private val clientId = BasicNameValuePair("client_id", "200744748489.apps.googleusercontent.com")
    private val clientSecret = BasicNameValuePair("client_secret", "edxCTl_L8_SFl1rz2klZ4DbB")
    private val redirectURI = BasicNameValuePair("redirect_uri", "urn:ietf:wg:oauth:2.0:oob")
    private val scope = "scope=https://picasaweb.google.com/data/"
    private val oAuth = "https://accounts.google.com/o/oauth2/auth?"
    private val httpReqPost = "https://accounts.google.com/o/oauth2/token"
    private val FILENAME = ".oauth_settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        doAuth()
    }

    private fun doAuth() {
        try {
            val uri = URI("$oAuth$clientId&$redirectURI&$scope&response_type=code")
            val wv = findViewById<WebView>(R.id.webview)
            wv.webChromeClient = ClientHandler(this)
            wv.webViewClient = MWebClient()
            wv.settings.javaScriptEnabled = true
            wv.loadUrl(uri.toASCIIString())
            Log.v(TAG, "Calling ${uri.toASCIIString()}")
        } catch (e: Exception) {
            Log.e(TAG, "doAuth=>error: ", e)
        }
    }

    companion object {
        const val TAG = "AuthActivity"
    }
}