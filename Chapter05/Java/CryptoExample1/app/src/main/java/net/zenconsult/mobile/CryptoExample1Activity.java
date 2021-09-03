package net.zenconsult.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoExample1Activity extends AppCompatActivity {

    private static final String TAG = "CryptoExample1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String key = "3106166021166038";
        String message = "This is a secret message";
        try {
            byte[] encryptData = encryptData(key, message);
            String decryptStr = decryptData(key, encryptData);
            Log.d(TAG, "onResume=>decryptStr: " + decryptStr);
        } catch (Exception e) {
            Log.e(TAG, "onResume=>error: ", e);
        }
    }

    private byte[] encryptData(String key, String data)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES/CBC/ZeroBytePadding");
        c.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(key.getBytes()));
        return c.doFinal(data.getBytes());
    }

    private String decryptData(String key, byte[] data)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
        Cipher c = Cipher.getInstance("AES/CBC/ZeroBytePadding");
        c.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(key.getBytes()));
        return new String(c.doFinal(data));
    }
}