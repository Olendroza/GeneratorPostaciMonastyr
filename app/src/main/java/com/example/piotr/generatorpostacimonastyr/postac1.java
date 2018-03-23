package com.example.piotr.generatorpostacimonastyr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class postac1 extends AppCompatActivity {


    Postac character = new Postac();
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

        Button[] mainFactorValue = new Button[8];
        Button[] auxiliaryFactorValue = new Button[6];

            for (int i = 0; i < mainFactorValue.length; i++) {
                String source = "wspGValue" + Integer.toString(i + 1);
                int id = getResources().getIdentifier(source, "id", getPackageName());
                mainFactorValue[i] = findViewById(id);
                mainFactorValue[i].setText(Integer.toString(character.WspolczynnikiGlowne[i]));
            }

            for (int i = 0; i < auxiliaryFactorValue.length; i++) {
                String source = "wspPValue" + Integer.toString(i + 1);
                int id = getResources().getIdentifier(source, "id", getPackageName());
                auxiliaryFactorValue[i] = findViewById(id);
                auxiliaryFactorValue[i].setText(Integer.toString(character.WspolczynnikiPomocnicze[i]));
            }

        }
        public void addOne(View view){
        Button buttonClicked = findViewById(view.getId());
        String id = view.getResources().getResourceName(view.getId());
            if(id.contains("wspG")){
                int numberOfFactor = Integer.parseInt(id.substring(id.length()-1))-1;
                this.character.WspolczynnikiGlowne[numberOfFactor]++;
                buttonClicked.setText(Integer.toString(this.character.WspolczynnikiGlowne[numberOfFactor]));
            }

            else if(id.contains("wspP")){
                int numberOfFactor = Integer.parseInt(id.substring(id.length()-1))-1;
                this.character.WspolczynnikiPomocnicze[numberOfFactor]++;
                buttonClicked.setText(Integer.toString(this.character.WspolczynnikiPomocnicze[numberOfFactor]));
            }
            else if(id.contains("abi"))
            {
                String numberString = id.substring(id.length()-3);
                numberString=numberString.replace("i","");
                int numberOfability = Integer.parseInt(numberString);
                this.character.Umiejetnosci[numberOfability]++;
                buttonClicked.setText(Integer.toString(this.character.Umiejetnosci[numberOfability]));
            }
        }
    public void subtractOne(View view){
        String id = view.getResources().getResourceName(view.getId());
        String newId = id.substring(0,id.length()-1)+"Value"+id.substring(id.length()-1);
        Button buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
        if(id.contains("wspG")){
            int numberOfFactor = Integer.parseInt(id.substring(id.length()-1))-1;
            this.character.WspolczynnikiGlowne[numberOfFactor]--;
            buttonValue.setText(Integer.toString(this.character.WspolczynnikiGlowne[numberOfFactor]));
        }

        else if(id.contains("wspP")){
            int numberOfFactor = Integer.parseInt(id.substring(id.length()-1))-1;
            this.character.WspolczynnikiPomocnicze[numberOfFactor]--;
            buttonValue.setText(Integer.toString(this.character.WspolczynnikiPomocnicze[numberOfFactor]));
        }

    }


    }

