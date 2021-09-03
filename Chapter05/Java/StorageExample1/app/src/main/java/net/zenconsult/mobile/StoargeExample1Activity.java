package net.zenconsult.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.Hashtable;

public class StoargeExample1Activity extends AppCompatActivity {

    private static final String TAG = "StoargeExample1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Hashtable<String, Object> data = new Hashtable<>();
        data.put("hostname", "smtp.gmail.com");
        data.put("port", 587);
        data.put("ssl", true);

        if (StoreData.storeData(data, this)) {
            Log.i(TAG, "onResume=>Successfully wrote data");
        } else {
            Log.e(TAG, "onResume=>Failed to write data to Shared Prefs");
        }

        EditText et = findViewById(R.id.editText1);
        et.setText(RetrieveData.get(this).toString());
    }
}