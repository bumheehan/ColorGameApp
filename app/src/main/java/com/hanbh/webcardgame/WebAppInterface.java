package com.hanbh.webcardgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;

public class WebAppInterface {
    MainActivity mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = (MainActivity)c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void sendRecord(int mm, int ss, int ms){

    }
    @JavascriptInterface
    public void clos(){
        mContext.finish();
    }

    @JavascriptInterface
    public void vib(){
        mContext.vibrator.vibrate(100);
    }
}