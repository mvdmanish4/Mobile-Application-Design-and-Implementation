package com.porject.cmu.mobileapplicationdesignandimplementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String website = intent.getStringExtra("webURL");

        webview =(WebView)findViewById(R.id.webView);

        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(WebViewActivity.this, null,
                        "Page is Loading...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                Toast.makeText(WebViewActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(website);
    }
}
