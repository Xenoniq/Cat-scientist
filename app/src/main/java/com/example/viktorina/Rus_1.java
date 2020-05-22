package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class Rus_1 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    Dialog dialogHelp;
    public int numLeft;
    public  int numRight;
    public  int numTask;
    public int count = 0;
    private MediaPlayer catSd;
    private MediaPlayer endlvl;
    private MediaPlayer vowel;
    private MediaPlayer consonant;
    Array array = new Array();
    Random random = new Random();
    private ImageView help;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_a);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //Скругление углов
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);
        //Скругление углов
        catSd = MediaPlayer.create(this,R.raw.lettersdescp);
        endlvl = MediaPlayer.create(this,R.raw.complete);
        catSd.start();

        //Игра на весь икран - нч
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Игра на весь икран - кц
        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        TextView descp = (TextView) dialog.findViewById(R.id.textdescr);
        descp.setText(R.string.rus_lvl_1);
        //Картинка в окне - нач
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgrusone);
        //картинка в окне - кц
        //Кнопка закрытия окна - нч
        TextView btnclose =(TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                try {
                    Intent intent = new Intent(Rus_1.this, Russian.class);
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
                catSd.stop();
                switch (numTask){
                    case 0:
                        vowel.start();
                        break;
                    case 1:
                        consonant.start();
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
        Uri numVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.letters);
        videoView.setVideoURI(numVideoUri);
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
                    Intent intent = new Intent(Rus_1.this,Russian.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                endlvl.stop();
                dialogEnd.dismiss();
            }
        });
        //Диалоговое окно в конце уровня - кц
        //Кнопка назад - нч
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Rus_1.this, Russian.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Кнопка назад - кц
        //Массив для прогресса игры нч
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7,
                R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12, R.id.point13,
                R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,};
        //Массив для прогресса игры кц
        //Анимация- нч
        final Animation a  = AnimationUtils.loadAnimation(Rus_1.this,R.anim.alpha);
        //Анимация - кц
        vowel = MediaPlayer.create(this,R.raw.vowel);
        consonant = MediaPlayer.create(this,R.raw.consonant);
        //отображение картинок и задания нч
        final TextView task =(TextView) findViewById(R.id.task);
        numLeft = random.nextInt(31);
        img_left.setImageResource(array.letters[numLeft]);
        if(numLeft < 10) {
            numRight = 10 + random.nextInt(22);
        }
        else numRight = random.nextInt(10);
        img_right.setImageResource(array.letters[numRight]);
        numTask = random.nextInt(2);
        task.setText(array.textLetters[numTask]);
        //отображение картинок и задания кц

        //Нажатие на левую картинку нч
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (numTask){
                        case 0:
                            vowel.stop();
                            break;
                        case 1:
                           consonant.stop();
                            break;
                    }
                    img_right.setEnabled(false);
                    switch (numTask) {
                        case 0:
                            if (numLeft < 10) {
                                img_left.setImageResource(R.drawable.imgtrue);
                            } else {
                                img_left.setImageResource((R.drawable.imgfalse));
                            }
                            break;
                        case 1:
                            if (numLeft > 9) {
                                img_left.setImageResource(R.drawable.imgtrue);
                            } else {
                                img_left.setImageResource(R.drawable.imgfalse);
                            }
                            break;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    switch (numTask) {
                        case 0:
                            if(numLeft < 10){
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
                            }
                            else {
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
                            break;
                        case 1:
                            if(numLeft > 9){
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
                            }
                            else {
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
                            break;
                    }
                    if(count==20){
                       endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        numLeft = random.nextInt(31);
                        img_left.setImageResource(array.letters[numLeft]);
                        img_left.startAnimation(a);
                        if(numLeft < 10) {
                            numRight = 10 + random.nextInt(22);
                        } else numRight = random.nextInt(10);
                        img_right.setImageResource(array.letters[numRight]);
                        img_right.startAnimation(a);
                        img_right.setEnabled(true);
                    }
                    numTask = random.nextInt(2);
                    task.setText(array.textLetters[numTask]);
                    switch (numTask){
                        case 0:
                            vowel.start();
                            break;
                        case 1:
                            consonant.start();
                            break;
                    }
                }
                    return true;
                }

        });
        //Нажатие на левую картинку кц


        //нажатие на правую картинку нч
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (numTask){
                        case 0:
                            vowel.stop();
                            break;
                        case 1:
                            consonant.stop();
                            break;
                    }
                    img_left.setEnabled(false);
                    switch (numTask) {
                        case 0:
                            if (numRight < 10) {
                                img_right.setImageResource(R.drawable.imgtrue);
                            } else {
                                img_right.setImageResource((R.drawable.imgfalse));
                            }
                            break;
                        case 1:
                            if (numRight > 9) {
                                img_right.setImageResource(R.drawable.imgtrue);
                            } else {
                                img_right.setImageResource(R.drawable.imgfalse);
                            }
                            break;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    switch (numTask) {
                        case 0:
                            if(numRight < 10){
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
                            }
                            else {
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
                            break;
                        case 1:
                            if(numRight > 9){
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
                            }
                            else {
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
                            break;
                    }
                    if(count==20){
                        endlvl.start();
                        dialogEnd.show();
                    }
                    else {
                        numLeft = random.nextInt(31);
                        img_left.setImageResource(array.letters[numLeft]);
                        img_left.startAnimation(a);
                        if(numLeft < 10) {
                            numRight = 10 + random.nextInt(22);
                        } else numRight = random.nextInt(10);
                        img_right.setImageResource(array.letters[numRight]);
                        img_right.startAnimation(a);
                        img_left.setEnabled(true);
                    }
                    numTask = random.nextInt(2);
                    task.setText(array.textLetters[numTask]);
                    switch (numTask){
                        case 0:
                            vowel.start();
                            break;
                        case 1:
                            consonant.start();
                            break;
                    }
                }
                return true;
            }

        });
        //Нажатие на правую картинку кц

    }


}
