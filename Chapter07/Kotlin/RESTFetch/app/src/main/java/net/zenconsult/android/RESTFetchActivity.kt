package net.zenconsult.android

import android.annotation.SuppressLint
import android.app.ListActivity
import android.database.Cursor
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import org.xml.sax.InputSource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class RESTFetchActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.isTextFilterEnabled = true
        listView.setOnItemClickListener { _, view, _, _ ->
            Toast.makeText(this, (view as TextView).text, Toast.LENGTH_SHORT).show()
        }

        val fetchTask = ResetFetchTask(this)
        fetchTask.execute(null)
    }

    private class ResetFetchTask(
        @SuppressLint("StaticFieldLeak") val activity: RESTFetchActivity
    ) : AsyncTask<Any?, Any?, ArrayList<String>?>() {

        override fun doInBackground(vararg params: Any?): ArrayList<String>? {
            val url = URL("http://www.amazingrace.cn/apress/members")
            val connection = url.openConnection() as HttpURLConnection

            connection.apply {
                requestMethod = "GET"
                doOutput = false
                doInput = true
                useCaches = false
            }

            connection.connect()

            Log.e(TAG, "doInBackground=>responseCode: ${connection.responseCode}")
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val sb = StringBuilder()
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var line = reader.readLine()
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
                reader.close()

                Log.e(TAG, "doInBackground=>response: ${sb.toString()}")

                val dbf = DocumentBuilderFactory.newInstance()
                val db = dbf.newDocumentBuilder()

                val input = InputSource()
                input.characterStream = StringReader(sb.toString())
                val doc = db.parse(input)

                val nodes = doc.getElementsByTagName("user")
                var names = ArrayList<String>()
                var i = 0
                while (i < nodes.length) {
                    names.add(nodes.item(i).attributes.getNamedItem("name").nodeValue)
                    i++
                }
                return names
            } else {
                return null
            }
        }

        override fun onPostExecute(result: ArrayList<String>?) {
            super.onPostExecute(result)
            if (result == null) {
                Log.e(TAG, "onPostExecute=>result is null.")
            }
            activity.listAdapter = ArrayAdapter(activity, R.layout.list_item, result!!.toArray())
        }

    }

    companion object {
        const val TAG = "RESTFetchActivity"
    }
}