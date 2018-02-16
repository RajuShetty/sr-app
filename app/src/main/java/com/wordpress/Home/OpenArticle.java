package com.wordpress.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wordpress.R;

/**
 * Created by wail babou on 2016-12-24.
 */

public class OpenArticle  extends AppCompatActivity{
    TextView title;
    WebView web;
    ImageView image;
    FloatingActionButton share,pc;
    Intent data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_article);
        title= (TextView) findViewById(R.id.title);
        web= (WebView) findViewById(R.id.web);
        image= (ImageView) findViewById(R.id.imageheader);
        data = getIntent();
        Glide.with(this)
                .load(data.getStringExtra("logo"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .fitCenter()
                .skipMemoryCache(true)
                .into(image);
        title.setText(data.getStringExtra("title"));
        //web.getSettings().setJavaScriptEnabled(true);
        web.loadDataWithBaseURL("",data.getStringExtra("content"), "text/html", "UTF-8", "");
        share= (FloatingActionButton) findViewById(R.id.fab1);
        pc= (FloatingActionButton) findViewById(R.id.fab2);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareTextUrl();
            }
        });
        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = data.getStringExtra("ArticleUrl");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
    private void shareTextUrl() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                getResources().getString(R.string.app_name)+":"+data.getStringExtra("ArticleUrl"));
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }

}
