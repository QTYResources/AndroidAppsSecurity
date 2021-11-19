package net.zenconsult.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ChallengeResponseClientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            Thread {
                val c = Comms(this)
                val challenge = c.getChallenge()
                val cram = CRAM(this)
                val hash = cram.generate(challenge)
                val reply = c.sendResponse(hash)
                runOnUiThread {
                    if (c.authorized(reply)) {
                        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }.start()
        }
    }
}