package com.example.piotr.generatorpostacimonastyr;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void goToPostac1(View view) {


        String nameF = "";
        try {
            InputStream is = getAssets().open("imK");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            nameF = new String(buffer);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String nameM = "";
        try {
            InputStream is = getAssets().open("imM");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            nameM = new String(buffer);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setClass(Menu.this, postac1.class);
        intent.putExtra("m",nameM);
        intent.putExtra("f",nameF);
        startActivity(intent);
    }
    public void notReady(View view) {
        Toast.makeText(this, "Not ready yet", Toast.LENGTH_SHORT).show();
    }
}
