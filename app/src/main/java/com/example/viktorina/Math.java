package com.example.viktorina;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Math extends AppCompatActivity {
    private MediaPlayer catSd;
    private ImageView cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cat = (ImageView)findViewById(R.id.cat);
        catSd = MediaPlayer.create(this,R.raw.mathlvl);
        imClick();
        soundPlay(catSd);

        Button button_back = (Button) findViewById(R.id.button_backtoctg);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Math.this, Categories.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //Переход на уровень 1 - нч
        TextView lvl1 = (TextView)findViewById(R.id.math_1);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Math.this, Math_1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //        //Переход на уровень 1 - кц


        //        //Переход на уровень 2 - нч
        TextView lvl2 = (TextView)findViewById(R.id.math_2);
        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Math.this, Math_2.class);
                    startActivity(intent);finish();
                }catch (Exception e){

               }

           }
        });
        //Переход на уровень 2 - кц
    }


    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        soundStop(catSd);
        try {
            Intent intent = new Intent(Math.this, Categories.class);
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
