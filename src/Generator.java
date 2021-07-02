import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public static void main(String[] args){
        int anzahlDienstleistungenProGerät = 10;
        int dienstleistungsZahlenBereich = 10;

        ArrayList<Devices> konfiguration = new ArrayList<Devices>();
        ArrayList<Integer> arrayRandom = new ArrayList<Integer>();

        for(int j=0; j<10; j++){
            for (int i=0; i < anzahlDienstleistungenProGerät; i++)
            {
                int randomNum = ThreadLocalRandom.current().nextInt(0, dienstleistungsZahlenBereich);
                arrayRandom.add(randomNum);
            }
            konfiguration.add(new Devices(arrayRandom));
            System.out.println(konfiguration.get(j).getDienstleistungen().toString());
        }

    }
}
