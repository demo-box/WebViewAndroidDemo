package com.bitibiti.webviewandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView webView;
    Button localBtn;
    Button remoteBtn;
    Button callJSBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBtn = findViewById(R.id.localBtn);
        remoteBtn = findViewById(R.id.remoteBtn);
        callJSBtn = findViewById(R.id.callJSBtn);
        localBtn.setOnClickListener(this);
        remoteBtn.setOnClickListener(this);
        callJSBtn.setOnClickListener(this);
        callJSBtn.setVisibility(View.GONE);
        webView = findViewById(R.id.webview);
        // 启用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // 向WebView提供原生方法
        webView.addJavascriptInterface(new JSBridge(MainActivity.this), "nativeObject");

        if (BuildConfig.DEBUG) {
            // 允许使用chrome调试
            webView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.localBtn:
                webView.loadUrl("file:///android_asset/index.html");
                callJSBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.remoteBtn:
                webView.loadUrl("https://beilunyang.github.io");
                callJSBtn.setVisibility(View.GONE);
                break;
            case R.id.callJSBtn:
                webView.loadUrl("javascript:callback('主动调用js代码成功')");
        }
    }
}
