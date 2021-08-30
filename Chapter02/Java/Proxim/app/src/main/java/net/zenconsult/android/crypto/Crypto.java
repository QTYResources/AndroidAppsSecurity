package net.zenconsult.android.crypto;

import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Crypto {

    private static final String TAG = "Crypto";

    public static byte[] generateKey(byte[] randomNumberSeed) {
        SecretKey sKey = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(randomNumberSeed);
            keyGen.init(128, random);
            sKey = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "No such algorithm exception");
        }
        return sKey.getEncoded();
    }
}
