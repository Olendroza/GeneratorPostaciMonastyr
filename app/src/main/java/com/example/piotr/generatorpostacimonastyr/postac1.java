package com.example.piotr.generatorpostacimonastyr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class postac1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postac1);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        final Postac character = new Postac();
        Button[] mainFactorValue =  new Button[8];
        Button[] auxiliaryFactorValue = new Button[6];

        for(int i=0;i<mainFactorValue.length;i++)
        {
            String source ="wspGValue"+Integer.toString(i+1);
            int id = getResources().getIdentifier(source,"id",getPackageName());
            mainFactorValue[i]=findViewById(id);
            mainFactorValue[i].setText(Integer.toString(character.WspolczynnikiGlowne[i]));
        }

        for(int i=0;i<auxiliaryFactorValue.length;i++){
            String source = "wspPValue"+Integer.toString(i+1);
            int id = getResources().getIdentifier(source,"id",getPackageName());
            auxiliaryFactorValue[i]=findViewById(id);
            auxiliaryFactorValue[i].setText(Integer.toString(character.WspolczynnikiPomocnicze[i]));
        }

    }


    }

