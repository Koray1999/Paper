import java.util.ArrayList;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeGeräte = new ArrayList<Device>();
    static ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<ArrayList<Integer>>();
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

        System.out.println("--------");

        for (Device a : SmartHomeGeräte){
            isDominated(a);
        }
        System.out.println(SmartHomeGeräte.get(0).getUpdates());

    }
    //----------------------------------------------------------------------------------------------------//

    public static void isDominated(Device device){
        int counter=0;

        for (ArrayList<Integer> a : device.getUpdates()){
            updatesCopy.add(a);
        }

        for (ArrayList<Integer> b : updatesCopy){
            counter++;
            for (int i=counter; i<updatesCopy.size(); i++){
                if (updatesCopy.get(i).containsAll(b)){
                    device.getUpdates().remove(b);
                    break;
                }
            }
        }
        updatesCopy.clear();
    }

    //----------------------------------------------------------------------------------------------------//
}
















