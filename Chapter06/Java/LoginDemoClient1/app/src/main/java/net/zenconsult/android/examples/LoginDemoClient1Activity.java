package net.zenconsult.android.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.Arrays;

public class LoginDemoClient1Activity extends AppCompatActivity {

    private static final String TAG = "LoginDemoClient";

    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login).setOnClickListener(v -> {
            String u = ((EditText) findViewById(R.id.username)).getText().toString();
            String p = ((EditText) findViewById(R.id.password)).getText().toString();

            login = new Login(u, p);

            String msg = "";
            EditText text = findViewById(R.id.editText1);
            text.setText(msg);

            new Thread(() -> {
                String result = "Response is null";
                try {
                    result = login.execute();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception in reading from stream.");
                    result = "Response is null";
                }
                final String message = result;
                runOnUiThread(() -> {
                    text.setText(message);
                });
            }).start();
        });
    }
}