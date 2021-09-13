package net.zenconsult.android.examples;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login {

    private static final String TAG = "Login";

    private String username;
    private String password;

    public Login(String user, String pass) {
        username = user;
        password = pass;
    }

    public String execute() throws IOException {
        Log.i(TAG, "execute called");
        String result = null;
        URL url = new URL("http://www.amazingrace.cn/logindemo");
        HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        connection.connect();

        String body = "username=" + username + "&password=" + password;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(body);
        bw.flush();
        bw.close();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            result = sb.toString();
        } else {
            result = "Status code other than HTTP 200 received";
        }
        connection.disconnect();
        return result;
    }
}
