package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Logic_1 extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_4);

        //Игра на весь икран - нч
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Игра на весь икран - кц

        //Кнопка назад - нч
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Logic_1.this, Logic.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Кнопка назад - кц
    }
    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Logic_1.this, Logic.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //Сиситемная кнопка назад - конец
}