package com.wordpress.ui.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wordpress.R;


/**
 * Created by wail babou on 2017-08-09.
 * Project : IN_Android_Official.
 */

public class AddComment extends AppCompatActivity {
    private String comment_post;
    private EditText caption;
    private FloatingActionButton submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.dialog_add_comment);
        comment_post = getIntent().getStringExtra("commentPost");
        Log.e("item_id_open",comment_post+"");
        initView();
        initListener();
    }

    private void initView() {
        caption = findViewById(R.id.comment);
        submit = findViewById(R.id.fab);
    }


    private void initListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caption.getText().length() > 0) {
                    changeData();
                } else {
                    Toast.makeText(AddComment.this,"Taper quelque chose svp  .. !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeData() {

    }
}
