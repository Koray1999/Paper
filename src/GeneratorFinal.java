import java.util.ArrayList;
import java.util.Random;

public class GeneratorFinal {

    //Methode zum Geräte generieren
    public static Device gerätErstellen(int nrOfUpdatesPerDevice, int nrOfServicesPerDevice){
        Random rand = new Random();
        int anzahlUpdatesProGerät = nrOfUpdatesPerDevice;
        int versionsNummer = rand.nextInt(nrOfUpdatesPerDevice);
        int anzahlDiensleistungen = nrOfServicesPerDevice;
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
                hilfsVariable = rand.nextInt(nrOfServicesPerDevice);
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
    public static ArrayList<ArrayList<Integer>> abhängigkeitenErstellen(ArrayList<Device> konfiguration){
        ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();
        int anzahlGeräte = konfiguration.size();
        ArrayList<Integer> momentanteDienstleistungen = new ArrayList<Integer>();
        Random rand = new Random();

        for(int i=0; i<anzahlGeräte; i++){
            abhängigkeiten.add(new ArrayList<Integer>());
        }

        for(int i=0; i<anzahlGeräte; i++){
            Device momentantesGerät = konfiguration.get(i);
            momentanteDienstleistungen = momentantesGerät.getDienstleistungen();
            abhängigkeiten.get(i).add(i);
            abhängigkeiten.get(i).add(momentanteDienstleistungen.get(rand.nextInt(10)));
            //Problem
            int test = rand.nextInt(10);
            if(test != i){
                abhängigkeiten.get(i).add(test);
            }else if(test < 9){
                abhängigkeiten.get(i).add(test+1);
            }else{
                abhängigkeiten.get(i).add(test-1);
            }

        }
        return abhängigkeiten;
    }
    //Methode zum Abhängigkeiten generieren
}
