package com.shancreation.sliatelms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebActivity extends AppCompatActivity {
private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();

        String URL = bundle.getString("url");

        setContentView(R.layout.activity_web);


String uri ="https://docs.google.com/gview?embedded=true&url=";
        wb = findViewById(R.id.wb_view);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setAllowFileAccess(true);
        try {
            String url = URLEncoder.encode(URL,"UTF-8");
            wb.loadUrl(uri+url);

        }catch (UnsupportedEncodingException e){

            e.printStackTrace();

        }

       // wb.loadData(url,"text/html","UTF-8");

    }
}