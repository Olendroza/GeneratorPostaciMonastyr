package com.example.piotr.generatorpostacimonastyr;

import android.content.res.AssetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Piotr on 20.03.2018.
 */

public class Postac {

        String imie;
        int SumaWspolczynnikowGlownych;
        int Rany = 0;
        int[] WspolczynnikiGlowne= new int[8];
        int[] WspolczynnikiPomocnicze = new int[6];
        int[] Umiejetnosci = new int[22];
        int[] AkcjeRuch = new int[4];
        int[] AkcjeZwarcie = new int[2];
        int[] AkcjeRapierAtak = new int[7];
        int[] AkcjeRapierObrona = new int[4];
        int[] AkcjeMieczAtak = new int [4];
        int[] AkcjeMieczObrona = new int[3];

        public Postac(String imionaM, String imionaZ) {
            Random generator= new Random();

            int plec= generator.nextInt(2);
            String[] meskieimiona = imionaM.split("\\n");
            String[] damskieimiona = imionaZ.split("\\n");
            if(plec==0)
                this.imie=meskieimiona[generator.nextInt(meskieimiona.length)];
            else
                this.imie=damskieimiona[generator.nextInt(damskieimiona.length)];

            //main coefficients
            for(int i=0;i<this.WspolczynnikiGlowne.length;i++) {
                this.WspolczynnikiGlowne[i] = generator.nextInt(14)+6;
                SumaWspolczynnikowGlownych += WspolczynnikiGlowne[i];
            }

            //auxiliary coefficients
            WspolczynnikiPomocnicze[0]=(int)(WspolczynnikiGlowne[1]+WspolczynnikiGlowne[3]+WspolczynnikiGlowne[5])/3;
            WspolczynnikiPomocnicze[1]=(int)(WspolczynnikiGlowne[0]+WspolczynnikiGlowne[4]+WspolczynnikiGlowne[7])/3;
            WspolczynnikiPomocnicze[2]=20;
            WspolczynnikiPomocnicze[3]=(int)(WspolczynnikiGlowne[0]+WspolczynnikiGlowne[1]+WspolczynnikiGlowne[2])/3;
            WspolczynnikiPomocnicze[4]=(int)(WspolczynnikiGlowne[0]+WspolczynnikiGlowne[3]+WspolczynnikiGlowne[5])/3;
            WspolczynnikiPomocnicze[5]=(int)WspolczynnikiGlowne[7]*5;
            //auxiliary coefficients
            int SumaUmiejetnosci=8;
            for(int i = 0;i<Umiejetnosci.length;i++) {
                if(i<4) Umiejetnosci[i]=2;
                else {
                    Umiejetnosci[i]=1;
                    SumaUmiejetnosci++;
                }
            }

            while(SumaUmiejetnosci<=(int)(SumaWspolczynnikowGlownych/2)) {
                int los = generator.nextInt(22);
                Umiejetnosci[los]++;
                SumaUmiejetnosci++;
            }

            //determination of combat actions
            AkcjeRuch[0] = WspolczynnikiPomocnicze[3] - 10;
            AkcjeRuch[1] = WspolczynnikiPomocnicze[3] - 9;
            AkcjeRuch[2] = WspolczynnikiPomocnicze[3] - 12;
            AkcjeRuch[3] = (int)(WspolczynnikiPomocnicze[3]/3)+4;

            for(int i=0;i<Umiejetnosci[1];i++) {
                int los = generator.nextInt(4);
                AkcjeRuch[los]++;
            }

            //movements
            AkcjeRapierAtak[0] = (int)(WspolczynnikiPomocnicze[3] / 2) - 5;
            AkcjeRapierAtak[1] = (int)(WspolczynnikiPomocnicze[3] / 2) - 5;
            AkcjeRapierAtak[2] = WspolczynnikiPomocnicze[3] -15;
            AkcjeRapierAtak[3] = (int)(WspolczynnikiPomocnicze[3] / 2) - 7;
            AkcjeRapierAtak[4] = WspolczynnikiPomocnicze[3] -12;
            AkcjeRapierAtak[2] = WspolczynnikiPomocnicze[3] -8;
            AkcjeRapierAtak[5] = (int)(WspolczynnikiPomocnicze[3] / 3) - 1;

            AkcjeRapierObrona[0] = (int)(WspolczynnikiPomocnicze[3]/2)-2;
            AkcjeRapierObrona[1] = (int)(WspolczynnikiPomocnicze[3]/2)-2;
            AkcjeRapierObrona[2] = (int)(WspolczynnikiPomocnicze[3]/2)-5;
            AkcjeRapierObrona[3] = WspolczynnikiPomocnicze[3]-18;

            for(int i=0;i<Umiejetnosci[2]*3;i++) {
        	 int los= generator.nextInt(11);
        	 	if(los<=6) AkcjeRapierAtak[los]++;
        	 	else {
        	 		los=los-7;
        	 		AkcjeRapierObrona[los]++;
                }
            }

            //rapier
            AkcjeMieczAtak[0] = (int)(WspolczynnikiPomocnicze[3] / 2) - 7;
            AkcjeMieczAtak[1] = WspolczynnikiPomocnicze[3] - 17;
            AkcjeMieczAtak[2] = (int)(WspolczynnikiPomocnicze[3] / 2) - 5;
            AkcjeMieczAtak[3] = (int)(WspolczynnikiPomocnicze[3] / 3) - 3;

            AkcjeMieczObrona[0] = (int)(WspolczynnikiPomocnicze[3] / 2) - 4;
            AkcjeMieczObrona[1] = (int)(WspolczynnikiPomocnicze[3] / 2) - 4;
            AkcjeMieczObrona[2] = WspolczynnikiPomocnicze[3] - 20;

            for(int i=0;i<Umiejetnosci[3]/2;i++) {
                int los = generator.nextInt(7);
                if(los<4) AkcjeMieczAtak[los]++;
                else {
                    los=los-4;
                    AkcjeMieczObrona[los]++;
                }
            }
            //sword
            AkcjeZwarcie[0] = (int)(WspolczynnikiPomocnicze[3] / 2) - 5;
            AkcjeZwarcie[1] = (int)(WspolczynnikiPomocnicze[3] / 2) - 3;

            for(int i=0;i<Umiejetnosci[0];i++) {
                int los = generator.nextInt(2);
                AkcjeZwarcie[los]++;
            }
        }
    public String Zapisz() {

        String record ="";
        record+=this.imie+",";
        record+=this.SumaWspolczynnikowGlownych+",";
        record+=this.Rany+",";
        for(int i=0;i< 8;i++) {
            record+=this.WspolczynnikiGlowne[i]+",";
        }
        for(int i=0;i<5 ;i++) {
            record+=this.WspolczynnikiPomocnicze[i]+",";
        }
        for(int i=0;i< 22;i++) {
            record+=this.Umiejetnosci[i]+",";
        }
        for(int i=0;i< 4;i++) {
            record+=this.AkcjeRuch[i]+",";
        }
        for(int i=0;i<2 ;i++) {
            record+=this.AkcjeZwarcie[i]+",";
        }
        for(int i=0;i< 7;i++) {
            record+=this.AkcjeRapierAtak[i]+",";
        }
        for(int i=0;i< 4;i++) {
            record+=this.AkcjeRapierObrona[i]+",";
        }
        for(int i=0;i<4 ;i++) {
            record+=this.AkcjeMieczAtak[i]+",";
        }
        for(int i=0;i<3 ;i++) {
            record+=this.AkcjeMieczObrona[i]+",";
        }
        record+=";";
        return record;
    }
    public void Wczytaj(String klucz){
            String[] dane = klucz.split(",");

        this.imie=dane[0];
        this.SumaWspolczynnikowGlownych=Integer.parseInt(dane[1]);
        this.Rany=Integer.parseInt(dane[2]);
        for(int i=0;i< 8;i++) {
            this.WspolczynnikiGlowne[i]=Integer.parseInt(dane[3+i]);
        }
        for(int i=0;i<5 ;i++) {
            this.WspolczynnikiPomocnicze[i]=Integer.parseInt(dane[11+i]);
        }
        for(int i=0;i< 22;i++) {
            this.Umiejetnosci[i]=Integer.parseInt(dane[16+i]);
        }
        for(int i=0;i< 4;i++) {
            this.AkcjeRuch[i]=Integer.parseInt(dane[38+i]);
        }
        for(int i=0;i<2 ;i++) {
            this.AkcjeZwarcie[i]=Integer.parseInt(dane[42+i]);
        }
        for(int i=0;i< 7;i++) {
            this.AkcjeRapierAtak[i]=Integer.parseInt(dane[44+i]);
        }
        for(int i=0;i< 4;i++) {
            this.AkcjeRapierObrona[i]=Integer.parseInt(dane[51+i]);
        }
        for(int i=0;i<4 ;i++) {
            this.AkcjeMieczAtak[i]=Integer.parseInt(dane[55+i]);
        }
        for(int i=0;i<3 ;i++) {
            this.AkcjeMieczObrona[i]=Integer.parseInt(dane[59+i]);
        }
    }

    }
