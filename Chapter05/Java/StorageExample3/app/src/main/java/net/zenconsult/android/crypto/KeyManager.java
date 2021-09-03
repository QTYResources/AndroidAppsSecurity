package net.zenconsult.android.crypto;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KeyManager {

    private static final String TAG = "KeyManager";

    private static final String FILE1 = "id_value";
    private static final String FILE2 = "iv_value";

    private Context mContext;

    public KeyManager(Context context) {
        mContext = context;
    }

    public void setId(byte[] data) {
        writer(data, FILE1);
    }

    public void setIv(byte[] data) {
        writer(data, FILE2);
    }

    public byte[] getId() {
        return reader(FILE1);
    }

    public byte[] getIv() {
        return reader(FILE2);
    }

    private byte[] reader(String file) {
        byte[] data = null;
        FileInputStream fis = null;
        try {
            int bytesRead = 0;
            fis = mContext.openFileInput(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while ((bytesRead = fis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            Log.e(TAG, "reader=>error: ", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ignore) {}
            }
        }
        return data;
    }

    private void writer(byte[] data, String file) {
        FileOutputStream fos = null;
        try {
            fos = mContext.openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            Log.e(TAG, "writer=>error: ", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ignore) {}
            }
        }
    }
}
