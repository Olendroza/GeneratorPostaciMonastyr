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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.CharacterData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu extends AppCompatActivity {

    String characterToLoad = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        LinearLayout linearLayout = findViewById(R.id.loadLayout);
        LinearLayout menuLayout = findViewById(R.id.menuButtonsLayout);
        menuLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

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
        intent.putExtra("m", nameM);
        intent.putExtra("f", nameF);
        intent.putExtra("loadedChar", characterToLoad);
        if(characterToLoad!="")
            characterToLoad="";
        startActivity(intent);
    }

    public void notReady(View view) {
        Toast.makeText(this, "Not ready yet", Toast.LENGTH_SHORT).show();
    }

    public void loadCharacter(View view) {
        setContentView(R.layout.activity_menu);
        SharedPreferences sharedPref = getSharedPreferences("saves",MODE_PRIVATE);
        String record = sharedPref.getString("savedCharacters", "dupa");
        final String[] characterData = record.split(";");

        LinearLayout linearLayout = findViewById(R.id.loadLayout);
        LinearLayout menuLayout = findViewById(R.id.menuButtonsLayout);
        menuLayout.setVisibility(View.GONE);

        RelativeLayout[] recordLayout = new RelativeLayout[characterData.length];
        Button[] deleteButton = new Button[characterData.length];
        Button[] loadButton = new Button[characterData.length];
        TextView[] names = new TextView[characterData.length];

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams deleteButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams loadButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams namesParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        deleteButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        loadButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        namesParams.addRule(RelativeLayout.CENTER_VERTICAL);
        namesParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        for (int i = 0; i < characterData.length; i++) {
            recordLayout[i]=new RelativeLayout(this);
            recordLayout[i].setLayoutParams(params);

            names[i] = new TextView(this);
            names[i].setLayoutParams(namesParams);
            String[] name = characterData[i].split(",");
            names[i].setText(" "+name[0]+" ");
            names[i].setTextColor(Color.parseColor("#FFFFFF"));


            deleteButton[i]=new Button(this);
            deleteButton[i].setLayoutParams(deleteButtonParams);
            deleteButton[i].setText("Usuń");
            deleteButton[i].setId(i+1000);

            deleteButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPref = getSharedPreferences("saves", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    String record = sharedPref.getString("savedCharacters","");
                    String[] savedChars = record.split(";");
                    int number = view.getId();
                    number-=1000;
                    record="";
                        for(int i=0;i<savedChars.length;i++){
                            if(i!=number)
                            record+=savedChars[i]+";";
                        }
                    editor.putString("savedCharacters",record);
                    editor.commit();
                    loadCharacter(view.getRootView());
                }
            });

            loadButton[i]= new Button(this);
            loadButton[i].setLayoutParams(loadButtonParams);
            loadButton[i].setText("Wczytaj");
            loadButton[i].setId(i);
            loadButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.activity_menu);
                    SharedPreferences sharedPref = getSharedPreferences("saves",MODE_PRIVATE);
                    String record = sharedPref.getString("savedCharacters", "dupa");
                    final String[] characterData = record.split(";");
                    characterToLoad = characterData[view.getId()];
                    goToPostac1(view);
                }
            });
            recordLayout[i].addView(names[i]);
            recordLayout[i].addView(deleteButton[i]);
            recordLayout[i].addView(loadButton[i]);
            if(name[0]!="")
            {
                linearLayout.addView(recordLayout[i]);
            }
        }


    }

    public void backToMenu(View view){
        LinearLayout linearLayout = findViewById(R.id.loadLayout);
        LinearLayout menuLayout = findViewById(R.id.menuButtonsLayout);
        menuLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }
}
