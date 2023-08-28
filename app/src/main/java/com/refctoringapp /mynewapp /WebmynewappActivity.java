package com.refctoringapp.mynewapp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebmynewappActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_mynewapp);
        WebView refctoringapp_web = findViewById(R.id.web_mynewapp_view);
        refctoringapp_web.setWebViewClient(new WebViewClient());
        refctoringapp_web.getSettings().setJavaScriptEnabled(true);
        refctoringapp_web.getSettings().setUseWideViewPort(true);
        refctoringapp_web.getSettings().setLoadWithOverviewMode(true);
        refctoringapp_web.getSettings().setDomStorageEnabled(true);
        refctoringapp_web.getSettings().setPluginState(WebSettings.PluginState.ON);
        refctoringapp_web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        refctoringapp_web.loadUrl("https://www.facebook.com/");
    }
}