package net.zenconsult.android

import android.app.Activity
import android.util.Base64
import android.widget.TextView
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class CRAM(
    private val activity: Activity
) {

    public fun generate(serverChallenge: String): String {
        val algo = "HmacSHA1"
        val pass = activity.findViewById<TextView>(R.id.editText2)
        val server = Base64.decode(serverChallenge, Base64.DEFAULT)

        var mac = Mac.getInstance(algo)
        val keyText = pass.text.toString()
        val key = SecretKeySpec(keyText.toByteArray(), algo)
        mac.init(key)
        val tmpHash = mac.doFinal(server)
        val user = activity.findViewById<TextView>(R.id.editText1)
        val username = user.text.toString()
        val concat = "$username ${Hex.toHex(tmpHash)}"
        return Base64.encodeToString(concat.toByteArray(), Base64.URL_SAFE)
    }

}