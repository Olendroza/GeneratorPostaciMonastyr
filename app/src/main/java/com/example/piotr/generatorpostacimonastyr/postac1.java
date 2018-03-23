package com.example.piotr.generatorpostacimonastyr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
        Button[] abilities = new Button[22];
        Button[] motion = new Button[4];
        Button[] rapierA = new Button[7];
        Button[] rapierD = new Button[4];
        Button[] closeC = new Button[2];

        closeC[0]=findViewById(R.id.cCombatA);
        closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));

        closeC[1]=findViewById(R.id.cCombatD);
        closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

        motion[0]=findViewById(R.id.motionAValue);
        motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
        for(int i=1;i<motion.length;i++){
            String source = "motionD" + Integer.toString(i-1)+"Value";
            int id = getResources().getIdentifier(source,"id",getPackageName());
            motion[i]=findViewById(id);
            motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
        }
        //<!--debagowac-->
        for(int i=0;i<rapierA.length;i++){
            String source = "rapierA"+Integer.toString(i)+"Value";
            int id = getResources().getIdentifier(source,"id",getPackageName());
            rapierA[i]=findViewById(id);
            rapierA[i].setText(Integer.toString(character.AkcjeRapierAtak[i]));
        }
        for(int i=0;i<rapierD.length;i++){
            String source = "rapierD"+Integer.toString(i)+"Value";
            int id = getResources().getIdentifier(source,"id",getPackageName());
            rapierD[i]=findViewById(id);
            rapierD[i].setText(Integer.toString(character.AkcjeRapierObrona[i]));
        }
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
            for(int i=0;i<abilities.length;i++){
                String source = "abiValue"+ Integer.toString(i);
                int id = getResources().getIdentifier(source,"id",getPackageName());
                abilities[i]=findViewById(id);
                abilities[i].setText(Integer.toString(character.Umiejetnosci[i]));
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
                String numberString = id.substring(id.length()-2);
                numberString=numberString.replace("u","");
                numberString=numberString.replace("e","");
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
        else if(id.contains("abi")){
            String numberString = id.substring(id.length()-3);
            numberString=numberString.replace("i","");
            numberString=numberString.replace("b","");
            String abiId = id.substring(0,id.length()-numberString.length())+"Value"+numberString;
            buttonValue=findViewById(getResources().getIdentifier(abiId,"id",getPackageName()));
            int numberOfability = Integer.parseInt(numberString);

            this.character.Umiejetnosci[numberOfability]--;
            buttonValue.setText(Integer.toString(this.character.Umiejetnosci[numberOfability]));
        }

    }
    public void changeViev(View view){
        LinearLayout abiLayout = findViewById(R.id.abilitiesLayout);
        LinearLayout rapierLayout = findViewById(R.id.rapierLayout);
        if(abiLayout.getVisibility()==View.GONE){
            abiLayout.setVisibility(View.VISIBLE);
            rapierLayout.setVisibility(View.GONE);
        }
        else {
            abiLayout.setVisibility(View.GONE);
            rapierLayout.setVisibility(View.VISIBLE);
        }
    }


    }

