package com.example.yls.meterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by icarus9527 on 2017/3/31.
 */
//第二次上传
public class DetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("animeName");
        int img = intent.getIntExtra("animePic",0);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.detail_collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        imageView = (ImageView) findViewById(R.id.detail_img);
        textView = (TextView) findViewById(R.id.detail_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout.setTitle(name);
        Glide.with(this).load(img).into(imageView);
        String animeContent = generateAnimeContent(name);
        textView.setText(animeContent);
    }

    private String generateAnimeContent(String name) {
        StringBuffer animeContent = new StringBuffer();
        for(int i=0;i<300;i++){
            animeContent.append(name);
        }
        return animeContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
