package net.zenconsult.android.examples

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginDemoClient1Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.login).setOnClickListener(this)
    }

    companion object {
        const val TAG = "LoginDemo1"
    }

    override fun onClick(v: View?) {
        val u = findViewById<EditText>(R.id.username).text.toString()
        val p = findViewById<EditText>(R.id.password).text.toString()

        val login = Login(u, p)

        Thread(Runnable {
            val response = login.execute()
            runOnUiThread(Runnable {
                if (response == null) {
                    findViewById<EditText>(R.id.editText1).setText("Response is null")
                } else {
                    findViewById<EditText>(R.id.editText1).setText(response)
                }
            })
        }).start()
    }
}