package com.bitibiti.webviewandroiddemo;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JSBridge {
    private MainActivity ctx;

    public JSBridge(MainActivity ctx) {
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void showToast(final String str, final String callback) {
        Toast.makeText(ctx, str, Toast.LENGTH_LONG).show();

        ctx.webView.post(new Runnable() {
            @Override
            public void run() {
                ctx.webView.loadUrl("javascript:" + callback + "('调用toast<" + str + ">成功');");
            }
        });
    }
}
