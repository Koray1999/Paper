import java.util.ArrayList;
import java.util.Random;

public class GeneratorFinal {
    //Methode zum Geräte generieren
    public static Device createDevice(int nrOfUpdatesPerDevice, int nrOfServicesPerDevice){
        Random rand = new Random();
        int versionNr = rand.nextInt(nrOfUpdatesPerDevice);
        int services = 50;
        int hilfsVariable;
        ArrayList<ArrayList<Integer>> updates = new ArrayList<ArrayList<Integer>>();

        //Leere Updates in Liste eintragen
        for(int i = 0; i< nrOfUpdatesPerDevice; i++){
            updates.add(new ArrayList<Integer>());
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
        return new Device(versionNr, updates.get(0), updates);
    }


    //Methode zum Abhängigkeiten generieren
    public static ArrayList<ArrayList<Integer>> createDependency(ArrayList<Device> konfiguration, int nrOfServicesPerDevice){
        ArrayList<ArrayList<Integer>> dependencies = new ArrayList<ArrayList<Integer>>();
        int nrOfDevices = konfiguration.size();
        ArrayList<Integer> momentanteDienstleistungen = new ArrayList<Integer>();
        Random rand = new Random();

        for(int i=0; i<nrOfDevices; i++){
            dependencies.add(new ArrayList<Integer>());
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
