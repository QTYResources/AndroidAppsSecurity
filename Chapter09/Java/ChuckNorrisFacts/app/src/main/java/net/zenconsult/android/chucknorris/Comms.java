package net.zenconsult.android.chucknorris;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class Comms {

    private static final String TAG = Comms.class.getSimpleName();

    private static final String url = "http://www.chucknorrisfacts.com/";

    private final DefaultHttpClient client;

    public Comms() {
        client = new DefaultHttpClient();
    }

    public String get() {
        InputStream pageStream = doGetAsInputStream(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        String pageText = "";
        Vector<String> quotes = new Vector<>();
        try {
            db = dbFactory.newDocumentBuilder();
            doc = db.parse(pageStream);
            NodeList nl = doc.getElementsByTagName("div");
            for (int x = 0; x < nl.getLength(); ++x) {
                Node node = nl.item(x);
                NamedNodeMap attributes = node.getAttributes();
                for (int y = 0; y < attributes.getLength(); ++y) {
                    if (attributes.getNamedItem("class") != null) {
                        Node attribute = attributes.getNamedItem("class");
                        if (attribute.getNodeValue()
                                .equals("views-field-title")) {
                            NodeList children = node.getChildNodes();
                            for (int z = 0; z < children.getLength(); ++z) {
                                Node child = children.item(z);
                                if (child.getNodeName()
                                        .equalsIgnoreCase("span"))
                                    quotes.add(child.getTextContent());
                            }
                        }
                    }

                }
            }
            Random r = new Random();
            pageText = quotes.get(r.nextInt(quotes.size() - 1));
            pageStream.close();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Log.e(TAG, "get=>error: ", e);
        }
        return pageText;
    }

    private String doGetAsString(String url) {
        HttpGet request = new HttpGet(url);
        String result = "";
        try {
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                Log.e(TAG, "doGetAsString=>Non 200 Status Code " + code);
            }
        } catch (IOException e) {
            Log.e(TAG, "doGetAsString=>error: ", e);
        }
        return result;

    }

    private InputStream doGetAsInputStream(String url) {
        HttpGet request = new HttpGet(url);
        InputStream result = null;
        try {
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                result = response.getEntity().getContent();
            } else {
                Log.e(TAG, "doGetAsInputStream=>Non 200 Status Code " + code);
            }
        } catch (IOException e) {
            Log.e(TAG, "doGetAsInputStream=>error: ", e);
        }
        return result;

    }
}
