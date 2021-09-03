package net.zenconsult.android;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RetrieveData {

    private static final String TAG = "RetrieveData";

    public static final String FILE = "contacts";

    public static byte[] get(Context context) {
        byte[] data = null;
        try {
            int bytesRead = 0;
            FileInputStream fis = context.openFileInput(FILE);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while ((bytesRead = fis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            data = bos.toByteArray();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "get=>error: ", e);
        } catch (IOException e) {
           Log.e(TAG, "get=>error: ", e);
        }
        return data;
    }
}
