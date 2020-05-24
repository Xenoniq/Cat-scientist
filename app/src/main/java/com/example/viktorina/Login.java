package com.example.viktorina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText etUser;
    DBHelper dbHelper;
    public static SQLiteDatabase database;
    public static UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = (EditText)findViewById(R.id.userName);
        dbHelper = new DBHelper(this);
        Button buttStart = (Button)findViewById(R.id.buttonStart);
        Button buttDelete = (Button)findViewById(R.id.buttonDelete);
        database = dbHelper.getWritableDatabase();
        buttStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo = new UserInfo();
                userInfo.userName = etUser.getText().toString();
                ContentValues contentValues = new ContentValues();
                try{

                contentValues.put(DBHelper.COLUMN_NAME, userInfo.userName);
                database.insert(DBHelper.TABLE_USERS, null, contentValues);
                } catch (Exception e){

                }

                try {
                    Intent intent = new Intent(Login.this, Categories.class);
                    startActivity(intent);
                } catch (Exception e) {

                }

            }
        });

        buttDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete(DBHelper.TABLE_USERS, null,null);
                database.delete(DBHelper.TABLE_RECORDS, null,null);

            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
