package net.zenconsult.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class OAuthPicasaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById<ListView>(android.R.id.list)

        Thread {
            val o = OAuth(this)
            val t = o.getToken()

            if (!t.isValidForReq()) {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }

            if (t.isExpired()) {
                o.getRequestToken()
            }

            val df = DataFetcher(t)
            df.fetchAlbums("sheranapress")
            val names = ArrayList<String>() // Add bridge code here to parse XML from DataFetcher and populate your List

            runOnUiThread {
                lv.adapter = ArrayAdapter<String>(this, R.layout.list_item, names)

                lv.isTextFilterEnabled = true

                lv.setOnItemClickListener { _, view, _, _ ->
                    Toast.makeText(applicationContext, (view as TextView).text, Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}