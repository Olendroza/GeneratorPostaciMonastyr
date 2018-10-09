package com.example.piotr.generatorpostacimonastyr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.os.*;

public class postac1 extends AppCompatActivity{

    Postac character = new Postac("noname", "noname");
    List quickLoadList = new ArrayList();
    int quickLoadNumber = -1;
    boolean qlSaved = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_postac1);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Context context = getBaseContext();
        Bundle extras = getIntent().getExtras();


        String temp = extras.getString("loadedChar");


        if (savedInstanceState == null && temp.equals("")) {
            String malenames = extras.getString("m");
            String femalenames = extras.getString("f");
            character = new Postac(malenames, femalenames);
            characterInitiator(decorView);
        } else if ((savedInstanceState == null && !temp.equals(""))) {
            character.Wczytaj(extras.getString("loadedChar"));

            characterInitiator(decorView);

        } else {
            character.Wczytaj(savedInstanceState.getString("save"));
            characterInitiator(decorView);
        }

        //initiate char
        View qlMenuButton = findViewById(R.id.QLmenu);
       qlMenuButton.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View view) {
               QLSavesMenu(view);
               return true;
           }
       });
       //QL menu listner
        View vievMenuButton = findViewById(R.id.viewButton);
        vievMenuButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button thisButton = (Button) findViewById(R.id.viewButton);
                thisButton.setTag("1");
                changeViewMenu(view);
                return true;
            }
        });
        //change view menu listner

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("save",character.Zapisz());
        super.onSaveInstanceState(outState);
    }
    public void characterInitiator(View view) {
        Button[] mainFactorValue = new Button[8];
        Button[] auxiliaryFactorValue = new Button[6];
        Button[] abilities = new Button[22];
        Button[] motion = new Button[4];
        Button[] Smotion = new Button[4]; // motion buttons in sword layout
        Button[] rapierA = new Button[7];
        Button[] rapierD = new Button[4];
        Button[] swordA = new Button[4];
        Button[] swordD = new Button[3];
        Button[] closeC = new Button[2];
        Button[] ScloseC = new Button[2];

        TextView name = findViewById(R.id.imie);
        Bundle extras = getIntent().getExtras();
        Button note = (Button) findViewById(R.id.note);
        note.setText(character.notka);
        name.setText(character.imie);

        closeC[0] = findViewById(R.id.cCombatAValue);
        closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
        closeC[1] = findViewById(R.id.cCombatDValue);
        closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

        ScloseC[0] = findViewById(R.id.ScCombatAValue);
        ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
        ScloseC[1] = findViewById(R.id.ScCombatDValue);
        ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));


        motion[0] = findViewById(R.id.SmotionAValue);
        motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
        Smotion[0] = findViewById(R.id.SmotionAValue);
        Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
        for (int i = 1; i < motion.length; i++) {
            String source = "motionD" + Integer.toString(i - 1) + "Value";
            String source2 = "SmotionD" + Integer.toString(i - 1) + "Value";
            int id = getResources().getIdentifier(source, "id", getPackageName());
            int id2 = getResources().getIdentifier(source2, "id", getPackageName());
            motion[i] = findViewById(id);
            motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
            Smotion[i] = findViewById(id2);
            Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

        }
        for (int i = 0; i < rapierA.length; i++) {
            String source = "rapierA" + Integer.toString(i) + "Value";
            int id = getResources().getIdentifier(source, "id", getPackageName());
            rapierA[i] = findViewById(id);
            rapierA[i].setText(Integer.toString(character.AkcjeRapierAtak[i]));
        }
        for (int i = 0; i < rapierD.length; i++) {
            String source = "rapierD" + Integer.toString(i) + "Value";
            int id = getResources().getIdentifier(source, "id", getPackageName());
            rapierD[i] = findViewById(id);
            rapierD[i].setText(Integer.toString(character.AkcjeRapierObrona[i]));
        }
        for (int i = 0; i < swordA.length; i++) {
            String source = "swordA" + Integer.toString(i) + "Value";
            int id = getResources().getIdentifier(source, "id", getPackageName());
            rapierA[i] = findViewById(id);
            rapierA[i].setText(Integer.toString(character.AkcjeMieczAtak[i]));
        }
        for (int i = 0; i < swordD.length; i++) {
            String source = "swordD" + Integer.toString(i) + "Value";
            int id = getResources().getIdentifier(source, "id", getPackageName());
            rapierD[i] = findViewById(id);
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
        for (int i = 0; i < abilities.length; i++) {
            String source = "abiValue" + Integer.toString(i);
            int id = getResources().getIdentifier(source, "id", getPackageName());
            abilities[i] = findViewById(id);
            abilities[i].setText(Integer.toString(character.Umiejetnosci[i]));
        }
        Button woundsButton = findViewById(R.id.ranyValue);
        Button staminaButton = findViewById(R.id.wspGValue1);
        woundsButton.setText(Integer.toString(character.Rany));
        if(character.Rany<0){
            woundsButton.setTextColor(Color.parseColor("#FF0000"));
        }
        else if(character.Rany>0){
            woundsButton.setTextColor(Color.parseColor("#008000"));
        }
        else{
            woundsButton.setTextColor(Color.parseColor("#000000"));
        }

        if(character.WspolczynnikiGlowne[0]<0){
            staminaButton.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            staminaButton.setTextColor(Color.parseColor("#000000"));
        }


    }

    public void QuickLoad(View view){
        boolean replaced = false;
        String record = character.Zapisz();
        for(int i =0;i<quickLoadList.size();i++)
        {
            String[] recordData = record.split(",");
            String load = (String)quickLoadList.get(i);
            String[] loadData = load.split(",");
            if(recordData[0].equals(loadData[0]))
            {
                quickLoadList.set(i,record);
                replaced = true;
            }
        }
        if(replaced==false) {
            quickLoadNumber++;
            quickLoadList.add(record);
        }
        if(qlSaved==false)
        {
            qlSaved = true;
            Button qlButton = (Button) findViewById(R.id.QLmenu);
            qlButton.setText("Szybki zapis \n Zapisano");
        }
        else{
            qlSaved = false;
            Button qlButton = (Button) findViewById(R.id.QLmenu);
            qlButton.setText("Szybki zapis \n Nie zapisana");
            qlButton.setText(Integer.toString(quickLoadNumber));

            quickLoadList.remove(quickLoadNumber);
            quickLoadNumber--;
        }
    }
    public void QLleft(View view){
       // QLdots(view);
        if(quickLoadNumber>0)
        {
            quickLoadNumber--;
            character.Wczytaj((String)quickLoadList.get(quickLoadNumber));
            characterInitiator(view);
        }
        else
            Toast.makeText(this, "Brak wcześniejszych zapisanych postaci", Toast.LENGTH_SHORT).show();


    }
    public void QLRight(View view){
      //  QLdots(view);
        if(quickLoadNumber!=quickLoadList.size()-1) {
            quickLoadNumber++;
            character.Wczytaj((String) quickLoadList.get(quickLoadNumber));
            characterInitiator(view);
        }
        else{
            generate(view);
            qlSaved=false;

            Button qlButton = (Button) findViewById(R.id.QLmenu);
            qlButton.setText("Szybki zapis \n Nie zapisana");
        }
    }
    public void QLSavesMenu(View view){
       // QLdots(view);
        if(quickLoadList.size()>0) {
            //Button thisButton = (Button) findViewById(R.id.QLmenu);
            //thisButton.setEnabled(false);

            RelativeLayout characterLayout = findViewById(R.id.characterLayout);
            final LinearLayout savesLayout = new LinearLayout(this);
            savesLayout.setBackgroundColor(Color.parseColor("#f2eae3"));

            final ScrollView scroll = new ScrollView(this);

            RelativeLayout.LayoutParams savesLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams scrollLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            scrollLayoutParams.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            scrollLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            savesLayout.setOrientation(LinearLayout.VERTICAL);
            savesLayout.setLayoutParams(savesLayoutParams);
            scroll.setLayoutParams(scrollLayoutParams);

            RelativeLayout[] recordLayout = new RelativeLayout[quickLoadList.size()];
            Button[] deleteButton = new Button[quickLoadList.size()];
            Button[] loadButton = new Button[quickLoadList.size()];

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(this.getResources().getDisplayMetrics().widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams deleteButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams loadButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            deleteButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            loadButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);


            int savesLayoutid = 11001;
            final Button exitButton = new Button(this);
            int exitButtonid1 = 11002;
            scroll.setId(savesLayoutid);
            exitButton.setId(exitButtonid1);
            RelativeLayout.LayoutParams exitParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams.addRule(RelativeLayout.ABOVE, savesLayoutid);
            exitButton.setLayoutParams(exitParams);
            exitButton.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton1 = new Button(this);
            int exitButtonid2 = 11003;
            exitButton1.setId(exitButtonid2);
            RelativeLayout.LayoutParams exitParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams1.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams1.addRule(RelativeLayout.RIGHT_OF, savesLayoutid);
            exitButton1.setLayoutParams(exitParams1);
            exitButton1.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton2 = new Button(this);
            int exitButtonid3 = 11004;
            exitButton2.setId(exitButtonid3);
            RelativeLayout.LayoutParams exitParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams2.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams2.addRule(RelativeLayout.LEFT_OF, savesLayoutid);
            exitButton2.setLayoutParams(exitParams2);
            exitButton2.setBackgroundColor(Color.parseColor("#00ff0000"));

            final Button exitButton3 = new Button(this);
            int exitButtonid4 = 11005;
            exitButton3.setId(exitButtonid4);
            RelativeLayout.LayoutParams exitParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams3.addRule(RelativeLayout.BELOW, savesLayoutid);
            exitButton3.setLayoutParams(exitParams3);
            exitButton3.setBackgroundColor(Color.parseColor("#00ff0000"));


            for (int i = 0; i < quickLoadList.size(); i++) {

                recordLayout[i] = new RelativeLayout(this);
               // recordLayout[i].setId(4000 + i);
                recordLayout[i].setLayoutParams(params);

                String data = (String) quickLoadList.get(i);
                String[] characterValues = data.split(",");

                loadButton[i] = new Button(this);
                loadButton[i].setLayoutParams(loadButtonParams);
                loadButton[i].setText(characterValues[0]);
                loadButton[i].setId(2000 + i);
                loadButton[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = view.getId();
                        number -= 2000;
                        character.Wczytaj((String) quickLoadList.get(number));

                        RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                        Button thisButton = (Button) findViewById(R.id.QLmenu);
                        thisButton.setEnabled(true);
                        characterLayout.removeView(scroll);
                        characterLayout.removeView(savesLayout);
                        characterLayout.removeView(exitButton);
                        characterLayout.removeView(exitButton1);
                        characterLayout.removeView(exitButton2);
                        characterLayout.removeView(exitButton3);

                        int viewLayout =22001;
                        int exitButton1 = 22002;
                        int exitButton2 = 22003;
                        int exitButton3 = 22004;

                        characterLayout.removeView(findViewById(viewLayout));
                        characterLayout.removeView(findViewById(exitButton1));
                        characterLayout.removeView(findViewById(exitButton2));
                        characterLayout.removeView(findViewById(exitButton3));

                        characterInitiator(view.getRootView());

                    }
                });
                deleteButton[i] = new Button(this);
                deleteButton[i].setLayoutParams(deleteButtonParams);
                deleteButton[i].setText("usuń");
                deleteButton[i].setId(3000 + i);
                deleteButton[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = view.getId();
                        number -= 3000;
                        quickLoadList.remove(number);
                        if(quickLoadList.size()!=0)
                            quickLoadNumber = 0;

                        RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                        characterLayout.removeView(scroll);
                        characterLayout.removeView(savesLayout);
                        characterLayout.removeView(exitButton);
                        characterLayout.removeView(exitButton1);
                        characterLayout.removeView(exitButton2);
                        characterLayout.removeView(exitButton3);

                        Button menuButton = (Button) findViewById(R.id.QLmenu);
                        menuButton.setEnabled(true);
                        int viewLayout =22001;
                        int exitButton1 = 22002;
                        int exitButton2 = 22003;
                        int exitButton3 = 22004;

                        characterLayout.removeView(findViewById(viewLayout));
                        characterLayout.removeView(findViewById(exitButton1));
                        characterLayout.removeView(findViewById(exitButton2));
                        characterLayout.removeView(findViewById(exitButton3));
                        QLSavesMenu(view.getRootView());
                    }
                });
                recordLayout[i].addView(deleteButton[i]);
                recordLayout[i].addView(loadButton[i]);

                savesLayout.addView(recordLayout[i]);

            }

            scroll.setId(savesLayoutid);
            scroll.addView(savesLayout);
            characterLayout.addView(scroll);


            characterLayout.addView(exitButton);
            characterLayout.addView(exitButton1);
            characterLayout.addView(exitButton2);
            characterLayout.addView(exitButton3);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(scroll);

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout =22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    Button menuButton = (Button) findViewById(R.id.QLmenu);
                    menuButton.setEnabled(true);

                }
            });
            exitButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(scroll);

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout =22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    Button menuButton = (Button) findViewById(R.id.QLmenu);
                    menuButton.setEnabled(true);
                }
            });
            exitButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(scroll);

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout =22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    Button menuButton = (Button) findViewById(R.id.QLmenu);
                    menuButton.setEnabled(true);
                }
            });
            exitButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(scroll);

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);
                    int viewLayout =22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    Button menuButton = (Button) findViewById(R.id.QLmenu);
                    menuButton.setEnabled(true);
                }
            });

            int viewLayout =22001;
             exitButtonid1 = 22002;
             exitButtonid2 = 22003;
             exitButtonid3 = 22004;

            characterLayout.removeView(findViewById(viewLayout));
            characterLayout.removeView(findViewById(exitButtonid1));
            characterLayout.removeView(findViewById(exitButtonid2));
            characterLayout.removeView(findViewById(exitButtonid3));

        }
        else
            Toast.makeText(this, "Nie ma zapisanych postaci", Toast.LENGTH_SHORT).show();
    }
   /* public void QLdots(View view){
        RelativeLayout characterLayout = findViewById(R.id.characterLayout);
        LinearLayout dotsLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        dotsLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        dotsLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams namesParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        if(quickLoadNumber>0) {
            TextView[] dots =new TextView[quickLoadNumber];
            for (int i = 0; i < quickLoadNumber; i++) {
                dots[i]= new TextView(this);
                dots[i].setLayoutParams(namesParams);
                dots[i].setText(".");
                if (i == quickLoadNumber)
                    dots[i].setTextColor(Color.parseColor("#FF0000"));
                dotsLayout.addView(dots[i]);
            }
            characterLayout.addView(dotsLayout);
        }
    }*/
    public void generate(View view){

        Bundle extras = getIntent().getExtras();
        String malenames = extras.getString("m");
            String femalenames = extras.getString("f");
            character = new Postac(malenames, femalenames);
            characterInitiator(view);

    }
    public void addOne(View view) {
        Button buttonClicked = findViewById(view.getId());
        String id = view.getResources().getResourceName(view.getId());
        if (id.contains("wspG")) {
            int numberOfFactor = Integer.parseInt(id.substring(id.length() - 1)) - 1;
            this.character.WspolczynnikiGlowne[numberOfFactor]++;
            buttonClicked.setText(Integer.toString(this.character.WspolczynnikiGlowne[numberOfFactor]));
        } else if (id.contains("wspP")) {
            int numberOfFactor = Integer.parseInt(id.substring(id.length() - 1)) - 1;
            this.character.WspolczynnikiPomocnicze[numberOfFactor]++;
            buttonClicked.setText(Integer.toString(this.character.WspolczynnikiPomocnicze[numberOfFactor]));
        } else if (id.contains("abi")) {
            String numberString = id.substring(id.length() - 2);
            numberString = numberString.replace("u", "");
            numberString = numberString.replace("e", "");
            int numberOfability = Integer.parseInt(numberString);
            this.character.Umiejetnosci[numberOfability]++;
            buttonClicked.setText(Integer.toString(this.character.Umiejetnosci[numberOfability]));
        } else if (id.contains("cCombatA")) {
            this.character.AkcjeZwarcie[0]++;
            buttonClicked.setText(Integer.toString(this.character.AkcjeZwarcie[0]));
        } else if (id.contains("cCombatD")) {
            this.character.AkcjeZwarcie[1]++;
            buttonClicked.setText(Integer.toString(this.character.AkcjeZwarcie[1]));
        } else if (id.contains("motion")) {
            if (id.contains("motionA")) {
                this.character.AkcjeRuch[0]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeRuch[0]));
            } else {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                int numberOfaction = Integer.parseInt(numberString) + 1;
                this.character.AkcjeRuch[numberOfaction]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeRuch[numberOfaction]));
            }
        } else if (id.contains("rapier")) {
            if (id.contains("rapierA")) {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierAtak[numberOfmove]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeRapierAtak[numberOfmove]));
            } else {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierObrona[numberOfmove]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeRapierObrona[numberOfmove]));
            }
        } else if (id.contains("sword")) {
            if (id.contains("swordA")) {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczAtak[numberOfmove]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeMieczAtak[numberOfmove]));
            } else {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczObrona[numberOfmove]++;
                buttonClicked.setText(Integer.toString(this.character.AkcjeMieczObrona[numberOfmove]));
            }
        }
    }
    public void subtractOne(View view) {
        String id = view.getResources().getResourceName(view.getId());
        String newId = id.substring(0, id.length() - 1) + "Value" + id.substring(id.length() - 1);
        Button buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
        if (id.contains("wspG")) {
            int numberOfFactor = Integer.parseInt(id.substring(id.length() - 1)) - 1;
            this.character.WspolczynnikiGlowne[numberOfFactor]--;
            buttonValue.setText(Integer.toString(this.character.WspolczynnikiGlowne[numberOfFactor]));
        } else if (id.contains("wspP")) {
            int numberOfFactor = Integer.parseInt(id.substring(id.length() - 1)) - 1;
            this.character.WspolczynnikiPomocnicze[numberOfFactor]--;
            buttonValue.setText(Integer.toString(this.character.WspolczynnikiPomocnicze[numberOfFactor]));
        } else if (id.contains("abi")) {
            String numberString = id.substring(id.length() - 3);
            numberString = numberString.replace("i", "");
            numberString = numberString.replace("b", "");
            String abiId = id.substring(0, id.length() - numberString.length()) + "Value" + numberString;
            buttonValue = findViewById(getResources().getIdentifier(abiId, "id", getPackageName()));
            int numberOfability = Integer.parseInt(numberString);

            this.character.Umiejetnosci[numberOfability]--;
            buttonValue.setText(Integer.toString(this.character.Umiejetnosci[numberOfability]));
        } else if (id.contains("cCombatA")) {
            newId = id + "Value";
            buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
            this.character.AkcjeZwarcie[0]--;
            buttonValue.setText(Integer.toString(this.character.AkcjeZwarcie[0]));
        } else if (id.contains("cCombatD")) {
            newId = id + "Value";
            buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
            this.character.AkcjeZwarcie[1]--;
            buttonValue.setText(Integer.toString(this.character.AkcjeZwarcie[1]));
        } else if (id.contains("motion")) {
            if (id.contains("motionA")) {
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                this.character.AkcjeRuch[0]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRuch[0]));
            } else {
                String numberString = id.substring(id.length() - 1);
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                int numberOfaction = Integer.parseInt(numberString) + 1;
                this.character.AkcjeRuch[numberOfaction]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRuch[numberOfaction]));
            }
        } else if (id.contains("rapier")) {
            if (id.contains("rapierA")) {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierAtak[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRapierAtak[numberOfmove]));
            } else {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeRapierObrona[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeRapierObrona[numberOfmove]));
            }
        } else if (id.contains("sword")) {
            if (id.contains("swordA")) {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczAtak[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeMieczAtak[numberOfmove]));
            } else {
                id = id.replace("Value", "");
                String numberString = id.substring(id.length() - 1);
                newId = id + "Value";
                buttonValue = findViewById(getResources().getIdentifier(newId, "id", getPackageName()));
                int numberOfmove = Integer.parseInt(numberString);
                this.character.AkcjeMieczObrona[numberOfmove]--;
                buttonValue.setText(Integer.toString(this.character.AkcjeMieczObrona[numberOfmove]));
            }
        }


    }
    public void changeViev(int numberOfView,boolean menuOn) {
        LinearLayout abiLayout = findViewById(R.id.abilitiesLayout);
        LinearLayout rapierLayout = findViewById(R.id.rapierLayout);
        LinearLayout swordLayout = findViewById(R.id.swordLayout);
        LinearLayout noteLayout = findViewById(R.id.noteLayout);

        if(menuOn==false){
            if(abiLayout.getVisibility()==LinearLayout.VISIBLE){
                abiLayout.setVisibility(LinearLayout.GONE);
                rapierLayout.setVisibility(LinearLayout.GONE);
                swordLayout.setVisibility(LinearLayout.GONE);
                noteLayout.setVisibility(LinearLayout.VISIBLE);
            }
            else if(rapierLayout.getVisibility()==LinearLayout.VISIBLE){
                abiLayout.setVisibility(LinearLayout.GONE);
                rapierLayout.setVisibility(LinearLayout.GONE);
                swordLayout.setVisibility(LinearLayout.VISIBLE);
                noteLayout.setVisibility(LinearLayout.GONE);
            }
            else if(swordLayout.getVisibility()==LinearLayout.VISIBLE){
                abiLayout.setVisibility(LinearLayout.VISIBLE);
                rapierLayout.setVisibility(LinearLayout.GONE);
                swordLayout.setVisibility(LinearLayout.GONE);
                noteLayout.setVisibility(LinearLayout.GONE);
            }
            else{
                abiLayout.setVisibility(LinearLayout.GONE);
                rapierLayout.setVisibility(LinearLayout.VISIBLE);
                swordLayout.setVisibility(LinearLayout.GONE);
                noteLayout.setVisibility(LinearLayout.GONE);
            }
        }
        else {

            if (numberOfView == 1) {
                abiLayout.setVisibility(View.GONE);
                rapierLayout.setVisibility(View.GONE);
                swordLayout.setVisibility(View.VISIBLE);

                Button[] Smotion = new Button[4]; // motion buttons in sword layout
                Button[] ScloseC = new Button[2];

                ScloseC[0] = findViewById(R.id.ScCombatAValue);
                ScloseC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
                ScloseC[1] = findViewById(R.id.ScCombatDValue);
                ScloseC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

                Smotion[0] = findViewById(R.id.SmotionAValue);
                Smotion[0].setText(Integer.toString(character.AkcjeRuch[0]));
                for (int i = 1; i < Smotion.length; i++) {
                    String source2 = "SmotionD" + Integer.toString(i - 1) + "Value";
                    int id2 = getResources().getIdentifier(source2, "id", getPackageName());
                    Smotion[i] = findViewById(id2);
                    Smotion[i].setText(Integer.toString(character.AkcjeRuch[i]));

                }
            } else if (numberOfView == 2) {
                abiLayout.setVisibility(View.VISIBLE);
                rapierLayout.setVisibility(View.GONE);
                swordLayout.setVisibility(View.GONE);
            } else if (numberOfView == 0) {
                abiLayout.setVisibility(View.GONE);
                rapierLayout.setVisibility(View.VISIBLE);
                swordLayout.setVisibility(View.GONE);

                Button[] motion = new Button[4];
                Button[] closeC = new Button[2];

                closeC[0] = findViewById(R.id.cCombatAValue);
                closeC[0].setText(Integer.toString(character.AkcjeZwarcie[0]));
                closeC[1] = findViewById(R.id.cCombatDValue);
                closeC[1].setText(Integer.toString(character.AkcjeZwarcie[1]));

                motion[0] = findViewById(R.id.SmotionAValue);
                motion[0].setText(Integer.toString(character.AkcjeRuch[0]));
                for (int i = 1; i < motion.length; i++) {
                    String source = "motionD" + Integer.toString(i - 1) + "Value";
                    int id = getResources().getIdentifier(source, "id", getPackageName());
                    motion[i] = findViewById(id);
                    motion[i].setText(Integer.toString(character.AkcjeRuch[i]));
                }
                }
            else{
                abiLayout.setVisibility(LinearLayout.GONE);
                rapierLayout.setVisibility(LinearLayout.GONE);
                swordLayout.setVisibility(LinearLayout.GONE);
                noteLayout.setVisibility(LinearLayout.VISIBLE);
            }
        }
    }
    public void changeViewMenu(View view){
        RelativeLayout characterLayout = findViewById(R.id.characterLayout);
        Button thisButton = (Button) findViewById(R.id.viewButton);
        if(thisButton.getTag()=="0"){
            changeViev(0,false);
        }
           else {
            thisButton.setTag("0");
            final LinearLayout CVmenuLayout = new LinearLayout(this);
            CVmenuLayout.setBackgroundColor(Color.parseColor("#f2eae3"));


            RelativeLayout.LayoutParams savesLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            savesLayoutParams.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            savesLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            CVmenuLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            CVmenuLayout.setOrientation(LinearLayout.VERTICAL);
            savesLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            CVmenuLayout.setLayoutParams(savesLayoutParams);

            Button[] buttons = new Button[4];
            buttons[0] = new Button(this);
            buttons[1] = new Button(this);
            buttons[2] = new Button(this);
            buttons[3] = new Button(this);

            RelativeLayout.LayoutParams buttonsParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            int menuLayoutId = 22001;
            CVmenuLayout.setId(menuLayoutId);
            final Button exitButton = new Button(this);
            int exitButtonid = 22002;
            exitButton.setId(exitButtonid);

            RelativeLayout.LayoutParams exitParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams.addRule(RelativeLayout.ABOVE, menuLayoutId);
            exitButton.setLayoutParams(exitParams);
            exitButton.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton1 = new Button(this);
            int exitButtonid1 = 22003;
            exitButton1.setId(exitButtonid1);

            RelativeLayout.LayoutParams exitParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams1.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams1.addRule(RelativeLayout.RIGHT_OF, menuLayoutId);
            exitButton1.setLayoutParams(exitParams1);
            exitButton1.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton2 = new Button(this);
            int exitButtonid3 = 22004;
            exitButton2.setId(exitButtonid3);
            RelativeLayout.LayoutParams exitParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams2.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams2.addRule(RelativeLayout.LEFT_OF, menuLayoutId);
            exitButton2.setLayoutParams(exitParams2);
            exitButton2.setBackgroundColor(Color.parseColor("#00ff0000"));

            final Button exitButton3 = new Button(this);
            int exitButtonid4 = 22005;
            exitButton3.setId(exitButtonid4);
            RelativeLayout.LayoutParams exitParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams3.addRule(RelativeLayout.BELOW, menuLayoutId);
            exitButton3.setLayoutParams(exitParams3);
            exitButton3.setBackgroundColor(Color.parseColor("#00ff0000"));

            buttons[0].setText("Rapier");
            buttons[1].setText("Miecz");
            buttons[2].setText("Umietnosci");
            buttons[3].setText("Notka");

            buttons[0].setLayoutParams(buttonsParams);
            buttons[1].setLayoutParams(buttonsParams);
            buttons[2].setLayoutParams(buttonsParams);
            buttons[3].setLayoutParams(buttonsParams);

            buttons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeViev(0, true);
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });
            buttons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeViev(1, true);
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });
            buttons[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeViev(2, true);
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });
            buttons[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeViev(3, true);
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });

            CVmenuLayout.addView(buttons[0]);
            CVmenuLayout.addView(buttons[1]);
            CVmenuLayout.addView(buttons[2]);
            CVmenuLayout.addView(buttons[3]);

            characterLayout.addView(CVmenuLayout);
            characterLayout.addView(exitButton);
            characterLayout.addView(exitButton1);
            characterLayout.addView(exitButton2);
            characterLayout.addView(exitButton3);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);


                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));


                }
            });
            exitButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);


                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                }
            });
            exitButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);


                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });
            exitButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(CVmenuLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int saveLayout = 11001;
                    int exitButton1 = 11002;
                    int exitButton2 = 11003;
                    int exitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));

                }
            });
            int saveLayout = 11001;
            exitButtonid1 = 11002;
            int exitButtonid2 = 11003;
            exitButtonid3 = 11004;

            characterLayout.removeView(findViewById(saveLayout));
            characterLayout.removeView(findViewById(exitButtonid1));
            characterLayout.removeView(findViewById(exitButtonid2));
            characterLayout.removeView(findViewById(exitButtonid3));
        }

    }
    public void loadCharacter(View view) {
        SharedPreferences sharedPref = getSharedPreferences("saves",MODE_PRIVATE);
        String savedChars = sharedPref.getString("savedCharacters", "");

        String record = savedChars;


        final String[] characterData = record.split(";");

        if (characterData.length > 0) {

            RelativeLayout characterLayout = findViewById(R.id.characterLayout);
            final LinearLayout savesLayout = new LinearLayout(this);
            savesLayout.setBackgroundColor(Color.parseColor("#f2eae3"));

            ScrollView scroll = new ScrollView(this);

            RelativeLayout.LayoutParams scrollLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams savesLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            scrollLayoutParams.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            scrollLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            savesLayout.setOrientation(LinearLayout.VERTICAL);
            savesLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);
            savesLayout.setLayoutParams(savesLayoutParams);
            scroll.setLayoutParams(scrollLayoutParams);

            RelativeLayout[] recordLayout = new RelativeLayout[characterData.length];
            Button[] loadButton = new Button[characterData.length];

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams loadButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);

            final int savesLayoutid = 33001;
            final Button exitButton = new Button(this);
            int exitButtonid1 = 33002;
            exitButton.setId(exitButtonid1);
            RelativeLayout.LayoutParams exitParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams.addRule(RelativeLayout.ABOVE, savesLayoutid);
            exitButton.setLayoutParams(exitParams);
            exitButton.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton1 = new Button(this);
            int exitButtonid2 = 33003;
            exitButton1.setId(exitButtonid2);
            RelativeLayout.LayoutParams exitParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams1.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams1.addRule(RelativeLayout.RIGHT_OF, savesLayoutid);
            exitButton1.setLayoutParams(exitParams1);
            exitButton1.setBackgroundColor(Color.parseColor("#00ff0000"));


            final Button exitButton2 = new Button(this);
            int exitButtonid3 = 33004;
            exitButton2.setId(exitButtonid3);
            RelativeLayout.LayoutParams exitParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams2.addRule(RelativeLayout.ABOVE, R.id.bottomMenuBar);
            exitParams2.addRule(RelativeLayout.LEFT_OF, savesLayoutid);
            exitButton2.setLayoutParams(exitParams2);
            exitButton2.setBackgroundColor(Color.parseColor("#00ff0000"));

            final Button exitButton3 = new Button(this);
            int exitButtonid4 = 33005;
            exitButton3.setId(exitButtonid4);
            RelativeLayout.LayoutParams exitParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            exitParams3.addRule(RelativeLayout.BELOW, savesLayoutid);
            exitButton3.setLayoutParams(exitParams3);
            exitButton3.setBackgroundColor(Color.parseColor("#00ff0000"));


            for (int i = 0; i < characterData.length; i++) {

                recordLayout[i] = new RelativeLayout(this);

                recordLayout[i].setLayoutParams(params);


                String data = (String) characterData[i];
                String[] characterValues = data.split(",");
                if(!characterValues[0].equals(""))
                {
                    loadButton[i] = new Button(this);
                    loadButton[i].setLayoutParams(loadButtonParams);
                    loadButton[i].setText(characterValues[0]);
                    loadButton[i].setId(2000 + i);

                    loadButton[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int number = view.getId();
                            number -= 2000;
                            character.Wczytaj(characterData[number]);

                            RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                            Button thisButton = (Button) findViewById(R.id.QLmenu);
                            thisButton.setEnabled(true);
                            characterLayout.removeView(findViewById(savesLayoutid));
                            characterLayout.removeView(savesLayout);
                            characterLayout.removeView(exitButton);
                            characterLayout.removeView(exitButton1);
                            characterLayout.removeView(exitButton2);
                            characterLayout.removeView(exitButton3);
                            int viewLayout = 22001;
                            int exitButton1 = 22002;
                            int exitButton2 = 22003;
                            int exitButton3 = 22004;

                            characterLayout.removeView(findViewById(viewLayout));
                            characterLayout.removeView(findViewById(exitButton1));
                            characterLayout.removeView(findViewById(exitButton2));
                            characterLayout.removeView(findViewById(exitButton3));


                            characterInitiator(view.getRootView());

                        }
                    });
                    recordLayout[i].addView(loadButton[i]);
                    savesLayout.addView(recordLayout[i]);
                }

            }

            scroll.setId(savesLayoutid);
            scroll.addView(savesLayout);
            characterLayout.addView(scroll);

            characterLayout.addView(exitButton);
            characterLayout.addView(exitButton1);
            characterLayout.addView(exitButton2);
            characterLayout.addView(exitButton3);

            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(findViewById(savesLayoutid));
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout = 22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    int saveLayout = 11001;
                    int saveExitButton1 = 11002;
                    int saveExitButton2 = 11003;
                    int saveExitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(saveExitButton1));
                    characterLayout.removeView(findViewById(saveExitButton2));
                    characterLayout.removeView(findViewById(saveExitButton3));



                }
            });
            exitButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(findViewById(savesLayoutid));

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout = 22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    int saveLayout = 11001;
                    int saveExitButton1 = 11002;
                    int saveExitButton2 = 11003;
                    int saveExitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(saveExitButton1));
                    characterLayout.removeView(findViewById(saveExitButton2));
                    characterLayout.removeView(findViewById(saveExitButton3));
                }
            });
            exitButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(findViewById(savesLayoutid));

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout = 22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    int saveLayout = 11001;
                    int saveExitButton1 = 11002;
                    int saveExitButton2 = 11003;
                    int saveExitButton3 = 11004;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(saveExitButton1));
                    characterLayout.removeView(findViewById(saveExitButton2));
                    characterLayout.removeView(findViewById(saveExitButton3));
                }
            });
            exitButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout characterLayout = (RelativeLayout) findViewById(R.id.characterLayout);
                    characterLayout.removeView(savesLayout);
                    characterLayout.removeView(findViewById(savesLayoutid));

                    characterLayout.removeView(exitButton);
                    characterLayout.removeView(exitButton1);
                    characterLayout.removeView(exitButton2);
                    characterLayout.removeView(exitButton3);

                    int viewLayout = 22001;
                    int exitButton1 = 22002;
                    int exitButton2 = 22003;
                    int exitButton3 = 22004;
                    int exitButton4 = 22005;

                    characterLayout.removeView(findViewById(viewLayout));
                    characterLayout.removeView(findViewById(exitButton1));
                    characterLayout.removeView(findViewById(exitButton2));
                    characterLayout.removeView(findViewById(exitButton3));
                    characterLayout.removeView(findViewById(exitButton4));

                    int saveLayout = 11001;
                    int saveExitButton1 = 11002;
                    int saveExitButton2 = 11003;
                    int saveExitButton3 = 11004;
                    int saveExitButton4 = 11005;

                    characterLayout.removeView(findViewById(saveLayout));
                    characterLayout.removeView(findViewById(saveExitButton1));
                    characterLayout.removeView(findViewById(saveExitButton2));
                    characterLayout.removeView(findViewById(saveExitButton3));
                    characterLayout.removeView(findViewById(saveExitButton4));

                }
            });

            int viewLayout = 22001;
            exitButtonid1 = 22002;
            exitButtonid2 = 22003;
            exitButtonid3 = 22004;

            characterLayout.removeView(findViewById(viewLayout));
            characterLayout.removeView(findViewById(exitButtonid1));
            characterLayout.removeView(findViewById(exitButtonid2));
            characterLayout.removeView(findViewById(exitButtonid3));

            int saveLayout = 11001;
            exitButtonid1 = 11002;
            exitButtonid2 = 11003;
            exitButtonid3 = 11004;

            characterLayout.removeView(findViewById(saveLayout));
            characterLayout.removeView(findViewById(exitButtonid1));
            characterLayout.removeView(findViewById(exitButtonid2));
            characterLayout.removeView(findViewById(exitButtonid3));

        } else
            Toast.makeText(this, "Nie ma zapisanych postaci", Toast.LENGTH_SHORT).show();

    }
    public void saveCharacter(View view) {

        SharedPreferences sharedPref = getSharedPreferences("saves", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String record = sharedPref.getString("savedCharacters", "");
        String[] savedChars = record.split(";");
        boolean saveExsist = false;
        for (int i = 0; i < savedChars.length; i++) {
            String[] savedCharParams = savedChars[i].split(",");
            if (savedCharParams[0].equals(character.imie)) {
                savedChars[i] = character.Zapisz();
                saveExsist = true;
            }
        }
        if (!saveExsist)
            record += character.Zapisz();
        else {
            record = "";
            for (int i = 0; i < savedChars.length; i++)
                record += savedChars[i] + ";";
        }
        editor.putString("savedCharacters", record);
        editor.commit();

        Toast.makeText(this, "Postać " + character.imie + " zapisana", Toast.LENGTH_SHORT).show();

    }
    public void back(View view) {
        Intent intent = new Intent();
        intent.setClass(postac1.this, Menu.class);
        startActivity(intent);
    }
    public void changeName(View view){
        Intent intent = new Intent();
        intent.setClass(postac1.this, text.class);
        Bundle extras = getIntent().getExtras();
        String malenames = extras.getString("m");
        String femalenames = extras.getString("f");

        intent.putExtra("m",malenames);
        intent.putExtra("f",femalenames);
        intent.putExtra("loadedChar", character.Zapisz());
        intent.putExtra("place","0");
        startActivity(intent);
    }
    public void changeNote(View view){
        Intent intent = new Intent();

        intent.setClass(postac1.this, text.class);
        Bundle extras = getIntent().getExtras();
        String malenames = extras.getString("m");
        String femalenames = extras.getString("f");

        intent.putExtra("m",malenames);
        intent.putExtra("f",femalenames);
        intent.putExtra("loadedChar", character.Zapisz());
        intent.putExtra("place","1");
        startActivity(intent);
    }

    public void hurt(View view) {
        Button buttonClicked = findViewById(view.getId());
        String buttonid = view.getResources().getResourceName(view.getId());
        if (buttonid.contains("Value"))
            character.zran(1);
            else
            character.zran(-1);

        characterInitiator(view);
    }
}
