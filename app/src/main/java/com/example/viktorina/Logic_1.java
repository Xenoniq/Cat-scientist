package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
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
    public int numLeftTop;
    public  int numRightTop;
    public int numLeftBot;
    public  int numRightBot;
    public  int numArr;
    public int count = 0;
    private MediaPlayer catSd;
    private MediaPlayer endlvl;
    private MediaPlayer truesd;
    private MediaPlayer falsesd;
    private ImageView help;
    Array array = new Array();
    Random random = new Random();
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

        catSd = MediaPlayer.create(this,R.raw.logicdescp);
        endlvl = MediaPlayer.create(this,R.raw.complete);
        truesd = MediaPlayer.create(this,R.raw.truesd);
        falsesd = MediaPlayer.create(this,R.raw.falsesd);
        catSd.start();

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
        TextView descp = (TextView) dialog.findViewById(R.id.textdescr);
        descp.setText(R.string.logic_lvl_1);
        //Картинка в окне - нач
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimglogic);
        //картинка в окне - кц

        //Кнопка закрытия окна - нч
        TextView btnclose =(TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
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
                catSd.stop();
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
                endlvl.stop();
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

        //Анимация- нч
        final Animation a  = AnimationUtils.loadAnimation(Logic_1.this,R.anim.alpha);
        //Анимация - кц

        //Массив для прогресса игры нч
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13,
                R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,};
        //Массив для прогресса игры кц

        //Показ картинок - нч
        numArr = random.nextInt(5);
        switch(numArr){
                case 0 :
                    numLeftTop = random.nextInt(4);
                    img_1.setImageResource(array.yellow[numLeftTop]);
                    numRightTop = random.nextInt(4);
                    while (numLeftTop==numRightTop){
                        numRightTop = random.nextInt(4);
                    }
                    img_2.setImageResource(array.yellow[numRightTop]);
                    numLeftBot = random.nextInt(4);
                    while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
                        numLeftBot = random.nextInt(4);
                    }
                    img_3.setImageResource(array.yellow[numLeftBot]);
                    numRightBot = random.nextInt(4);
                    while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot){
                        numRightBot = random.nextInt(4);
                    }
                    img_4.setImageResource(array.yellow[numRightBot]);
                    break;

                case 1:
                    numLeftTop = random.nextInt(4);
                    img_1.setImageResource(array.red[numLeftTop]);
                    numRightTop = random.nextInt(4);
                    while (numLeftTop == numRightTop){
                        numRightTop = random.nextInt(4);
                    }
                    img_2.setImageResource(array.red[numRightTop]);
                    numLeftBot = random.nextInt(4);
                    while (numLeftBot == numRightTop  || numLeftBot==numLeftTop){
                        numLeftBot = random.nextInt(4);
                    }
                    img_3.setImageResource(array.red[numLeftBot]);
                    numRightBot = random.nextInt(4);
                    while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                        numRightBot = random.nextInt(4);
                    }
                    img_4.setImageResource(array.red[numRightBot]);
                    break;
                case 2 :
                    numLeftTop = random.nextInt(4);
                    img_1.setImageResource(array.fruit[numLeftTop]);
                    numRightTop = random.nextInt(4);
                    while (numLeftTop==numRightTop){
                        numRightTop = random.nextInt(4);
                    }
                    img_2.setImageResource(array.fruit[numRightTop]);
                    numLeftBot = random.nextInt(4);
                    while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                        numLeftBot = random.nextInt(4);
                    }
                    img_3.setImageResource(array.fruit[numLeftBot]);
                    numRightBot = random.nextInt(4);
                    while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                        numRightBot = random.nextInt(4);
                    }
                    img_4.setImageResource(array.fruit[numRightBot]);

                    break;
                case 3 :
                    numLeftTop = random.nextInt(4);
                    img_1.setImageResource(array.car[numLeftTop]);
                    numRightTop = random.nextInt(4);
                    while (numLeftTop==numRightTop){
                        numRightTop = random.nextInt(4);
                    }
                    img_2.setImageResource(array.car[numRightTop]);
                    numLeftBot = random.nextInt(4);
                    while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                        numLeftBot = random.nextInt(4);
                    }
                    img_3.setImageResource(array.car[numLeftBot]);
                    numRightBot = random.nextInt(4);
                    while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                        numRightBot = random.nextInt(4);
                    }
                    img_4.setImageResource(array.car[numRightBot]);

                    break;
                case 4 :
                    numLeftTop = random.nextInt(4);
                    img_1.setImageResource(array.fish[numLeftTop]);
                    numRightTop = random.nextInt(4);
                    while (numLeftTop==numRightTop){
                        numRightTop = random.nextInt(4);
                    }
                    img_2.setImageResource(array.fish[numRightTop]);
                    numLeftBot = random.nextInt(4);
                    while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                        numLeftBot = random.nextInt(4);
                    }
                    img_3.setImageResource(array.fish[numLeftBot]);
                    numRightBot = random.nextInt(4);
                    while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                        numRightBot = random.nextInt(4);
                    }
                    img_4.setImageResource(array.fish[numRightBot]);

                    break;

        }

        //Нажатие на левую верхнюю картинку нч
        img_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_2.setEnabled(false);
                    img_3.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numLeftTop == 3){
                        img_1.setImageResource(R.drawable.imgtrue);
                        truesd.start();
                    } else {
                        img_1.setImageResource((R.drawable.imgfalse));
                        falsesd.start();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeftTop == 3){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i = 0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i < count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }


                    }else {
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count = count-2;
                            }
                        }
                        for (int i = 0; i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if(count==20){
                        endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        switch (numArr) {
                            case 0:
                            numLeftTop = random.nextInt(4);
                            img_1.setImageResource(array.red[numLeftTop]);
                            img_1.startAnimation(a);
                            numRightTop = random.nextInt(4);
                            while (numLeftTop == numRightTop) {
                                numRightTop = random.nextInt(4);
                            }
                            img_2.setImageResource(array.red[numRightTop]);
                            img_2.startAnimation(a);
                            numLeftBot = random.nextInt(4);
                            while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                                numLeftBot = random.nextInt(4);
                            }
                            img_3.setImageResource(array.red[numLeftBot]);
                            img_3.startAnimation(a);
                            numRightBot = random.nextInt(4);
                            while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                                numRightBot = random.nextInt(4);
                            }
                            img_4.setImageResource(array.red[numRightBot]);
                            img_4.startAnimation(a);

                            img_2.setEnabled(true);
                            img_3.setEnabled(true);
                            img_4.setEnabled(true);
                            numArr = 1;
                            break;
                            case 1:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fruit[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fruit[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fruit[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fruit[numRightBot]);
                                img_4.startAnimation(a);

                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr =2;
                                break;
                            case 2:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.car[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.car[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.car[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.car[numRightBot]);
                                img_4.startAnimation(a);

                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 3;
                                break;
                            case 3:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fish[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fish[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fish[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fish[numRightBot]);
                                img_4.startAnimation(a);

                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 4;
                                break;
                            case 4:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.yellow[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.yellow[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.yellow[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.yellow[numRightBot]);
                                img_4.startAnimation(a);

                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);

                                numArr = 0;
                                break;

                        }

                    }
                }
                return true;
            }
        });
        //Нажатие на левую верхнюю картинку кц

        //Нажатие на правую верхнюю картинку нч
        img_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_1.setEnabled(false);
                    img_3.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numRightTop == 3){
                        img_2.setImageResource(R.drawable.imgtrue);
                        truesd.start();
                    } else {
                        img_2.setImageResource((R.drawable.imgfalse));
                        falsesd.start();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numRightTop == 3){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i = 0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }


                    }else {
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count=count-2;
                            }
                        }
                        for (int i = 0; i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if(count==20){
                        endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        switch (numArr) {
                            case 0:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.red[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop) {
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.red[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.red[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.red[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 1;
                                break;
                            case 1:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fruit[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fruit[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fruit[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fruit[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr =2;
                                break;
                            case 2:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.car[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.car[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.car[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.car[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 3;
                                break;
                            case 3:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fish[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fish[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fish[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fish[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 4;
                                break;
                            case 4:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.yellow[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.yellow[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.yellow[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.yellow[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_3.setEnabled(true);
                                img_4.setEnabled(true);

                                numArr = 0;
                                break;

                        }

                    }
                }
                return true;
            }
        });
        //Нажатие на правую верхнюю картинку кц

        //Нажатие на левую нижнюю картинку нч
        img_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_1.setEnabled(false);
                    img_2.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numLeftBot == 3){
                        img_3.setImageResource(R.drawable.imgtrue);
                        truesd.start();
                    } else {
                        img_3.setImageResource((R.drawable.imgfalse));
                        falsesd.start();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeftBot == 3){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i = 0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }


                    }else {
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count=count-2;
                            }
                        }
                        for (int i = 0; i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if(count==20){
                        endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        switch (numArr) {
                            case 0:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.red[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop) {
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.red[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.red[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.red[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 1;
                                break;
                            case 1:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fruit[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fruit[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fruit[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fruit[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr =2;
                                break;
                            case 2:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.car[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.car[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.car[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.car[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 3;
                                break;
                            case 3:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fish[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fish[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fish[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fish[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_4.setEnabled(true);
                                numArr = 4;
                                break;
                            case 4:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.yellow[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.yellow[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.yellow[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.yellow[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_4.setEnabled(true);

                                numArr = 0;
                                break;

                        }

                    }
                }
                return true;
            }
        });
        //Нажатие на левую нижнюю картинку кц

        //Нажатие на правую нижнюю картинку нч
        img_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_1.setEnabled(false);
                    img_2.setEnabled(false);
                    img_3.setEnabled(false);
                    if(numRightBot == 3){
                        img_4.setImageResource(R.drawable.imgtrue);
                        truesd.start();
                    } else {
                        img_4.setImageResource((R.drawable.imgfalse));
                        falsesd.start();
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numRightBot == 3){
                        if(count<20){
                            count=count+1;
                        }
                        for (int i = 0; i<20;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }


                    }else {
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count=count-2;
                            }
                        }
                        for (int i = 0; i<19;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0; i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if(count==20){
                        endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        switch (numArr) {
                            case 0:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.red[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop) {
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.red[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.red[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.red[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                numArr = 1;
                                break;
                            case 1:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fruit[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fruit[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fruit[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fruit[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                numArr =2;
                                break;
                            case 2:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.car[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.car[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.car[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.car[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                numArr = 3;
                                break;
                            case 3:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.fish[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.fish[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.fish[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.fish[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_3.setEnabled(true);
                                numArr = 4;
                                break;
                            case 4:
                                numLeftTop = random.nextInt(4);
                                img_1.setImageResource(array.yellow[numLeftTop]);
                                img_1.startAnimation(a);
                                numRightTop = random.nextInt(4);
                                while (numLeftTop == numRightTop){
                                    numRightTop = random.nextInt(4);
                                }
                                img_2.setImageResource(array.yellow[numRightTop]);
                                img_2.startAnimation(a);
                                numLeftBot = random.nextInt(4);
                                while (numLeftBot==numRightTop  || numLeftBot==numLeftTop){
                                    numLeftBot = random.nextInt(4);
                                }
                                img_3.setImageResource(array.yellow[numLeftBot]);
                                img_3.startAnimation(a);
                                numRightBot = random.nextInt(4);
                                while (numLeftBot==numRightBot || numRightTop==numRightBot || numLeftTop==numRightBot){
                                    numRightBot = random.nextInt(4);
                                }
                                img_4.setImageResource(array.yellow[numRightBot]);
                                img_4.startAnimation(a);

                                img_1.setEnabled(true);
                                img_2.setEnabled(true);
                                img_3.setEnabled(true);

                                numArr = 0;
                                break;

                        }

                    }
                }
                return true;
            }
        });
        //Нажатие на правую нижнюю картинку кц


    }




    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        catSd.stop();
        try {
            Intent intent = new Intent(Logic_1.this, Logic.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //Сиситемная кнопка назад - конец

}