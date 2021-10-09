package net.zenconsult.android;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTJSONActivity extends ListActivity {

    private static final String TAG = "RESTJSONActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
        });

        new AsyncTask<Void, Void, String[]>() {

            @Override
            protected String[] doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://www.amazingrace.cn/apress/members");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    connection.connect();

                    Log.d(TAG, "doInBackground=>responseCode: " + connection.getResponseCode());
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        StringBuilder sb = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        reader.close();

                        Log.d(TAG, "doInBackground=>response: " + sb.toString());

                        JSONObject users = new JSONObject(sb.toString()).getJSONObject("users");
                        JSONArray jArray = users.getJSONArray("user");
                        String[] names = new String[jArray.length()];
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            names[i] = jsonObject.getString("name");
                        }
                        return names;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground=>error: ", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(String[] result) {
                super.onPostExecute(result);
                if (result == null) {
                    Log.e(TAG, "onPostExecute=>result is null.");
                    return;
                }
                setListAdapter(new ArrayAdapter<String>(RESTJSONActivity.this, R.layout.list_item, result));
            }

        }.execute();
    }
}