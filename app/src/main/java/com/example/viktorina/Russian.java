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

public class Russian extends AppCompatActivity {
    private MediaPlayer catSd;
    private ImageView cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.russian);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        cat = (ImageView)findViewById(R.id.cat);
        catSd = MediaPlayer.create(this,R.raw.ruslvl);
        imClick();
        catSd.start();

        Button button_back = (Button) findViewById(R.id.button_backtoctg);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                try {
                    Intent intent = new Intent(Russian.this, Categories.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        //Переход на уровень 1 - нч
        TextView rus1 = (TextView)findViewById(R.id.rus_1);
        rus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                try {
                    Intent intent = new Intent(Russian.this, Rus_1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
        //        //Переход на уровень 1 - кц


        //        //Переход на уровень 2 - нч
        TextView rus2 = (TextView)findViewById(R.id.rus_2);
        rus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                try {
                    Intent intent = new Intent(Russian.this, Rus_2.class);
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
        catSd.stop();
        try {
            Intent intent = new Intent(Russian.this, Categories.class);
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
                        catSd.start();
                    }
                }
        );

    }


}