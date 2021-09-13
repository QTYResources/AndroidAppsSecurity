package net.zenconsult.android.examples

import java.io.*
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class Login(
    var username: String,
    var password: String
) {

    fun execute(): String? {
        var result: String? = null
        val url = URL("http://www.amazingrace.cn/logindemo")
        val connection = url.openConnection() as HttpURLConnection

        connection.apply {
            requestMethod = "POST"
            doOutput = true
            doInput = true
            useCaches = false
        }

        connection.connect()

        val body = "username=$username&password=$password"
        val writer = BufferedWriter(OutputStreamWriter(connection.outputStream))
        writer.write(body)
        writer.flush()
        writer.close()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            var sb = StringBuilder()
            var reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
            reader.close()
            result = sb.toString()
        } else {
            result = "Status code other than HTTP 200 received"
        }
        connection.disconnect()
        return result
    }
}