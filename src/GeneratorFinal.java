import java.time.LocalDate;

import java.time.Month;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GeneratorFinal {

    //Kreiert ein Datum zwischen zwei Daten
    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    //Methode zum Geräte generieren
    public static Device createDevice(int nrOfUpdatesPerDevice, int nrOfServicesPerDevice, int services){
        Random rand = new Random();
        //Wählt für ein Gerät random die Versionsnummer aus
        int versionNr = rand.nextInt(nrOfUpdatesPerDevice);
        int service;
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        ArrayList<ArrayList<LocalDate>> updatesAge = new ArrayList<>();
        LocalDate start = LocalDate.of(2005, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();


        //Kreiert leere Listen in die das Alter (ein Datum) der Updates eingefügt werden
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updatesAge.add(new ArrayList<>());
        }
        //Füllt die leeren Listen mit einem Datum
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updatesAge.get(i).add(between(start, end));
        }


        //Kreiert leere Listen in die Diensleistungen der Updates eingefügt werden
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updates.add(new ArrayList<>());
        }
        //Füllt die leeren Listen mit random Diensleistungen aus dem Zahlenbereich 0-"Services"
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            int randomNumber= rand.nextInt(10);
            // i=0 bedeutet, dass zunächst random Diensleistungen in das Update an Stelle 0 eingefügt werden.
            if(i == 0){
                while(updates.get(i).size()< nrOfServicesPerDevice){
                    service = rand.nextInt(services);
                    if(!updates.get(i).contains(service)){
                        updates.get(i).add(service);
                    }
                }
            }
            //Hier werden alle Updates mit Dienstleistungen gefüllt die nicht an Stelle 1 sind
            //Entweder wird eine zufällige Dienstlesitung um 1 erhöht, eine neue Dienstleistung hinzugefügt oder einfach nichts wird geändert
            else if(randomNumber < 4){
                service = rand.nextInt(nrOfServicesPerDevice);
                updates.get(i).addAll(updates.get(i-1));
                updates.get(i).set(service, updates.get(i).get(service)+1);
            }else if(randomNumber < 7){
                service = rand.nextInt(nrOfServicesPerDevice);
                updates.get(i).addAll(updates.get(i-1));
                updates.get(i).add(rand.nextInt(50));
            }else {
                updates.get(i).addAll(updates.get(i-1));
            }
        }
        return new Device(versionNr, updates.get(0), updates, updatesAge);
    }


    //Methode zum Abhängigkeiten generieren Version1 (wird nicht benutzt)
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
            int test = rand.nextInt(nrOfDevices);
            if(test != i){
                dependencies.get(i).add(test);
            }else if(test == 0){
                dependencies.get(i).add(test+1);
            }else {
                dependencies.get(i).add(test-1);
            }

        }
        return dependencies;
    }

    //Methode zum Abhängigkeiten generieren Version2
    public static ArrayList<ArrayList<Integer>> createDependencies2(int nrOfDependencies){
        ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
        ArrayList<Integer> allServices = new ArrayList<>();
        Random rand = new Random();


        //Erstellt eine List mit allen Dienstleistungen von allen Geräten
        for (Device device : SmartHome.SmartHomeDevices){
            for (ArrayList<Integer> updates : device.getUpdates()){
                for (int service : updates){
                    allServices.add(service);
                }
            }
        }

        //Erstellt leere Abhängigkeiten
        for(int i=0; i<nrOfDependencies; i++){
            dependencies.add(new ArrayList<>());
        }

        //Füllt leere Abhängigkeiten mit einer Gerätenummer und einer Dienstleistung aus der List aller Dienstleistungen
        for (int i=0; i<nrOfDependencies; i++){
            dependencies.get(i).add(rand.nextInt(SmartHome.nrOfDevices));
            dependencies.get(i).add(allServices.get(rand.nextInt(allServices.size())));
        }
        return dependencies;
    }
}
