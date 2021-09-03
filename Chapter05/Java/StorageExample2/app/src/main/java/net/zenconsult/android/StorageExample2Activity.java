package net.zenconsult.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class StorageExample2Activity extends AppCompatActivity {

    private static final String TAG = "StorageExample2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Store data
        Contact contact = new Contact();
        contact.setFirstName("Sheran");
        contact.setLastName("Gunasekera");
        contact.setEmail("sheran@zenconsult.net");
        contact.setPhone("+12120031337");

        StoreData.storeData(contact.getBytes(), this);

        // Retrieve data
        EditText et = findViewById(R.id.editText1);
        et.setText(new String(RetrieveData.get(this)));
    }
}