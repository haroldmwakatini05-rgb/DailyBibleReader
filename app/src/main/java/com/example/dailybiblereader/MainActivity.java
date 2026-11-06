package com.example.dailybiblereader;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.net.Uri;

import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(
            Color.rgb(23, 59, 50)
        );

        webView = new WebView(this);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setDomStorageEnabled(true);

        WebViewAssetLoader assetLoader =
            new WebViewAssetLoader.Builder()
                .addPathHandler(
                    "/assets/",
                    new WebViewAssetLoader.AssetsPathHandler(this)
                )
                .build();

        webView.setWebViewClient(
            new WebViewClientCompat() {

                @Override
                public WebResourceResponse shouldInterceptRequest(
                    WebView view,
                    WebResourceRequest request
                ) {
                    return assetLoader.shouldInterceptRequest(
                        request.getUrl()
                    );
                }

                @Override
                public WebResourceResponse shouldInterceptRequest(
                    WebView view,
                    String url
                ) {
                    return assetLoader.shouldInterceptRequest(
                        Uri.parse(url)
                    );
                }
            }
        );

        setContentView(webView);

        webView.loadUrl(
            "https://appassets.androidplatform.net/assets/www/index.html"
        );
    }
}0
