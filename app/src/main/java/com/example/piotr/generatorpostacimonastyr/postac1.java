package com.example.piotr.generatorpostacimonastyr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class postac1 extends AppCompatActivity {

    Postac character= new Postac("noname","noname");
    Boolean created=false;

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
        Button[] Smotion = new Button [4]; // motion buttons in sword layout
        Button[] rapierA = new Button[7];
        Button[] rapierD = new Button[4];
        Button[] swordA = new Button[4];
        Button[] swordD = new Button[3];
        Button[] closeC = new Button[2];
        Button[] ScloseC = new Button[2];

        TextView name= findViewById(R.id.imie);
        Bundle extras = getIntent().getExtras();
        String temp = extras.getString("loadedChar");

        if(savedInstanceState==null && temp.equals("")){
            String malenames = extras.getString("m");
            String femalenames = extras.getString("f");
            character = new Postac(malenames,femalenames);

            name.setText(character.imie);

            closeC[0]=findViewById(R.id.cCombatAValue);
            closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            closeC[1]=findViewById(R.id.cCombatDValue);
            closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

            ScloseC[0]=findViewById(R.id.ScCombatAValue);
            ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            ScloseC[1]=findViewById(R.id.ScCombatDValue);
            ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));


            motion[0]=findViewById(R.id.SmotionAValue);
            motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            Smotion[0]=findViewById(R.id.SmotionAValue);
            Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            for(int i=1;i<motion.length;i++){
                String source = "motionD" + Integer.toString(i-1)+"Value";
                String source2 = "SmotionD" + Integer.toString(i-1)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                int id2 = getResources().getIdentifier(source2,"id",getPackageName());
                motion[i]=findViewById(id);
                motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
                Smotion[i]=findViewById(id2);
                Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

            }
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
            for(int i=0;i<swordA.length;i++){
                String source = "swordA"+Integer.toString(i)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                rapierA[i]=findViewById(id);
                rapierA[i].setText(Integer.toString(character.AkcjeMieczAtak[i]));
            }
            for(int i=0;i<swordD.length;i++){
                String source = "swordD"+Integer.toString(i)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                rapierD[i]=findViewById(id);
                rapierD[i].setText(Integer.toString(character.AkcjeMieczObrona[i]));
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
        else if(extras.getString("loadedChar")!=""){
            character.Wczytaj(extras.getString("loadedChar"));

            name.setText(character.imie);

            closeC[0]=findViewById(R.id.cCombatAValue);
            closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            closeC[1]=findViewById(R.id.cCombatDValue);
            closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

            ScloseC[0]=findViewById(R.id.ScCombatAValue);
            ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            ScloseC[1]=findViewById(R.id.ScCombatDValue);
            ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));


            motion[0]=findViewById(R.id.SmotionAValue);
            motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            Smotion[0]=findViewById(R.id.SmotionAValue);
            Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            for(int i=1;i<motion.length;i++){
                String source = "motionD" + Integer.toString(i-1)+"Value";
                String source2 = "SmotionD" + Integer.toString(i-1)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                int id2 = getResources().getIdentifier(source2,"id",getPackageName());
                motion[i]=findViewById(id);
                motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
                Smotion[i]=findViewById(id2);
                Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

            }
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
            for(int i=0;i<swordA.length;i++){
                String source = "swordA"+Integer.toString(i)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                rapierA[i]=findViewById(id);
                rapierA[i].setText(Integer.toString(character.AkcjeMieczAtak[i]));
            }
            for(int i=0;i<swordD.length;i++){
                String source = "swordD"+Integer.toString(i)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                rapierD[i]=findViewById(id);
                rapierD[i].setText(Integer.toString(character.AkcjeMieczObrona[i]));
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
        else{
                character.imie = savedInstanceState.getString("name");

                character.SumaWspolczynnikowGlownych = savedInstanceState.getInt("factorsSum");
                for(int i=0;i<character.WspolczynnikiGlowne.length;i++)
                    character.WspolczynnikiGlowne[i]= savedInstanceState.getInt("mainFactors"+Integer.toString(i));
                for(int i=0;i<character.WspolczynnikiPomocnicze.length;i++)
                    character.WspolczynnikiPomocnicze[i]= savedInstanceState.getInt("auxiliaryFactors"+Integer.toString(i));
                for(int i=0;i<character.Umiejetnosci.length;i++)
                    character.Umiejetnosci[i]= savedInstanceState.getInt("abi"+Integer.toString(i));
                for(int i=0;i<character.AkcjeRuch.length;i++)
                    character.AkcjeRuch[i]= savedInstanceState.getInt("actionMove"+Integer.toString(i));
                for(int i=0;i<character.AkcjeZwarcie.length;i++)
                    character.AkcjeZwarcie[i]= savedInstanceState.getInt("actionCloseCombat"+Integer.toString(i));
                for(int i=0;i<character.AkcjeRapierAtak.length;i++)
                    character.AkcjeRapierAtak[i]= savedInstanceState.getInt("actionRapierAttack"+Integer.toString(i));
                for(int i=0;i<character.AkcjeRapierObrona.length;i++)
                    character.AkcjeRapierObrona[i]= savedInstanceState.getInt("actionRapierDefence"+Integer.toString(i));
                for(int i=0;i<character.AkcjeMieczAtak.length;i++)
                    character.AkcjeMieczAtak[i]= savedInstanceState.getInt("actionSwordAttack"+Integer.toString(i));
                for(int i=0;i<character.AkcjeMieczObrona.length;i++)
                    character.AkcjeMieczObrona[i]= savedInstanceState.getInt("actionSwordDefence"+Integer.toString(i));

                name.setText(character.imie);
                closeC[0]=findViewById(R.id.cCombatAValue);
                closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
                closeC[1]=findViewById(R.id.cCombatDValue);
                closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

                ScloseC[0]=findViewById(R.id.ScCombatAValue);
                ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
                ScloseC[1]=findViewById(R.id.ScCombatDValue);
                ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));


                motion[0]=findViewById(R.id.SmotionAValue);
                motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
                Smotion[0]=findViewById(R.id.SmotionAValue);
                Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
                for(int i=1;i<motion.length;i++){
                    String source = "motionD" + Integer.toString(i-1)+"Value";
                    String source2 = "SmotionD" + Integer.toString(i-1)+"Value";
                    int id = getResources().getIdentifier(source,"id",getPackageName());
                    int id2 = getResources().getIdentifier(source2,"id",getPackageName());
                    motion[i]=findViewById(id);
                    motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
                    Smotion[i]=findViewById(id2);
                    Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

                }
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
                for(int i=0;i<swordA.length;i++){
                    String source = "swordA"+Integer.toString(i)+"Value";
                    int id = getResources().getIdentifier(source,"id",getPackageName());
                    rapierA[i]=findViewById(id);
                    rapierA[i].setText(Integer.toString(character.AkcjeMieczAtak[i]));
                }
                for(int i=0;i<swordD.length;i++){
                    String source = "swordD"+Integer.toString(i)+"Value";
                    int id = getResources().getIdentifier(source,"id",getPackageName());
                    rapierD[i]=findViewById(id);
                    rapierD[i].setText(Integer.toString(character.AkcjeMieczObrona[i]));
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
        }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("name", character.imie);
        outState.putInt("factorsSum",character.SumaWspolczynnikowGlownych);
        for(int i=0;i<character.WspolczynnikiGlowne.length;i++)
            outState.putInt("mainFactors"+Integer.toString(i),character.WspolczynnikiGlowne[i]);
        for(int i=0;i<character.WspolczynnikiPomocnicze.length;i++)
            outState.putInt("auxiliaryFactors"+Integer.toString(i),character.WspolczynnikiPomocnicze[i]);
        for(int i=0;i<character.Umiejetnosci.length;i++)
            outState.putInt("abi"+Integer.toString(i),character.Umiejetnosci[i]);
        for(int i=0;i<character.AkcjeRuch.length;i++)
            outState.putInt("actionMove"+Integer.toString(i),character.AkcjeRuch[i]);
        for(int i=0;i<character.AkcjeZwarcie.length;i++)
            outState.putInt("actionCloseCombat"+Integer.toString(i),character.AkcjeZwarcie[i]);
        for(int i=0;i<character.AkcjeRapierAtak.length;i++)
            outState.putInt("actionRapierAttack"+Integer.toString(i),character.AkcjeRapierAtak[i]);
        for(int i=0;i<character.AkcjeRapierObrona.length;i++)
            outState.putInt("actionRapierDefence"+Integer.toString(i),character.AkcjeRapierObrona[i]);
        for(int i=0;i<character.AkcjeMieczAtak.length;i++)
            outState.putInt("actionSwordAttack"+Integer.toString(i),character.AkcjeMieczAtak[i]);
        for(int i=0;i<character.AkcjeMieczObrona.length;i++)
            outState.putInt("actionSwordDefence"+Integer.toString(i),character.AkcjeMieczObrona[i]);


        super.onSaveInstanceState(outState);
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
            else if(id.contains("cCombatA")) {
                this.character.AkcjeZwarcie[0]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeZwarcie[0]));
            }
            else if(id.contains("cCombatD")) {
                this.character.AkcjeZwarcie[1]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeZwarcie[1]));
            }
            else if(id.contains("motion")){
                if(id.contains("motionA")){
                    this.character.AkcjeRuch[0]++;
                    buttonClicked.setText(Integer.toString(this.character.AkcjeRuch[0]));
                }
                else{
                    id=id.replace("Value","");
                    String numberString = id.substring(id.length()-1);
                    int numberOfaction = Integer.parseInt(numberString)+1;
                    this.character.AkcjeRuch[numberOfaction]++;
                    buttonClicked.setText(Integer.toString(this.character.AkcjeRuch[numberOfaction]));
                }
            }
            else if(id.contains("rapier"))
            {
               if(id.contains("rapierA")){
                   id=id.replace("Value","");
                   String numberString = id.substring(id.length()-1);
                   int numberOfmove = Integer.parseInt(numberString);
                   this.character.AkcjeRapierAtak[numberOfmove]++;
                   buttonClicked.setText(Integer.toString(this.character.AkcjeRapierAtak[numberOfmove]));
               }
               else{
                   id=id.replace("Value","");
                   String numberString = id.substring(id.length()-1);
                   int numberOfmove = Integer.parseInt(numberString);
                   this.character.AkcjeRapierObrona[numberOfmove]++;
                   buttonClicked.setText(Integer.toString(this.character.AkcjeRapierObrona[numberOfmove]));
               }
            }
            else if(id.contains("sword"))
            {
                if(id.contains("swordA")){
                    id=id.replace("Value","");
                    String numberString = id.substring(id.length()-1);
                    int numberOfmove = Integer.parseInt(numberString);
                    this.character.AkcjeMieczAtak[numberOfmove]++;
                    buttonClicked.setText(Integer.toString(this.character.AkcjeMieczAtak[numberOfmove]));
                }
                else{
                    id=id.replace("Value","");
                    String numberString = id.substring(id.length()-1);
                    int numberOfmove = Integer.parseInt(numberString);
                    this.character.AkcjeMieczObrona[numberOfmove]++;
                    buttonClicked.setText(Integer.toString(this.character.AkcjeMieczObrona[numberOfmove]));
                }
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
        else if(id.contains("cCombatA")){
            newId = id+"Value";
            buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
            this.character.AkcjeZwarcie[0]--;
            buttonValue.setText(Integer.toString(this.character.AkcjeZwarcie[0]));
        }
        else if(id.contains("cCombatD")) {
            newId = id+"Value";
            buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
            this.character.AkcjeZwarcie[1]--;
            buttonValue.setText(Integer.toString(this.character.AkcjeZwarcie[1]));
        }
        else if(id.contains("motion")){
            if(id.contains("motionA")){
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                this.character.AkcjeRuch[0]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRuch[0]));
            }
            else{
                String numberString = id.substring(id.length()-1);
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                int numberOfaction = Integer.parseInt(numberString)+1;
                this.character.AkcjeRuch[numberOfaction]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRuch[numberOfaction]));
            }
        }
        else if(id.contains("rapier"))
        {
            if(id.contains("rapierA")){
                id=id.replace("Value","");
                String numberString = id.substring(id.length()-1);
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierAtak[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRapierAtak[numberOfmove]));
            }
            else{
                id=id.replace("Value","");
                String numberString = id.substring(id.length()-1);
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierObrona[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRapierObrona[numberOfmove]));
            }
        }
        else if(id.contains("sword"))
        {
            if(id.contains("swordA")){
                id=id.replace("Value","");
                String numberString = id.substring(id.length()-1);
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczAtak[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeMieczAtak[numberOfmove]));
            }
            else{
                id=id.replace("Value","");
                String numberString = id.substring(id.length()-1);
                newId = id+"Value";
                buttonValue = findViewById(getResources().getIdentifier(newId,"id",getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczObrona[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeMieczObrona[numberOfmove]));
            }
        }


    }
    public void changeViev(View view){
        LinearLayout abiLayout = findViewById(R.id.abilitiesLayout);
        LinearLayout rapierLayout = findViewById(R.id.rapierLayout);
        LinearLayout swordLayout = findViewById(R.id.swordLayout);
        Button buttonClicked = findViewById(view.getId());
        String idd = view.getResources().getResourceName(view.getId());

        if(idd.contains("Sword")){
            abiLayout.setVisibility(View.GONE);
            rapierLayout.setVisibility(View.GONE);
            swordLayout.setVisibility(View.VISIBLE);

            Button[] Smotion = new Button [4]; // motion buttons in sword layout
            Button[] ScloseC = new Button[2];

            ScloseC[0]=findViewById(R.id.ScCombatAValue);
            ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            ScloseC[1]=findViewById(R.id.ScCombatDValue);
            ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

            Smotion[0]=findViewById(R.id.SmotionAValue);
            Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            for(int i=1;i<Smotion.length;i++){
                String source2 = "SmotionD" + Integer.toString(i-1)+"Value";
                int id2 = getResources().getIdentifier(source2,"id",getPackageName());
                Smotion[i]=findViewById(id2);
                Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

            }
        }
        else if(idd.contains("Abi")){
            abiLayout.setVisibility(View.VISIBLE);
            rapierLayout.setVisibility(View.GONE);
            swordLayout.setVisibility(View.GONE);
        }
        else if(idd.contains("Rapier")){
            abiLayout.setVisibility(View.GONE);
            rapierLayout.setVisibility(View.VISIBLE);
            swordLayout.setVisibility(View.GONE);

            Button[] motion = new Button[4];
            Button[] closeC = new Button[2];

            closeC[0]=findViewById(R.id.cCombatAValue);
            closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
            closeC[1]=findViewById(R.id.cCombatDValue);
            closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

            motion[0]=findViewById(R.id.SmotionAValue);
            motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
            for(int i=1;i<motion.length;i++){
                String source = "motionD" + Integer.toString(i-1)+"Value";
                int id = getResources().getIdentifier(source,"id",getPackageName());
                motion[i]=findViewById(id);
                motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
            }
        }
    }
    public void saveCharacter(View view){

        SharedPreferences sharedPref = getSharedPreferences("saves", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String record = sharedPref.getString("savedCharacters","");
        String[] savedChars = record.split(";");
        boolean saveExsist=false;
        for(int i=0; i<savedChars.length;i++){
            String[] savedCharParams = savedChars[i].split(",");
            if(savedCharParams[0].equals(character.imie))
            {
                savedChars[i]=character.Zapisz();
                saveExsist = true;
            }
        }
        if(!saveExsist)
            record += character.Zapisz();
        else{
            record="";
            for(int i=0;i<savedChars.length;i++)
                record+=savedChars[i]+";";
        }
        editor.putString("savedCharacters",record);
        editor.commit();
    }
}

