package net.zenconsult.android.crypto;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

    private static final String TAG = "Crypto";

    private static final String ENGINE = "AES";
    private static final String CRYPTO = "AES/CBC/PKCS5Padding";

    private Context mContext;

    public Crypto(Context context) {
        mContext = context;
    }

    public byte[] encrypt(byte[] data)
            throws NoSuchPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        return cipher(data, Cipher.ENCRYPT_MODE);
    }

    public byte[] decrypt(byte[] data)
            throws NoSuchPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {
        return cipher(data, Cipher.DECRYPT_MODE);
    }

    public String armorEncrypt(byte[] data)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        return Base64.encodeToString(encrypt(data), Base64.DEFAULT);
    }

    public String armorDecrypt(String data)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        return new String(decrypt(Base64.decode(data, Base64.DEFAULT)));
    }

    private byte[] cipher(byte[] data, int mode)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        KeyManager km = new KeyManager(mContext);
        SecretKeySpec sks = new SecretKeySpec(km.getId(), ENGINE);
        IvParameterSpec iv = new IvParameterSpec(km.getIv());
        Cipher c = Cipher.getInstance(CRYPTO);
        c.init(mode, sks, iv);
        return c.doFinal(data);
    }
}
