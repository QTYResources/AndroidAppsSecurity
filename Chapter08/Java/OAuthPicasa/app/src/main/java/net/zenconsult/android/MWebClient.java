package net.zenconsult.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MWebClient extends WebViewClient {

    public MWebClient() {}

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }
}
