package net.zenconsult.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import net.zenconsult.android.controller.SaveController;
import net.zenconsult.android.crypto.Crypto;
import net.zenconsult.android.model.Contact;
import net.zenconsult.android.model.Location;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Contact contact = new Contact();
        contact.setFirstName("Sheran");
        contact.setLastName("Gunasekera");
        contact.setAddress1("");
        contact.setAddress2("");
        contact.setEmail("sheran@zenconsult.net");
        contact.setPhone("12120031337");
        byte[] key = Crypto.generateKey("randomtext".getBytes());
        SaveController.saveContact(this, contact, key);
        byte[] data = SaveController.readContact(this, contact.getFirstName(), key);
        if (data != null) {
            String str = new String(data);
            Log.d(TAG, "onResume=>real string: " + str);
        } else {
            Log.e(TAG, "onResume=>data is null.");
        }
        Location location = new Location();
        location.setIdentifier("北京");
        location.setLatitude(116.2);
        location.setLongitude(39.56);
        SaveController.saveLocation(this, location, key);
        byte[] locationData = SaveController.readLocation(this, location.getIdentifier(), key);
        if (locationData != null) {
            String locationStr = new String(locationData);
            Log.d(TAG, "onResume=>Location: " + locationStr);
        } else {
            Log.e(TAG, "onResume=>Data is null.");
        }
    }
}