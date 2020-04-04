package com.example.viktorina;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Math extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button) findViewById(R.id.button_backtoctg);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                try {
                    Intent intent = new Intent(Math.this, Math_1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }

            }
        });
//        //Переход на уровень 1 - кц
//        //Переход на уровень 2 - нч
//        TextView lvl2 = (TextView)findViewById(R.id.math_2);
//        lvl1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent intent = new Intent(Math.this, Math_2.class);
//                    startActivity(intent);finish();
//                }catch (Exception e){
//
//                }
//
//            }
//        });
//        //Переход на уровень 1 - кц
    }


    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Math.this, Categories.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //Сиситемная кнопка назад - конец
}
