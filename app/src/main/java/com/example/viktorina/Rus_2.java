package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
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

import java.io.IOException;
import java.util.Random;

public class Rus_2 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    Dialog dialogHelp;
    public int numLeft;
    public  int numRight;
    public  int numTask;
    public int count = 0;
    private MediaPlayer catSd;
    private MediaPlayer endlvl;
    private MediaPlayer sonorous;
    private MediaPlayer muffled;
    private MediaPlayer truesd;
    private MediaPlayer falsesd;
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

        catSd = MediaPlayer.create(this,R.raw.soundsdescp);
        endlvl = MediaPlayer.create(this,R.raw.complete);
        truesd = MediaPlayer.create(this,R.raw.truesd);
        falsesd = MediaPlayer.create(this,R.raw.falsesd);
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
        descp.setText(R.string.rus_lvl_2);
        //Картинка в окне - нач
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgrustwo);
        //картинка в окне - кц
        //Кнопка закрытия окна - нч
        TextView btnclose =(TextView)dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                try {
                    Intent intent = new Intent(Rus_2.this, Russian.class);
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
                switch (numTask){
                    case 0:
                        sonorous.start();
                        break;
                    case 1:
                        muffled.start();
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

        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        View layout = getLayoutInflater().inflate(R.layout.dialoghelp, null);

        dialogHelp.setContentView(R.layout.dialoghelp);
        dialogHelp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogHelp
                .getWindow()
                .setLayout((int) (displayRectangle.width()), (int) (displayRectangle.height() * 0.5));
        dialogHelp.setCancelable(false);
        final VideoView videoView = (VideoView) dialogHelp.findViewById(R.id.vidHelp);
        Uri numVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.soundsvid);
        videoView.setVideoURI(numVideoUri);


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
                                        videoView.start();
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
                    Intent intent = new Intent(Rus_2.this,Russian.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
                endlvl.stop();
            }
        });
        //Диалоговое окно в конце уровня - кц
        //Кнопка назад - нч
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Rus_2.this, Russian.class);
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
        final Animation a  = AnimationUtils.loadAnimation(Rus_2.this,R.anim.alpha);
        //Анимация - кц
        sonorous = MediaPlayer.create(this,R.raw.sonorous);
        muffled = MediaPlayer.create(this,R.raw.muffled);
        //отображение картинок и задания нч
        final TextView task =(TextView) findViewById(R.id.task);
        numLeft = random.nextInt(21);
        img_left.setImageResource(array.sounds[numLeft]);
        if(numLeft < 11) {
            numRight = 11 + random.nextInt(9);
        }
        else numRight = random.nextInt(10);
        img_right.setImageResource(array.sounds[numRight]);
        numTask = random.nextInt(2);
        task.setText(array.textSounds[numTask]);

        //отображение картинок и задания кц

        //Нажатие на левую картинку нч
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (numTask){
                        case 0:
                            sonorous.stop();
                            break;
                        case 1:
                            muffled.stop();
                            break;
                    }
                    img_right.setEnabled(false);
                    switch (numTask) {
                        case 0:
                            if (numLeft < 11) {
                                truesd.stop();
                                try {
                                    truesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_left.setImageResource(R.drawable.imgtrue);
                                truesd.start();
                            } else {
                                falsesd.stop();
                                try {
                                    falsesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_left.setImageResource((R.drawable.imgfalse));
                                falsesd.start();
                            }
                            break;
                        case 1:
                            if (numLeft > 10) {
                                truesd.stop();
                                try {
                                    truesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_left.setImageResource(R.drawable.imgtrue);
                                truesd.start();
                            } else {
                                falsesd.stop();
                                try {
                                    falsesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_left.setImageResource(R.drawable.imgfalse);
                                falsesd.start();
                            }
                            break;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    switch (numTask) {
                        case 0:
                            if(numLeft < 11){
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
                            if(numLeft > 10){
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
                        numLeft = random.nextInt(21);
                        img_left.setImageResource(array.sounds[numLeft]);
                        img_left.startAnimation(a);
                        if(numLeft < 11) {
                            numRight = 11 + random.nextInt(9);
                        } else numRight = random.nextInt(10);
                        img_right.setImageResource(array.sounds[numRight]);
                        img_right.startAnimation(a);
                        img_right.setEnabled(true);
                    }
                    numTask = random.nextInt(2);
                    task.setText(array.textSounds[numTask]);
                    switch (numTask){
                        case 0:
                            sonorous.start();
                            break;
                        case 1:
                            muffled.start();
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
                            sonorous.stop();
                            break;
                        case 1:
                            muffled.stop();
                            break;
                    }
                    img_left.setEnabled(false);
                    switch (numTask) {
                        case 0:
                            if (numRight < 11) {
                                truesd.stop();
                                try {
                                    truesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_right.setImageResource(R.drawable.imgtrue);
                                truesd.start();
                            } else {
                                falsesd.stop();
                                try {
                                    falsesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_right.setImageResource((R.drawable.imgfalse));
                                falsesd.start();
                            }
                            break;
                        case 1:
                            if (numRight > 10) {
                                truesd.stop();
                                try {
                                    truesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_right.setImageResource(R.drawable.imgtrue);
                                truesd.start();
                            } else {
                                falsesd.stop();
                                try {
                                    falsesd.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                img_right.setImageResource(R.drawable.imgfalse);
                                falsesd.start();
                            }
                            break;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    switch (numTask) {
                        case 0:
                            if(numRight < 11){
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
                            if(numRight > 10){
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
                        dialogEnd.show();
                        endlvl.start();
                    }
                    else {
                        numLeft = random.nextInt(21);
                        img_left.setImageResource(array.sounds[numLeft]);
                        img_left.startAnimation(a);
                        if(numLeft < 11) {
                            numRight = 11 + random.nextInt(9);
                        } else numRight = random.nextInt(10);
                        img_right.setImageResource(array.sounds[numRight]);
                        img_right.startAnimation(a);
                        img_left.setEnabled(true);
                    }
                    numTask = random.nextInt(2);
                    task.setText(array.textSounds[numTask]);
                    switch (numTask){
                        case 0:
                            sonorous.start();
                            break;
                        case 1:
                            muffled.start();
                            break;
                    }
                }
                return true;
            }

        });
        //Нажатие на правую картинку кц

    }

}

