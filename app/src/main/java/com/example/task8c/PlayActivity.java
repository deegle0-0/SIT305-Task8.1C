package com.example.task8c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayActivity extends AppCompatActivity {

    WebView webView;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();

        String link2 = intent.getStringExtra("youtube_link");
        String separator ="https://youtu.be/";
        int sepPos = link2.indexOf(separator);
        if (sepPos == -1) {
            Log.v("link string","empty link");
        }
        link= link2.substring(sepPos +separator.length());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                view.loadUrl("javascript:player.playerVideo();");
            }
        });

        webView.loadData(
                "<html>" +
                        "<body>" +
                        " <iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                        + link + "?enabledjsapi=1\" frameborder=\"0\" allowfullscreen>" +
                        " </iframe> </body> </html>",
                "text/html",
                "utf-8");
    }


}