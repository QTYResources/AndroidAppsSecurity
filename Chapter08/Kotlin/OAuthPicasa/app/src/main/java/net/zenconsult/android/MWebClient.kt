package net.zenconsult.android

import android.webkit.WebView
import android.webkit.WebViewClient

class MWebClient: WebViewClient() {

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }
}