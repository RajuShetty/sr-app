package com.wordpress.ui.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.wordpress.R;
import com.wordpress.ui.Comments.CommentsActivity;

/**
 * Created by wail babou on 2016-12-24.
 */

public class OpenArticle  extends AppCompatActivity{
    TextView title;
    WebView web;
    WebView image;
    FloatingActionButton share,pc,comment;
    Intent data;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_article);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title= (TextView) findViewById(R.id.title);
        web= (WebView) findViewById(R.id.web);
        image= (WebView) findViewById(R.id.imageheader);
        data = getIntent();

        image.loadDataWithBaseURL(null, "<style> img{display: inline;height: auto; height:90%;} " +
                " iframe{display: inline;height: auto;max-width: 100%;}</style>"
                +"<center> <img src=\""+data.getStringExtra("logo")+"\" > </center>", "text/html", "UTF-8", null);

        title.setText(data.getStringExtra("title"));
        //web.getSettings().setJavaScriptEnabled(true);
        web.loadDataWithBaseURL("",data.getStringExtra("content"), "text/html", "UTF-8", "");
        share= (FloatingActionButton) findViewById(R.id.fab1);
        pc= (FloatingActionButton) findViewById(R.id.fab2);
        comment=findViewById(R.id.fab3);
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
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(OpenArticle.this, CommentsActivity.class);
                ii.putExtra("id",getIntent().getIntExtra("id",0)+"");
                startActivity(ii);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
