package com.example.piotr.generatorpostacimonastyr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class text extends AppCompatActivity {
    String chara;
    String place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Bundle extras = getIntent().getExtras();
        chara = extras.getString("loadedChar");
        place = extras.getString("place");

    }

    public void gotopostac(View view){

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        EditText et = (EditText) findViewById(R.id.textttt);
        String stuff = et.getText().toString();
        Intent intent = new Intent();
        intent.setClass(text.this, postac1.class);
        Bundle extras = getIntent().getExtras();
        String malenames = extras.getString("m");
        String femalenames = extras.getString("f");

        intent.putExtra("m",malenames);
        intent.putExtra("f",femalenames);
        if(stuff.equals(""))
            stuff=".";

        if(place.equals("0")) {
            String[] data = chara.split(",");
            data[0]=stuff;
            chara="";
            for(int i =0;i<data.length;i++)
                chara+=data[i]+",";
            chara = chara.substring(0,chara.length()-1);
           // chara+=";";
            intent.putExtra("loadedChar", chara);
        }
        else{
            String[] data = chara.split(",");
            data[data.length-1]=stuff;
            chara="";
            for(int i =0;i<data.length;i++)
                chara+=data[i]+",";
            chara = chara.substring(0,chara.length()-1);
            // chara+=";";
            intent.putExtra("loadedChar", chara);
        }
        startActivity(intent);
        finish();
    }
}
