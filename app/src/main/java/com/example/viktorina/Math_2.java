package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class Math_2 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    Dialog dialogHelp;
    public int numLeftTop;
    public  int numRightTop;
    public int numLeftBot;
    public  int numRightBot;
    public  int numArr;
    public  int numTask = 0;
    public int count = 0;
    private ImageView help;
    private MediaPlayer catSd;
    private MediaPlayer cirlce;
    private MediaPlayer star;
    private MediaPlayer square;
    private MediaPlayer triangle;
    private MediaPlayer heart;
    private MediaPlayer rectangle;
    private MediaPlayer endlvl;

    Array array = new Array();
    Random random = new Random();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_4a);
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

        catSd = MediaPlayer.create(this,R.raw.figuresdescp);
        endlvl = MediaPlayer.create(this,R.raw.complete);

        //Игра на весь икран - нч
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Игра на весь икран - кц

        //Кнопка назад - нч
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (numArr){
                    case 0:
                        cirlce.stop();
                        break;
                    case 1:
                        square.stop();
                        break;
                    case 2:
                        star.stop();
                        break;
                    case 3:
                        triangle.stop();
                        break;
                    case 4:
                        heart.stop();
                        break;
                    case 5:
                        rectangle.stop();
                        break;
                }
                try {
                    Intent intent = new Intent(Math_2.this, Math.class);
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
        catSd.start();
        TextView descp = (TextView) dialog.findViewById(R.id.textdescr);
        descp.setText(R.string.math_lvl_2);
        //Картинка в окне - нач
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgfigures);
        //картинка в окне - кц

        //Кнопка закрытия окна - нч
        TextView btnclose =(TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    catSd.stop();
                    Intent intent = new Intent( Math_2.this, Math.class);
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
                switch (numArr){
                    case 0:
                        cirlce.start();
                        break;
                    case 1:
                        square.start();
                        break;
                    case 2:
                        star.start();
                        break;
                    case 3:
                        triangle.start();
                        break;
                    case 4:
                        heart.start();
                        break;
                    case 5:
                        rectangle.start();
                        break;
                }
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
        Uri logVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.figures);
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
                    Intent intent = new Intent(Math_2.this,Math.class);
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
        final Animation a  = AnimationUtils.loadAnimation(Math_2.this,R.anim.alpha);
        //Анимация - кц

        //Массив для прогресса игры нч
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13,
                R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,};
        //Массив для прогресса игры кц
        //Отображение картинок нч
        final TextView task =(TextView) findViewById(R.id.task);
        numLeftTop = random.nextInt(6);
        img_1.setImageResource(array.figures[numLeftTop]);
        numRightTop = random.nextInt(6);
        while (numLeftTop==numRightTop){
            numRightTop = random.nextInt(6);
        }
        img_2.setImageResource(array.figures[numRightTop]);
        numLeftBot = random.nextInt(6);
        while (numLeftBot == numRightTop  || numLeftBot == numLeftTop){
            numLeftBot = random.nextInt(6);
        }
        img_3.setImageResource(array.figures[numLeftBot]);
        numRightBot = random.nextInt(6);
        while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot){
            numRightBot = random.nextInt(6);
        }
        img_4.setImageResource(array.figures[numRightBot]);

        while (numArr != numLeftBot && numArr != numLeftTop && numArr != numRightBot && numArr != numRightTop ){
            numArr = random.nextInt(6);
        }
        task.setText(array.textFigures[numArr]);
        cirlce = MediaPlayer.create(this,R.raw.circle);
        square = MediaPlayer.create(this,R.raw.square);
        star = MediaPlayer.create(this,R.raw.star);
        triangle = MediaPlayer.create(this,R.raw.triangle);
        heart = MediaPlayer.create(this,R.raw.heart);
        rectangle = MediaPlayer.create(this,R.raw.rectangle);


        //Отображение картинок кц

        //Нажатие на левую верхнюю картинку нч
        img_1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    switch (numArr){
                        case 0:
                            cirlce.stop();
                            break;
                        case 1:
                            square.stop();
                            break;
                        case 2:
                            star.stop();
                            break;
                        case 3:
                            triangle.stop();
                            break;
                        case 4:
                            heart.stop();
                            break;
                        case 5:
                            rectangle.stop();
                            break;
                    }
                    img_2.setEnabled(false);
                    img_3.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numLeftTop == numArr){
                        img_1.setImageResource(R.drawable.imgtrue);
                    } else {
                        img_1.setImageResource((R.drawable.imgfalse));
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeftTop == numArr ){
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

                        numLeftTop = random.nextInt(6);
                        img_1.setImageResource(array.figures[numLeftTop]);
                        img_1.startAnimation(a);
                        numRightTop = random.nextInt(6);
                            while (numLeftTop == numRightTop) {
                                numRightTop = random.nextInt(6);
                            }
                        img_2.setImageResource(array.figures[numRightTop]);
                        img_2.startAnimation(a);
                        numLeftBot = random.nextInt(6);
                            while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                                numLeftBot = random.nextInt(6);
                            }
                        img_3.setImageResource(array.figures[numLeftBot]);
                        img_3.startAnimation(a);
                        numRightBot = random.nextInt(6);
                            while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                                numRightBot = random.nextInt(6);
                            }
                        img_4.setImageResource(array.figures[numRightBot]);
                        img_4.startAnimation(a);

                        img_2.setEnabled(true);
                        img_3.setEnabled(true);
                        img_4.setEnabled(true);
                            while (numTask != numLeftBot && numTask != numLeftTop && numTask != numRightBot && numTask != numRightTop || numTask == numArr){
                                numTask = random.nextInt(6);
                            }
                                numArr = numTask;
                                task.setText(array.textFigures[numArr]);
                        switch (numArr){
                            case 0:
                                cirlce.start();
                                break;
                            case 1:
                                square.start();
                                break;
                            case 2:
                                star.start();
                                break;
                            case 3:
                                triangle.start();
                                break;
                            case 4:
                                heart.start();
                                break;
                            case 5:
                                rectangle.start();
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
                    switch (numArr){
                        case 0:
                            cirlce.stop();
                            break;
                        case 1:
                            square.stop();
                            break;
                        case 2:
                            star.stop();
                            break;
                        case 3:
                            triangle.stop();
                            break;
                        case 4:
                            heart.stop();
                            break;
                        case 5:
                            rectangle.stop();
                            break;
                    }
                    img_1.setEnabled(false);
                    img_3.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numRightTop == numArr){
                        img_2.setImageResource(R.drawable.imgtrue);
                    } else {
                        img_2.setImageResource((R.drawable.imgfalse));
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numRightTop == numArr ){
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

                        numLeftTop = random.nextInt(6);
                        img_1.setImageResource(array.figures[numLeftTop]);
                        img_1.startAnimation(a);
                        numRightTop = random.nextInt(6);
                        while (numLeftTop == numRightTop) {
                            numRightTop = random.nextInt(6);
                        }
                        img_2.setImageResource(array.figures[numRightTop]);
                        img_2.startAnimation(a);
                        numLeftBot = random.nextInt(6);
                        while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                            numLeftBot = random.nextInt(6);
                        }
                        img_3.setImageResource(array.figures[numLeftBot]);
                        img_3.startAnimation(a);
                        numRightBot = random.nextInt(6);
                        while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                            numRightBot = random.nextInt(6);
                        }
                        img_4.setImageResource(array.figures[numRightBot]);
                        img_4.startAnimation(a);

                        img_1.setEnabled(true);
                        img_3.setEnabled(true);
                        img_4.setEnabled(true);
                        while (numTask != numLeftBot && numTask != numLeftTop && numTask != numRightBot && numTask != numRightTop || numTask == numArr){
                            numTask = random.nextInt(6);
                        }
                        numArr = numTask;
                        task.setText(array.textFigures[numArr]);

                        switch (numArr){
                            case 0:
                                cirlce.start();
                                break;
                            case 1:
                                square.start();
                                break;
                            case 2:
                                star.start();
                                break;
                            case 3:
                                triangle.start();
                                break;
                            case 4:
                                heart.start();
                                break;
                            case 5:
                                rectangle.start();
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
                    switch (numArr){
                        case 0:
                            cirlce.stop();
                            break;
                        case 1:
                            square.stop();
                            break;
                        case 2:
                            star.stop();
                            break;
                        case 3:
                            triangle.stop();
                            break;
                        case 4:
                            heart.stop();
                            break;
                        case 5:
                            rectangle.stop();
                            break;
                    }
                    img_1.setEnabled(false);
                    img_2.setEnabled(false);
                    img_4.setEnabled(false);
                    if(numLeftBot == numArr){
                        img_3.setImageResource(R.drawable.imgtrue);
                    } else {
                        img_3.setImageResource((R.drawable.imgfalse));
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numLeftBot == numArr ){
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

                        numLeftTop = random.nextInt(6);
                        img_1.setImageResource(array.figures[numLeftTop]);
                        img_1.startAnimation(a);
                        numRightTop = random.nextInt(6);
                        while (numLeftTop == numRightTop) {
                            numRightTop = random.nextInt(6);
                        }
                        img_2.setImageResource(array.figures[numRightTop]);
                        img_2.startAnimation(a);
                        numLeftBot = random.nextInt(6);
                        while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                            numLeftBot = random.nextInt(6);
                        }
                        img_3.setImageResource(array.figures[numLeftBot]);
                        img_3.startAnimation(a);
                        numRightBot = random.nextInt(6);
                        while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                            numRightBot = random.nextInt(6);
                        }
                        img_4.setImageResource(array.figures[numRightBot]);
                        img_4.startAnimation(a);

                        img_1.setEnabled(true);
                        img_2.setEnabled(true);
                        img_4.setEnabled(true);
                        while (numTask != numLeftBot && numTask != numLeftTop && numTask != numRightBot && numTask != numRightTop || numTask == numArr){
                            numTask = random.nextInt(6);
                        }
                        numArr = numTask;
                        task.setText(array.textFigures[numArr]);

                        switch (numArr){
                            case 0:
                                cirlce.start();
                                break;
                            case 1:
                                square.start();
                                break;
                            case 2:
                                star.start();
                                break;
                            case 3:
                                triangle.start();
                                break;
                            case 4:
                                heart.start();
                                break;
                            case 5:
                                rectangle.start();
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
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    switch (numArr){
                        case 0:
                            cirlce.stop();
                            break;
                        case 1:
                            square.stop();
                            break;
                        case 2:
                            star.stop();
                            break;
                        case 3:
                            triangle.stop();
                            break;
                        case 4:
                            heart.stop();
                            break;
                        case 5:
                            rectangle.stop();
                            break;
                    }
                    img_1.setEnabled(false);
                    img_3.setEnabled(false);
                    img_2.setEnabled(false);
                    if(numRightBot == numArr){
                        img_4.setImageResource(R.drawable.imgtrue);
                    } else {
                        img_4.setImageResource((R.drawable.imgfalse));
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(numRightBot == numArr ){
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
                        numLeftTop = random.nextInt(6);
                        img_1.setImageResource(array.figures[numLeftTop]);
                        img_1.startAnimation(a);
                        numRightTop = random.nextInt(6);
                        while (numLeftTop == numRightTop) {
                            numRightTop = random.nextInt(6);
                        }
                        img_2.setImageResource(array.figures[numRightTop]);
                        img_2.startAnimation(a);
                        numLeftBot = random.nextInt(6);
                        while (numLeftBot == numRightTop || numLeftBot == numLeftTop) {
                            numLeftBot = random.nextInt(6);
                        }
                        img_3.setImageResource(array.figures[numLeftBot]);
                        img_3.startAnimation(a);
                        numRightBot = random.nextInt(6);
                        while (numLeftBot == numRightBot || numRightTop == numRightBot || numLeftTop == numRightBot) {
                            numRightBot = random.nextInt(6);
                        }
                        img_4.setImageResource(array.figures[numRightBot]);
                        img_4.startAnimation(a);

                        img_1.setEnabled(true);
                        img_3.setEnabled(true);
                        img_2.setEnabled(true);
                        while (numTask != numLeftBot && numTask != numLeftTop && numTask != numRightBot && numTask != numRightTop || numTask == numArr){
                            numTask = random.nextInt(6);
                        }
                        numArr = numTask;
                        task.setText(array.textFigures[numArr]);
                        switch (numArr){
                            case 0:
                                cirlce.start();
                                break;
                            case 1:
                                square.start();
                                break;
                            case 2:
                                star.start();
                                break;
                            case 3:
                               triangle.start();
                                break;
                            case 4:
                                heart.start();
                                break;
                            case 5:
                                rectangle.start();
                                break;
                        }
                    }
                }
                return true;
            }
        });
        //Нажатие на правую нижнюю картинку кц


    }


    private void releaseMp(MediaPlayer sd) {
        if (sd != null) {
            try {
                sd.release();
                sd = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
