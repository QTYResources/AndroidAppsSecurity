package net.zenconsult.android.chucknorris

import android.util.Log
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import java.io.InputStream
import java.lang.Exception
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class Comms {

    private val client = DefaultHttpClient()

    public fun get(): String {
        var pageText = ""
        val pageStream = doGetAsInputStream(REQUEST_URL)
        pageStream?.let {
            val dbFactory = DocumentBuilderFactory.newInstance()
            val quotes = Vector<String>()
            try {
                val db = dbFactory.newDocumentBuilder()
                val doc = db.parse(it)
                val nl = doc.getElementsByTagName("div")
                for (x in 0 until nl.length) {
                    val node = nl.item(x)
                    for (y in 0 until node.attributes.length) {
                        if (node.attributes.getNamedItem("class") != null) {
                            val attribute = node.attributes.getNamedItem("class")
                            if (attribute.nodeValue.equals("views-field-title")) {
                                for (z in 0 until node.childNodes.length) {
                                    val child = node.childNodes.item(z)
                                    if (child.nodeName.lowercase().equals("span")) {
                                        quotes.add(child.textContent)
                                    }
                                }
                            }
                        }
                    }
                }
                val r = Random()
                pageText = quotes[r.nextInt(quotes.size - 1)]
                pageStream.close()
            } catch (e: Exception) {
                Log.e(TAG, "get=>error: ", e)
            }
        }
        return pageText
    }

    private fun doGetAsString(url: String): String {
        val request = HttpGet(url)
        var result = ""
        try {
            val response = client.execute(request)
            if (response.statusLine.statusCode == 200) {
                result = EntityUtils.toString(response.entity)
            } else {
                Log.e(TAG, "doGetAsString=>Non 200 Status Code ${response.statusLine.statusCode}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "doGetAsString=>error: ", e)
        }
        return result
    }

    private fun doGetAsInputStream(url: String): InputStream? {
        val request = HttpGet(url)
        var result: InputStream? = null
        try {
            val response = client.execute(request)
            if (response.statusLine.statusCode == 200) {
                result = response.entity.content
            } else {
                Log.e(TAG, "doGetAsInputStream=>Non 200 Status Code ${response.statusLine.statusCode}")
            }
        } catch (e: Exception){
            Log.e(TAG, "doGetAsInputStream=>error: ", e)
        }
        return result
    }

    companion object {
        const val TAG = "Comms"
        const val REQUEST_URL = "http://www.chucknorrisfacts.com/"
    }
}