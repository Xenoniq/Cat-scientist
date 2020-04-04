package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
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
import android.widget.VideoView;

import java.util.Random;

public class Logic_1 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    Dialog dialogHelp;
    private ImageView help;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_4);
        final ImageView img_1 = (ImageView) findViewById(R.id.img_left_top);
        final ImageView img_2 = (ImageView) findViewById(R.id.img_right_top);
        final ImageView img_3 = (ImageView) findViewById(R.id.img_left_bot);
        final ImageView img_4 = (ImageView) findViewById(R.id.img_right_bot);
        //Скругление углов
        img_1.setClipToOutline(true);
        img_2.setClipToOutline(true);
        img_3.setClipToOutline(true);
        img_4.setClipToOutline(true);
        //Скругление углов
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
        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        //Картинка в окне - нач
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimglogic);
        //картинка в окне - кц

        //Кнопка закрытия окна - нч
        TextView btnclose =(TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent( Logic_1.this, Logic.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialog.dismiss();
            }
        });
        //Кнопка закрытия окна - кц
        //Продолжить - нч
        Button btncontinue = (Button)dialog.findViewById(R.id.buttoncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        //Продолжить - кц
        dialog.show();
        //Вызов диалогового окна - кц

        //Диалоговое окно помощь - нач
        dialogHelp = new Dialog(this);
        dialogHelp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogHelp.setContentView(R.layout.dialoghelp);
        dialogHelp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogHelp.setCancelable(false);
        final VideoView videoView = (VideoView) dialogHelp.findViewById(R.id.vidHelp);
        Uri logVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.logic);
        videoView.setVideoURI(logVideoUri);
        videoView.start();

        Button but_cont = (Button)dialogHelp.findViewById(R.id.buttoncont);
        but_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    videoView.pause();
                    dialogHelp.dismiss();
                }catch (Exception e){

                }
            }
        });
        //Диалоговое окно помощь - кц


        //Диалоговое окно в конце уровня - нач
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);
        //Продолжить - нч
        Button btncont = (Button)dialogEnd.findViewById(R.id.butcont);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Logic_1.this,Logic.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });
        //Диалоговое окно в конце уровня - кц
        //Кнопка помощь - нач
        help = (ImageView)findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           dialogHelp.show();
                                       }
                                   }
        );
        //нопка помощь - кц
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