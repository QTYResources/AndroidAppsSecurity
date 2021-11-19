package net.zenconsult.android

import android.util.Log
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import java.lang.Exception

class DataFetcher(
    private val token: Token
) {

    private val httpClient = DefaultHttpClient();

    public fun fetchAlbums(userId: String) {
        val url = "https://picasaweb.google.com/data/feed/api/user/$userId"
        try {
            val resp = httpClient.execute(buildGet(token.getAccessToken(), url))
            if (resp.statusLine.statusCode == 200) {
                val line = EntityUtils.toString(resp.entity)
                // Do your XML Parsing here
            }
        } catch (e: Exception) {
            Log.e(TAG, "fetchAlbums=>error: ", e)
        }
    }

    private fun buildGet(accessToken: String, url: String): HttpGet {
        val get = HttpGet(url)
        get.addHeader("Authorization", "Bearer " + accessToken)
        return get
    }

    companion object {
        const val TAG = "DataFetcher"
    }
}