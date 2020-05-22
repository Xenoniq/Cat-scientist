package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TheWorld extends AppCompatActivity {
    private MediaPlayer catSd;
    private ImageView cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cat = (ImageView)findViewById(R.id.cat);
        catSd = MediaPlayer.create(this,R.raw.animallvl);
        imClick();
        soundPlay(catSd);

        Button button_back = (Button) findViewById(R.id.button_backtoctg);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(TheWorld.this, Categories.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //Переход на уровень 1 - нч
        TextView lvl1 = (TextView)findViewById(R.id.world);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(TheWorld.this, World_1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //Переход на уровень 1 - кц
    }


    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        soundStop(catSd);
        try {
            Intent intent = new Intent(TheWorld.this, Categories.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //Сиситемная кнопка назад - конец

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
    public void soundStop(MediaPlayer sd){
        sd.stop();
    }
}
