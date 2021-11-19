package net.zenconsult.android

import android.app.Activity
import android.widget.Toast
import org.apache.http.NameValuePair
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

class Comms(
    private val activity: Activity
) {

    private val url = "http://192.168.3.117:8080/ChallengeResponse/login"
    private val client = DefaultHttpClient()

    public fun sendResponse(hash: String): String {
        val params = ArrayList<NameValuePair>()
        params.add(BasicNameValuePair("challenge", hash))
        val paramString = URLEncodedUtils.format(params, "utf-8")
        val cUrl = "$url?$paramString"
        return doGetAsString(cUrl)
    }

    public fun authorized(response: String): Boolean {
        val inputStream = ByteArrayInputStream(response.toByteArray())
        val dbFactory = DocumentBuilderFactory.newInstance()
        val db = dbFactory.newDocumentBuilder()
        val doc = db.parse(inputStream)
        val nl = doc.getElementsByTagName("Response")
        val reply = nl.item(0).textContent
        inputStream.close()
        return reply.matches(Regex("Authorized"))
    }

    public fun getChallenge(): String {
        val challengeText = doGetAsInputStream(url) ?: return ""
        val dbFactory = DocumentBuilderFactory.newInstance()
        val db = dbFactory.newDocumentBuilder()
        val doc = db.parse(challengeText)
        val nl = doc.getElementsByTagName("Challenge")
        val challenge = nl.item(0).textContent
        challengeText.close()
        return challenge
    }

    public fun doGetAsString(url: String): String {
        var result = ""
        val request = HttpGet(url)
        val response = client.execute(request)
        val code = response.statusLine.statusCode
        if (code == 200) {
            result = EntityUtils.toString(response.entity)
        } else {
            Toast.makeText(activity, "Status Code $code", Toast.LENGTH_SHORT).show()
        }
        return result
    }

    public fun doGetAsInputStream(url: String): InputStream? {
        var result: InputStream? = null
        val request = HttpGet(url)
        val response = client.execute(request)
        val code = response.statusLine.statusCode
        if (code == 200) {
            result = response.entity.content
        } else {
            Toast.makeText(activity, "Status Code $code", Toast.LENGTH_SHORT).show()
        }
        return result
    }
}