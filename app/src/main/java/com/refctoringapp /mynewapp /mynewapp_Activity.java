package com.refctoringapp.mynewapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class mynewapp_Activity extends AppCompatActivity {

    private WebView webrefctoringapp;
    LinearLayout layout_refctoringapp;
    Button buttonrefctoringapp_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynewapp);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initView() {
        webrefctoringapp = findViewById(R.id.view_mynewapp_web);
        layout_refctoringapp = findViewById(R.id.layoutconnetion_refctoringapp);

        CookieManager.getInstance().setAcceptCookie(true);
        webrefctoringapp.getSettings().setJavaScriptEnabled(true);
        webrefctoringapp.getSettings().setUseWideViewPort(true);
        webrefctoringapp.getSettings().setLoadWithOverviewMode(true);
        webrefctoringapp.getSettings().setDomStorageEnabled(true);
        webrefctoringapp.getSettings().setPluginState(WebSettings.PluginState.ON);
        webrefctoringapp.setWebChromeClient(new WebChromeClient());
        webrefctoringapp.setVisibility(View.VISIBLE);

        webrefctoringapp.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                String refctoringapp_url = request.getUrl().toString();
                if (!refctoringapp_url.startsWith("http")) {
                    startActivity(new Intent(mynewapp_Activity.this, WebmynewappActivity.class));
                    try {
                        Intent refctoringappintent = new Intent(Intent.ACTION_VIEW);
                        refctoringappintent.setData(Uri.parse(refctoringapp_url));
                        startActivity(refctoringappintent);
                        finish();
                        return;
                    } catch (Exception e) {
                        finish();
                        return;
                    }
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        showrefctoringappView();
    }

    public void validatingrefctoringappInternetConnection() {
        layout_refctoringapp.setVisibility(View.VISIBLE);
        buttonrefctoringapp_retry = findViewById(R.id.refctoringapp_btn);
        buttonrefctoringapp_retry.setOnClickListener(view -> {
            layout_refctoringapp.setVisibility(View.GONE);
            showrefctoringappView();
        });
    }

    protected void showrefctoringappView() {
        if (mynewapp_Utils.isNetworkConnected(this)) {
            webrefctoringapp.loadUrl(mynewapp_Utils.generatemynewappPremiumLink(mynewapp_Activity.this));
        } else {
            validatingrefctoringappInternetConnection();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        webrefctoringapp.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webrefctoringapp.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webrefctoringapp.loadUrl("about:blank");
    }

    @Override
    public void onBackPressed() {
    }

}
