package com.example.viktorina;

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

public class Categories extends AppCompatActivity {
    private MediaPlayer catSd;
    private ImageView cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Categories.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        cat = (ImageView)findViewById(R.id.cat);
        catSd = MediaPlayer.create(this,R.raw.category);
        imClick();
        soundPlay(catSd);
        //Переход в категорию математика - нч
        TextView lvl1 = (TextView)findViewById(R.id.ctg_1);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Categories.this, Math.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //Переход в категорию математика- кц

        //Переход в категорию русский - нч
        TextView lvl2 = (TextView)findViewById(R.id.ctg_2);
        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Categories.this, Russian.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //Переход в категорию русский - кц

        //Переход в категорию логика - нч
        TextView lvl3 = (TextView)findViewById(R.id.ctg_3);
        lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Categories.this, Logic.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //Переход в категорию логика- кц

        //Переход в категорию окрмир - нч
        TextView lvl4 = (TextView)findViewById(R.id.ctg_4);
        lvl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStop(catSd);
                try {
                    Intent intent = new Intent(Categories.this, TheWorld.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //Переход в категорию окрмир- кц

    }


    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        soundStop(catSd);
        try {
            Intent intent = new Intent(Categories.this, MainActivity.class);
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