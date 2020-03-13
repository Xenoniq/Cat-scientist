package com.example.viktorina;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseAccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_account);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

        //Системная кнопка назад - начало
        @Override
        public void onBackPressed() {
            try {
                Intent intent = new Intent(ChooseAccount.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {

            }
        }
        //Сиситемная кнопка назад - конец
    }
