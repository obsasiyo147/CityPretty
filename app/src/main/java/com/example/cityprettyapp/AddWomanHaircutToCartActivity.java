package com.example.cityprettyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AddWomanHaircutToCartActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mProgress;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        mWebView = (WebView) findViewById(R.id.mWebView);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mWebView.setVisibility(View.GONE);
                mProgress.setVisibility(View.VISIBLE);
                if(url.equals("http://citypretty.com")) {
                    Toast.makeText(AddWomanHaircutToCartActivity.this, "Payment is canceled", Toast.LENGTH_SHORT);
                    finish();
                }

                else {
                    Toast.makeText(AddWomanHaircutToCartActivity.this, "Payment complete", Toast.LENGTH_SHORT);

                }

            }



            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.setVisibility(View.VISIBLE);
                mProgress.setVisibility(View.GONE);
            }
        });

        mWebView.loadUrl("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=A9LJRMWS76D24");
        startActivity(new Intent(getApplicationContext(), PayPalActivity.class));

    }
}
