import java.util.ArrayList;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeGeräte = new ArrayList<Device>();
    static ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();
    static int nrOfDevices = 10;
    static int nrOfUpdatesPerDevice = 3;
    static int nrOfServicesPerDevice = 10;

    public static void main (String[] args){


        for(int i=0; i<nrOfDevices; i++){
            SmartHomeGeräte.add(gerätErstellen(nrOfUpdatesPerDevice, nrOfServicesPerDevice));
        }

        System.out.println(SmartHomeGeräte);
        abhängigkeiten = abhängigkeitenErstellen(SmartHomeGeräte);
        System.out.println(abhängigkeiten);
        System.out.println(SmartHomeGeräte.get(0).getUpdates());
    }
    //----------------------------------------------------------------------------------------------------//

    public static void isDominated(){
        // i ist das Gerät
        ArrayList<ArrayList<Integer>> hilfsArray = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < nrOfUpdatesPerDevice; i++){
            for (int j = i +1; j< nrOfUpdatesPerDevice; j++){
                if(SmartHomeGeräte.get(i).getUpdates().get(j).containsAll(SmartHomeGeräte.get(i).getUpdates().get(i))){
                    SmartHomeGeräte.get(i).getUpdates().remove(i);
                }
            }

        }
    }

    public static void isDominated2(){
        ArrayList<ArrayList<Integer>> hilfsArray = new ArrayList<ArrayList<Integer>>();

        for (int i=0; i<nrOfUpdatesPerDevice; i++){
            hilfsArray = SmartHomeGeräte.get(i).getUpdates();

            for (int j=i+1; j<nrOfUpdatesPerDevice; j++){
                if (hilfsArray.get(j).containsAll(hilfsArray.get(i))){
                    SmartHomeGeräte.get(i).getUpdates().remove(i);
                    j = nrOfUpdatesPerDevice;
                }
            }
        }
    }
    //----------------------------------------------------------------------------------------------------//
}
















