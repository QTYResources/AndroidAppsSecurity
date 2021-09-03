package net.zenconsult.android;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoreData {

    private static final String TAG = "StoreData";

    private static final String FILE = "contacts";

    public static void storeData(byte[] data, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE, Context.MODE_PRIVATE);
            fos.write(data);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "storeData=>error: ", e);
        } catch (IOException e) {
            Log.e(TAG, "storeData=>error: ", e);
        }
    }
}
