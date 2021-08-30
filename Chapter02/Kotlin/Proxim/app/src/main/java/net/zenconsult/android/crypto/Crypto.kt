package net.zenconsult.android.crypto

import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class Crypto {

    companion object {

        public fun generateKey(randomNumberSeed: ByteArray): ByteArray {
            val keyGen = KeyGenerator.getInstance("AES")
            val random = SecureRandom.getInstance("SHA1PRNG")
            random.setSeed(randomNumberSeed)
            keyGen.init(128, random)
            val sKey = keyGen.generateKey()
            return sKey.encoded
        }

    }
}