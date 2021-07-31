
import java.util.*;

public class GeneratorTest {
    public static void main(String[] args){
        ArrayList<Device> konfiguration = new ArrayList<Device>();
        /*
        //Standard Dienstleistungen
        ArrayList<Integer> dienstleistungen = new ArrayList<Integer>();
        dienstleistungen.add(1);
        dienstleistungen.add(2);
        dienstleistungen.add(3);

        //Update 1 erstellen
        ArrayList<Integer> dienstleistungen1 = new ArrayList<Integer>();
        dienstleistungen1.add(4);
        dienstleistungen1.add(5);
        dienstleistungen1.add(6);

        //Update 2 erstellen
        ArrayList<Integer> dienstleistungen2 = new ArrayList<Integer>();
        dienstleistungen2.add(7);
        dienstleistungen2.add(8);
        dienstleistungen2.add(9);

        //Liste mit Updates füllen
        ArrayList<ArrayList<Integer>> updates = new ArrayList<ArrayList<Integer>>();
        updates.add(dienstleistungen);
        updates.add(dienstleistungen1);
        updates.add(dienstleistungen2);

        //Gerät erstellen
        Device gerät1 = new Device(0, updates.get(0) ,updates);

        //Geräte zu Array hinzufügen
        konfiguration.add(gerät1);
        */

        for(int i=0; i<10; i++){
            konfiguration.add(gerätErstellen());
        }
        System.out.println(konfiguration);

        abhängigkeitenErstellen2(konfiguration);
    }

    //Methode zum Geräte generieren
    public static Device gerätErstellen(){
        Random rand = new Random();
        int anzahlUpdatesProGerät = 3;
        int versionsNummer = rand.nextInt(3);
        int anzahlDiensleistungen = 10;
        int hilfsVariable;
        ArrayList<ArrayList<Integer>> updates = new ArrayList<ArrayList<Integer>>();

        //Leere Updates in Liste eintragen
        for(int i=0; i<anzahlUpdatesProGerät; i++){
            updates.add(new ArrayList<Integer>());
        }
        //Leere Updates in Liste eintragen

        //Leere Updates mit Dienstleistungen füllen
        for(int i=0; i<updates.size();i++){
            while(updates.get(i).size()<anzahlDiensleistungen){
                hilfsVariable = rand.nextInt(10);
                if(!updates.get(i).contains(hilfsVariable)){
                    updates.get(i).add(hilfsVariable);
                }
            }

        }
        //Leere Updates mit Dienstleistungen füllen

        Device device = new Device(versionsNummer, updates.get(0), updates);
        return device;
    }
    //Methode zum Geräte generieren

    //Methode zum Abhängigkeiten generieren
    public static void abhängigkeitenErstellen(ArrayList<Device> konfiguration){
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();
        int anzahlGeräte = konfiguration.size();

        for(int i=0; i<anzahlGeräte; i++){
            abhängigkeiten.add(new ArrayList<Integer>());
        }

        for(int i=0; i<anzahlGeräte; i++){
            ArrayList<Integer> hilfsArray = konfiguration.get(i).getServices();
            int hilfVariable = hilfsArray.get(rand.nextInt(10));

            ArrayList<Integer> test = new ArrayList<Integer>();
            test.add(i);
            test.add(hilfsArray.get(hilfVariable));
            test.add(rand.nextInt(10));
        }
    }
    //Methode zum Abhängigkeiten generieren

    //Methode zum Abhängigkeiten generieren
    public static void abhängigkeitenErstellen2(ArrayList<Device> konfiguration){
        ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();
        int anzahlGeräte = konfiguration.size();
        ArrayList<Integer> momentanteDienstleistungen = new ArrayList<Integer>();
        Random rand = new Random();

        for(int i=0; i<anzahlGeräte; i++){
            abhängigkeiten.add(new ArrayList<Integer>());
        }

        for(int i=0; i<anzahlGeräte; i++){
            Device momentantesGerät = konfiguration.get(i);
            momentanteDienstleistungen = momentantesGerät.getServices();
            abhängigkeiten.get(i).add(i);
            abhängigkeiten.get(i).add(momentanteDienstleistungen.get(rand.nextInt(10)));
            abhängigkeiten.get(i).add(rand.nextInt(9)+1);
        }
        System.out.println(abhängigkeiten);
    }
    //Methode zum Abhängigkeiten generieren
}
