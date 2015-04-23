package com.example.administrator.car;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.image_hello_main);
        Animation anim= AnimationUtils.loadAnimation(MainActivity.this, R.anim.set);
        imageView.startAnimation(anim);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,ZhuyaoActivity.class);
        startActivity(intent);
    }
}
