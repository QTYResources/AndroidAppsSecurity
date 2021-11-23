package net.zenconsult.android.chucknorris

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.vending.licensing.AESObfuscator
import com.google.android.vending.licensing.LicenseChecker
import com.google.android.vending.licensing.LicenseCheckerCallback
import com.google.android.vending.licensing.ServerManagedPolicy
import java.util.*

class ChuckNorrisFactsActivity : AppCompatActivity(), CommsEvent {

    private lateinit var button: Button
    private lateinit var view: TextView
    private lateinit var event: CommsEvent
    private lateinit var lcb: LicCallBack;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        event = this

        view = findViewById(R.id.editText1)
        button = findViewById(R.id.button1)

        button.setOnClickListener {
            // Do License Check before allowing click

            // Generate a Unique ID
            val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            val serialId = Build.SERIAL
            val uuid = UUID(deviceId.hashCode().toLong(), serialId.hashCode().toLong())
            val identify = uuid.toString()

            // Create an Obfuscator and a Policy
            val obf = AESObfuscator(SALT, packageName, identify)
            val policy = ServerManagedPolicy(applicationContext, obf)

            // Create the LicenseChecker
            val check = LicenseChecker(applicationContext, policy, PUB_KEY)

            // Do the license check
            lcb = LicCallBack(this)
            check.checkAccess(lcb)
        }

    }

    override fun onTextReceived(text: String) {
        runOnUiThread {
            setProgressBarIndeterminateVisibility(false)
            view.text = text
            button.isEnabled = true
        }
    }

    class LicCallBack(
        private val activity: ChuckNorrisFactsActivity
    ): LicenseCheckerCallback {

        override fun allow(reason: Int) {
            if (activity.isFinishing) {
                return
            }
            Toast.makeText(activity, "Licensed!", Toast.LENGTH_LONG).show()
            activity.button.isEnabled = false
            activity.setProgressBarIndeterminateVisibility(true)
            activity.view.text = "Fetching fact..."
            val c = CommsNotifier(activity.event)
            c.start()
        }

        override fun dontAllow(reason: Int) {
            if (activity.isFinishing) {
                return
            }
            Toast.makeText(activity, "Unlicensed!", Toast.LENGTH_LONG).show()
        }

        override fun applicationError(errorCode: Int) {
            Log.e(TAG, "applicationError=>errorCode: $errorCode")
        }

        companion object {
            const val TAG = "LicCallBack"
        }

    }

    companion object {
        const val TAG = "ChuckNorrisFactsActivity"
        const val PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlXLnY54Y62odQOcizrYgGuTz1f0OYCnSqv5FUX475uCkLZBCr+9OMZkiW/koxw/ujIpNNyu+AgcP7fTla64ylGKQ2o7IUmzxzJDAitN+/uxdbVqXu6LhvxHjggSDI+g8QYs4LO2lLqyeFddfpS/EkOoFD7aQ0GRZzgyY6eW4dwZ3BML9jXKtj6T37BlgPDv5SjK8chECMOc7IpIh/K6TYX28X9kyyiUK7UWtuaUl99iD9Qyisfwp+8xZlQDNPclWbwZz+SojsNjs9Yh3ISUOFcF/BqxZbiMWhRFj9lLwo+xiTXaNErMspjc4O/vNOuHV9mwAm+ire+c7Fpv6vuSpIwIDAQAB"
        val SALT = byteArrayOf(-118, -112, 38, 124, 15,
            -121, 59, 93, 35, -55, 14, -15, -52, 67, -53, 54, 111, -28, -87, 12)
    }
}