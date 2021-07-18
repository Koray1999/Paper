import java.util.ArrayList;

public class SmartHome extends GeneratorFinal{
    public static void main (String[] args){
        ArrayList<Device> SmartHomeGeräte = new ArrayList<Device>();
        ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();

        int nrOfDevices = 10;
        int nrOfUpdatesPerDevice = 3;
        int nrOfServicesPerDevice = 10;

        for(int i=0; i<nrOfDevices; i++){
            SmartHomeGeräte.add(gerätErstellen(nrOfUpdatesPerDevice, nrOfServicesPerDevice));
        }
        System.out.println(SmartHomeGeräte);

        abhängigkeiten = abhängigkeitenErstellen(SmartHomeGeräte);
        System.out.println(abhängigkeiten);

        System.out.println(SmartHomeGeräte.get(0).getDienstleistungen());


    }
}
