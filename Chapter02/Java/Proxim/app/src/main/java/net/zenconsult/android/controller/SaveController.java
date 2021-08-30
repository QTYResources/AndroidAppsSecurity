package net.zenconsult.android.controller;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import net.zenconsult.android.crypto.Crypto;
import net.zenconsult.android.model.Contact;
import net.zenconsult.android.model.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SaveController {

    private static final String TAG = "SaveController";

    public static void saveContact(Context context, Contact contact, byte[] key) {
        if (isReadWrite()) {
            try {
                File outputFile = new File(context.getExternalFilesDir(null), contact.getFirstName());
                Log.d(TAG, "saveContact=>path: " + outputFile.getAbsolutePath());
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                byte[] data = encrypt(key, contact.getBytes());
                Log.d(TAG, "saveLocation=>length: " + data.length);
                outputStream.write(data);
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File not found");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception");
            }
        } else {
            Log.e(TAG, "Error opening media card in read/write mode!");
        }
    }

    public static byte[] readContact(Context context, String fileName, byte[] key) {
        byte[] data = null;
        if (isReadWrite()) {
            try {
                File outputFile = new File(context.getExternalFilesDir(null), fileName);
                Log.d(TAG, "saveContact=>path: " + outputFile.getAbsolutePath());
                FileInputStream inputStream = new FileInputStream(outputFile);
                byte[] encryptdata = new byte[inputStream.available()];
                Log.d(TAG, "readContact=>length: " + encryptdata.length);
                inputStream.read(encryptdata);
                data = dencrypt(key, encryptdata);
                inputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File not found");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception");
            }
        } else {
            Log.e(TAG, "Error opening media card in read/write mode!");
        }
        return data;
    }

    public static void saveLocation(Context context, Location location, byte[] key) {
        if (isReadWrite()) {
            try {
                File outputFile = new File(context.getExternalFilesDir(null), location.getIdentifier());
                Log.d(TAG, "saveLocation=>path: " + outputFile.getAbsolutePath());
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                byte[] data = encrypt(key, location.getBytes());
                Log.d(TAG, "saveLocation=>length: " + data.length);
                outputStream.write(data);
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File not found");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception");
            }
        } else {
            Log.e(TAG, "Error opening media card in read/write mode!");
        }
    }

    public static byte[] readLocation(Context context, String fileName, byte[] key) {
        byte[] data = null;
        if (isReadWrite()) {
            try {
                File outputFile = new File(context.getExternalFilesDir(null), fileName);
                Log.d(TAG, "saveContact=>path: " + outputFile.getAbsolutePath());
                FileInputStream inputStream = new FileInputStream(outputFile);
                byte[] encryptdata = new byte[inputStream.available()];
                Log.d(TAG, "readContact=>length: " + encryptdata.length);
                inputStream.read(encryptdata);
                data = dencrypt(key, encryptdata);
                inputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "File not found");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception");
            }
        } else {
            Log.e(TAG, "Error opening media card in read/write mode!");
        }
        return data;
    }

    private static boolean isReadOnly() {
        Log.e(TAG, Environment.getExternalStorageState());
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState());
    }

    private static boolean isReadWrite() {
        Log.e(TAG, Environment.getExternalStorageState());
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private static byte[] encrypt(byte[] key, byte[] data) {
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher;
        byte[] ciphertext = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
            ciphertext = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            Log.e(TAG, "NoSucuPaddingException");
        } catch (IllegalBlockSizeException e) {
            Log.e(TAG, "IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            Log.d(TAG, "BadPaddingException");
        } catch (InvalidKeyException e) {
            Log.d(TAG, "InvalidKeyException");
        }
        return ciphertext;
    }

    private static byte[] dencrypt(byte[] key, byte[] data) {
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher;
        byte[] ciphertext = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            ciphertext = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            Log.e(TAG, "NoSucuPaddingException");
        } catch (IllegalBlockSizeException e) {
            Log.e(TAG, "IllegalBlockSizeException");
        } catch (BadPaddingException e) {
            Log.d(TAG, "BadPaddingException");
        } catch (InvalidKeyException e) {
            Log.d(TAG, "InvalidKeyException");
        }
        return ciphertext;
    }
}
