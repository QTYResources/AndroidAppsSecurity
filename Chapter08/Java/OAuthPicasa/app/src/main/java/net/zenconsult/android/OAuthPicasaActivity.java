package net.zenconsult.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OAuthPicasaActivity extends ListActivity {

    protected OAuthPicasaActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = this;
        new Thread(() -> {
            OAuth o = new OAuth(this);
            Token t = o.getToken();

            if (!t.isValidForReq()) {
                Intent intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
            }

            if (t.isExpired()) {
                o.getRequestToken();
            }

            DataFetcher df = new DataFetcher(t);
            df.fetchAlbums("sheranapress");
            String[] names = new String[] {};   // Add bridge code here to parse XML from DataFetcher and populate your List

            runOnUiThread(() -> {
                setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));

                ListView lv = getListView();
                lv.setTextFilterEnabled(true);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }).start();

    }
}