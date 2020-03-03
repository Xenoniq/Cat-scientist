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
    private MediaPlayer catSd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttStart = (Button)findViewById(R.id.buttonStart);
        buttStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Categories.class);
                    startActivity(intent);finish();
                }catch (Exception e) {

                }

            }
        });
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cat = (ImageView)findViewById(R.id.cat);
        catSd = MediaPlayer.create(this,R.raw.catsd);
        imClick();
    }
    public void imClick() {
        cat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundPlay(catSd);
                    }
                }
        );

    }


    public void soundPlay(MediaPlayer sd){
        sd.start();
    }

}
