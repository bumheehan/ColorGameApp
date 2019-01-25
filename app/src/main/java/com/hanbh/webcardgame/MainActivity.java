package com.hanbh.webcardgame;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView myWebView;
    Vibrator vibrator;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);


        myWebView = findViewById(R.id.webview);
        //link 내 웹뷰로 설정
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        myWebView.loadUrl(" file:///android_asset/colorgame/color3.html ");

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        //android(java) - html(js) 연결
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        //디버깅
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override
    public void onBackPressed()
    {
        long tempTime        = System.currentTimeMillis();
        long intervalTime    = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "Press Back to exit.", Toast.LENGTH_SHORT).show();
        }
    }


}
