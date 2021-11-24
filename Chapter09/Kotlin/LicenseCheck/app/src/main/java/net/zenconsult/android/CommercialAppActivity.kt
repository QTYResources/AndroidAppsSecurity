package net.zenconsult.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CommercialAppActivity : AppCompatActivity(), CommsEvent {

    private lateinit var view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(R.id.editText1)

        // Verify license on first run

        // Click Button
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            view.text = "Fetching fact..."
            val c = CommsNotifier(this)
            c.start()
        }
    }

    override fun onTextReceived(text: String) {
        TODO("Not yet implemented")
    }

    companion object {
        const val TAG = "CommercialAppActivity"
    }
}