import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GeneratorFinal {


    //Methode zum Geräte generieren
    public static Device createDevice(int nrOfUpdatesPerDevice, int nrOfServicesPerDevice){
        Random rand = new Random();
        int versionNr = rand.nextInt(nrOfUpdatesPerDevice);
        int services = 50;

        int hilfsVariable;
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        ArrayList<ArrayList<LocalDate>> updatesAge = new ArrayList<>();

        //Leere UpdatesAge in Liste eintragen
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updatesAge.add(new ArrayList<>());
        }

        //Leere UpdatesAge mit Dienstleistungen füllen
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updatesAge.get(i).add(LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-100*365, 100*365)));
        }

        //Leere Updates in Liste eintragen
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updates.add(new ArrayList<>());
        }


        //Leere Updates mit Dienstleistungen füllen
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            int randomNumber= rand.nextInt(10);
            //"Originale" Dienstleistungen füllen
            if(i == 0){
                while(updates.get(i).size()< nrOfServicesPerDevice){
                    hilfsVariable = rand.nextInt(services);
                    if(!updates.get(i).contains(hilfsVariable)){
                        updates.get(i).add(hilfsVariable);
                    }
                }
            }//"Originale" Dienstleistungen füllen
            //Entweder zufälligen Dienstlesitung um 1 erhöhen, neue Dienstleistung hinzufügen oder einfach nichts ändern
            else if(randomNumber < 5){
                hilfsVariable = rand.nextInt(nrOfServicesPerDevice);
                updates.get(i).addAll(updates.get(i-1));
                updates.get(i).set(hilfsVariable, updates.get(i).get(hilfsVariable)+1);
            }else if(randomNumber<9){
                hilfsVariable = rand.nextInt(nrOfServicesPerDevice);
                updates.get(i).addAll(updates.get(i-1));
                updates.get(i).add(rand.nextInt(50));
            }else {
                updates.get(i).addAll(updates.get(i-1));
            }
        }
        return new Device(versionNr, updates.get(0), updates, updatesAge);
    }


    //Methode zum Abhängigkeiten generieren
    public static ArrayList<ArrayList<Integer>> createDependency(ArrayList<Device> konfiguration, int nrOfServicesPerDevice){
        ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
        int nrOfDevices = konfiguration.size();
        ArrayList<Integer> momentanteDienstleistungen = new ArrayList<>();
        Random rand = new Random();

        for(int i=0; i<nrOfDevices; i++){
            dependencies.add(new ArrayList<>());
        }

        for(int i=0; i<nrOfDevices; i++){
            Device momentantesGeraet = konfiguration.get(i);
            momentanteDienstleistungen = momentantesGeraet.getServices();
            dependencies.get(i).add(i);
            dependencies.get(i).add(momentanteDienstleistungen.get(rand.nextInt(nrOfServicesPerDevice)));
            //Problem
            int test = rand.nextInt(nrOfDevices);
            if(test != i){
                dependencies.get(i).add(test);
            }else if(test < nrOfDevices-2){
                dependencies.get(i).add(test+1);
            }else{
                dependencies.get(i).add(test-1);
            }

        }
        return dependencies;
    }
    //Methode zum Abhängigkeiten generieren
}
