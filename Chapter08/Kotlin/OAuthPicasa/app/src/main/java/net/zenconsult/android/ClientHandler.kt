package net.zenconsult.android

import android.app.Activity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast

class ClientHandler(
    private val activity: Activity,
): WebChromeClient() {

    private val oAuth = OAuth(activity)

    override fun onReceivedTitle(view: WebView?, title: String?) {
        var code = ""
        title?.let {
            if (it.contains("Success")) {
                code = title.substring(title.indexOf("=") + 1, it.length)
                setAuthCode(code)
                Log.v(TAG, "onReceivedTitle=>Code is $code")
                oAuth.getRequestToken()
                oAuth.writeToken(oAuth.getToken())
                Toast.makeText(activity.applicationContext, "Authorization Successful", Toast.LENGTH_SHORT).show()
                activity.finish()
            } else if (it.contains("Denied")) {
                code = it.substring(it.indexOf("=") + 1, it.length)
                setAuthCode(code)
                Log.v(TAG, "Denied, error was $code")
                Toast.makeText(activity, "Authorization Failed", Toast.LENGTH_SHORT).show()
                activity.finish()
            }
        }
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    public fun getAuthCode(): String {
        return oAuth.getToken().getAuthCode()
    }

    public fun setAuthCode(authCode: String) {
        oAuth.getToken().setAuthCode(authCode)
    }

    companion object {
        const val TAG = "ClientHandler"
    }
}