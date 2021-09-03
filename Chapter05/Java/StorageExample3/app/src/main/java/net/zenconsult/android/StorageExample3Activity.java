package net.zenconsult.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import net.zenconsult.android.crypto.Crypto;
import net.zenconsult.android.crypto.KeyManager;

public class StorageExample3Activity extends AppCompatActivity {

    private static final String TAG = "StorageExample3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String key = "12345678909876543212345678909876";
        String iv = "1234567890987654";

        KeyManager km = new KeyManager(this);
        km.setIv(iv.getBytes());
        km.setId(key.getBytes());

        // Store data
        Contact contact = new Contact();
        contact.setFirstName("Sheran");
        contact.setLastName("Gunasekera");
        contact.setEmail("sheran@zenconsult.net");
        contact.setPhone("+12120031337");

        ContactsDb db = new ContactsDb(this, "ContactsDb", null, 1);
        Log.i(TAG, "onResume=>store data: " + String.valueOf(StoreData.store(new Crypto(this), db, contact)));

        Contact c = RetrieveData.get(new Crypto(this), db);
        db.close();

        EditText et = findViewById(R.id.editText1);
        et.setText(c.toString());

    }
}