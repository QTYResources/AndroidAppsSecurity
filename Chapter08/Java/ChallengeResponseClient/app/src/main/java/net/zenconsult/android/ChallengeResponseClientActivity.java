package net.zenconsult.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChallengeResponseClientActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Activity activity = this;

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener((v) -> {
            new Thread(() -> {
                Comms c = new Comms(activity);
                String challenge = c.getChallenge();
                CRAM cram = new CRAM(activity);
                String hash = cram.generate(challenge);
                String reply = c.sendResponse(hash);
                runOnUiThread(() -> {
                    if (c.authorized(reply)) {
                        Toast toast = Toast.makeText(
                                activity.getApplicationContext(), "Login success",
                                Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(
                                activity.getApplicationContext(), "Login failed",
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }).start();
        });
    }
}