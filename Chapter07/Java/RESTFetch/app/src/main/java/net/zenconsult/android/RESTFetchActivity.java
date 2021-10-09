package net.zenconsult.android;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RESTFetchActivity extends ListActivity {

    private static final String TAG = "RESTFetchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
        });

        ResetFetchTask task = new ResetFetchTask(this);
        task.execute();
    }

    private class ResetFetchTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private RESTFetchActivity activity;

        public ResetFetchTask(RESTFetchActivity activity) {
            this.activity = activity;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
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

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();

                    InputSource input = new InputSource();
                    input.setCharacterStream(new StringReader(sb.toString()));
                    Document doc = db.parse(input);

                    NodeList nodes = doc.getElementsByTagName("user");
                    ArrayList<String> names = new ArrayList<>();
                    int i = 0;
                    while (i < nodes.getLength()) {
                        names.add(nodes.item(i).getAttributes().getNamedItem("name").getNodeValue());
                        i++;
                    }
                    return names;
                }
            } catch (Exception e) {
                Log.e(TAG, "doInBackground=>error: ", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            if (result == null) {
                Log.e(TAG, "onPostExecute=>result is null.");
                return;
            }
            if (activity != null) {
                activity.setListAdapter(new ArrayAdapter<String>(activity, R.layout.list_item, result));
            }
        }
    }
}