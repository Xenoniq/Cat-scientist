package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView cat;
    private MediaPlayer hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cat = (ImageView)findViewById(R.id.cat);
        hello = MediaPlayer.create(this,R.raw.hello);
        imClick();
        hello.start();
        Button buttStart = (Button)findViewById(R.id.buttonStart);
        buttStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello.stop();
                try {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);finish();
                }catch (Exception e) {

                }

            }
        });
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void imClick() {
        cat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hello.start();
                    }
                }
        );

    }



}
