package com.wangweijun.myapplication.web;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangweijun.myapplication.R;
import com.wangweijun.myapplication.Test;
import com.wangweijun.myapplication.UtilsKt;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test1);
        // https://demo.codeseasy.com/downloads/CodesEasy.pdf
        String pdf_url = getIntent().getStringExtra("pdf_url");
        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+pdf_url);
        webView.loadUrl(pdf_url);

        /**
         * String googleDocs = "https://docs.google.com/viewer?url=";
         * String pdf_url = "http://www.somedomain.com/new.pdf";
         *
         * webView.loadUrl(googleDocs + pdf_url);
         */
    }

    public boolean launchPDF(WebView view, String url) {
        /*if ( urlIsPDF(url)){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "application/pdf");
            try{
                view.getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                //user does not have a pdf viewer installed
            }
        } else {
            webview.loadUrl(url);
        }*/
        return true;
    }
}
