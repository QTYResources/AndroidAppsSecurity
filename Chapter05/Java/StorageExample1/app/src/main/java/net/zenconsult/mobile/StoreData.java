package net.zenconsult.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Hashtable;

public class StoreData {

    public static boolean storeData(Hashtable<String, Object> data, Context ctx) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String hostname = (String) data.get("hostname");
        int port = (Integer) data.get("port");
        boolean useSSL = (Boolean) data.get("ssl");
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("hostname", hostname);
        ed.putInt("port", port);
        ed.putBoolean("ssl", useSSL);
        return ed.commit();
    }

}
