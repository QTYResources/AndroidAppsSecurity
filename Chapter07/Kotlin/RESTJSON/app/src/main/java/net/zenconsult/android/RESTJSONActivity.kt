package net.zenconsult.android

import android.app.ListActivity
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class RESTJSONActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.isTextFilterEnabled = true
        listView.setOnItemClickListener { _, view, _, _ ->
            Toast.makeText(this@RESTJSONActivity, (view as TextView).text, Toast.LENGTH_SHORT)
                .show()
        }

        FetchTask(this).execute()
    }

    private class FetchTask(
        val activity: RESTJSONActivity
    ): AsyncTask<Any?, Any?, ArrayList<String>>() {

        override fun doInBackground(vararg params: Any?): ArrayList<String>? {
            try {
                val url = URL("http://www.amazingrace.cn/apress/members")
                val connection = url.openConnection() as HttpURLConnection

                connection.apply {
                    requestMethod = "GET"
                    doOutput = false
                    doInput = true
                    useCaches = false
                }

                connection.connect()

                Log.d(TAG, "doInBackground=>responseCode: ${connection.responseCode}")
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val sb = StringBuilder()
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    var line = reader.readLine()
                    while (line != null) {
                        sb.append(line)
                        line = reader.readLine()
                    }
                    reader.close()

                    Log.d(TAG, "doInBackground=>response: $sb")

                    val users = JSONObject(sb.toString()).getJSONObject("users")
                    val jArray = users.getJSONArray("user")
                    val names = ArrayList<String>()
                    var i = 0
                    while (i < jArray.length()) {
                        val jsonObject = jArray.getJSONObject(i)
                        names.add(jsonObject.getString("name"))
                        i++
                    }
                    return names
                }
            } catch (e: Exception) {
                Log.e(TAG, "doInBackground=>error: ", e)
            }
            return null
        }

        override fun onPostExecute(result: ArrayList<String>?) {
            super.onPostExecute(result)
            if (result == null) {
                Log.e(TAG, "onPostExecute=>result is null.")
                return;
            }
            result?.let {
                activity.listAdapter = ArrayAdapter<String>(activity, R.layout.list_item, it)
            }
        }

    }

    companion object {
        const val TAG = "RESTJSONActivity"
    }
}