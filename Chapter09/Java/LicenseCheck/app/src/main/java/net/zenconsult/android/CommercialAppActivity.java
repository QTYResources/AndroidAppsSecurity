package net.zenconsult.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CommercialAppActivity extends AppCompatActivity implements CommsEvent {

    private static final String TAG = CommercialAppActivity.class.getSimpleName();

    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.editText1);

        // Verify license on first run

        // Click Button
        final Button button = findViewById(R.id.button1);
        button.setOnClickListener((v) -> {
            view.setText("Fetching fact...");
            CommsNotifier c = new CommsNotifier(this);
            c.start();
        });
    }

    @Override
    public void onTextReceived(String text) {
        runOnUiThread(() -> {
            view.setText(text);
        });
    }
}