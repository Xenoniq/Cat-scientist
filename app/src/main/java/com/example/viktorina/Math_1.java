package com.example.viktorina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
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
import java.util.Date;
import java.util.Random;

public class Math_1 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    Dialog dialogHelp;
    public int numLeft;
    public int numRight;
    public int count = 0;
    Array array = new Array();
    Random random = new Random();
    private ImageView help;
    private MediaPlayer catSd;
    private MediaPlayer endlvl;
    private MediaPlayer truesd;
    private MediaPlayer falsesd;
    public static int lvl = 1;
    private Stopwatch stopwatch;
    public static String time;
    static DBHelper dbHelper;
    public TextView bestTime;
    public static String bestScore;
    public static SQLiteDatabase database;
    public String userName;
    public static int userId;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        stopwatch = new Stopwatch(Math_1.this);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //Скругление углов
        img_left.setClipToOutline(true);
        img_right.setClipToOutline(true);
        //Скругление углов

        //Определяем пользователя нч
        if(Login.userInfo!=null){
            if(Login.userInfo.userName!=null){
                userName = Login.userInfo.userName;
            }
        }

        String selection = DBHelper.COLUMN_NAME + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(userName)};
        Cursor c = database.query(DBHelper.TABLE_USERS, null, selection, selectionArgs, null, null, null);
        if(c.moveToFirst()){
            userId  = c.getInt(c.getColumnIndexOrThrow (DBHelper.COLUMN_ID));
        }
        c.close();
        //Определяем пользователя кц



        catSd = MediaPlayer.create(this, R.raw.numsdescp);
        endlvl = MediaPlayer.create(this, R.raw.complete);
        truesd = MediaPlayer.create(this, R.raw.truesd);
        falsesd = MediaPlayer.create(this, R.raw.falsesd);


        //Игра на весь икран - нч
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Игра на весь икран - кц
        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        catSd.start();
        //Кнопка закрытия окна - нч
        TextView btnclose = (TextView) dialog.findViewById(R.id.button_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Math_1.this, Math.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();
                catSd.stop();
            }
        });
        //Кнопка закрытия окна - кц
        //Продолжить - нч
        Button btncontinue = (Button) dialog.findViewById(R.id.buttoncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catSd.stop();
                dialog.dismiss();
                stopwatch.startChronometer();
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

        dialogHelp.setContentView(layout);
        dialogHelp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogHelp
                .getWindow()
                .setLayout((int) (displayRectangle.width()), (int) (displayRectangle.height() * 0.5));
        dialogHelp.setCancelable(false);
        final VideoView videoView = (VideoView) dialogHelp.findViewById(R.id.vidHelp);
        Uri numVideoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nums);
        videoView.setVideoURI(numVideoUri);

        Button but_cont = (Button) dialogHelp.findViewById(R.id.buttoncont);
        but_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    videoView.pause();
                    dialogHelp.dismiss();
                } catch (Exception e) {

                }
            }
        });
        Button openVid = (Button) dialogHelp.findViewById(R.id.butOpenVid);
        openVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hq5Jr9StrLM&t=3s"));
                startActivity(i);
            }
        });
        //Диалоговое окно помощь - кц
        //Кнопка помощь - нач
        help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoView.start();
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
        bestTime = (TextView) dialogEnd.findViewById(R.id.bestTime);
        //Продолжить
        Button btncont = (Button) dialogEnd.findViewById(R.id.butcont);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Math_1.this, Math.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                endlvl.stop();
                dialogEnd.dismiss();
            }
        });
        //Удалить рекорды юзера
        Button btndel = (Button) dialogEnd.findViewById(R.id.deleteRec);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectionDel = DBHelper.COLUMN_LVL + " = ?" + " AND " + DBHelper.COLUMN_US_ID + " = ?";
                String[] selectionArgsDel = new String[] {Integer.toString(lvl), Integer.toString(userId)};
                database.delete(DBHelper.TABLE_RECORDS, selectionDel,selectionArgsDel);
            }
        });

        //Диалоговое окно в конце уровня - кц
        //Кнопка назад - нч
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Math_1.this, Math.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

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
        final Animation a = AnimationUtils.loadAnimation(Math_1.this, R.anim.alpha);
        //Анимация - кц
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        numRight = random.nextInt(10);
        //Цикл проверяющий равенство чисел справа и слева - нц
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }


        //Цикл проверяющий равенство чисел справа и слева - кц
        img_right.setImageResource(array.images1[numRight]);
        //Нажатие на левую картинку нч
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
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
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }


                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if (count == 20) {
                        endlvl.start();
                        stopwatch.pauseChronometer();
                        TextView timeTw = (TextView) dialogEnd.findViewById(R.id.time);
                        time = stopwatch.getStringTime();
                        timeTw.setText(time);
                        dbUpdate();
                        bestScore = showBestScore(bestScore,userId);
                        bestTime.setText(bestScore);
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);
                        //Цикл проверяющий равенство чисел справа и слева - нц
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        //Цикл проверяющий равенство чисел справа и слева - кц
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_right.setEnabled(true);

                    }
                }

                return true;
            }
        });
        //Нажатие на левую картинку кц
        //Нажатие на правую картинку нч
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft < numRight) {
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
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_purp);
                        }

                    }
                    if (count == 20) {
                        endlvl.start();
                        stopwatch.pauseChronometer();
                        time = stopwatch.getStringTime();
                        TextView timeTw = (TextView) dialogEnd.findViewById(R.id.time);
                        timeTw.setText(time);
                        dbUpdate();
                        bestScore = showBestScore(bestScore,userId);
                        bestTime.setText(bestScore);
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        numRight = random.nextInt(10);
                        //Цикл проверяющий равенство чисел справа и слева - нц
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        //Цикл проверяющий равенство чисел справа и слева - кц
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        img_left.setEnabled(true);

                    }
                }

                return true;
            }
        });
        //Нажатие на правую картинку кц

    }

    public static void dbUpdate(){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.COLUMN_LVL, lvl);
        contentValues.put(DBHelper.COLUMN_TIME, time);
        contentValues.put(DBHelper.COLUMN_US_ID, userId);
        database.insert(DBHelper.TABLE_RECORDS, null, contentValues);

    }

    public static String showBestScore(String bestTime, int usId){
        String selection = DBHelper.COLUMN_LVL + " = ?" + " AND " + DBHelper.COLUMN_US_ID + " = ?";
        String[] selectionArgs = new String[] {Integer.toString(lvl), Integer.toString(usId)};
        String[] columns = new String[]{"min(time) as time"};
        Cursor c = database.query(DBHelper.TABLE_RECORDS,columns ,selection, selectionArgs, null, null, null);
        if(c.moveToFirst()){
            bestTime = c.getString(c.getColumnIndexOrThrow (DBHelper.COLUMN_TIME));
        }
        c.close();
        return bestTime;
    }

    //Системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Math_1.this, Math.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }
    //Сиситемная кнопка назад - конец


}
