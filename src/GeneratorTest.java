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
            for(int j=0; updates.get(i).size()<anzahlDiensleistungen; j++){
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
}
