import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeDevices = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
    static List<List<List<Integer>>> updateConfigurationGraph = new ArrayList<>();
    static List<List<List<LocalDate>>> updateConfigurationGraphDates = new ArrayList<>();
    static int nrOfDevices = 5;
    static int nrOfUpdatesPerDevice = 2;
    static int nrOfServicesPerDevice = 5;
    static int nrOfDependencies = 7;
    static int services = 1000;

    public static void main (String[] args){

        //Geräte erstellen und in Liste packen
        for(int i=0; i<nrOfDevices; i++){
            SmartHomeDevices.add(createDevice(nrOfUpdatesPerDevice, nrOfServicesPerDevice, services));
        }

        //Abhängigkeiten erstellen
        dependencies = createDependencies(nrOfDependencies);

        System.out.println("Die Geräte:");
        System.out.println(SmartHomeDevices);
        System.out.println("--------");

        System.out.println("Die Abhängigkeiten:");
        System.out.println(dependencies);
        System.out.println("--------");

        System.out.println("Die Updates jedes einzelnen Geräts");
        for (Device device : SmartHomeDevices){
            System.out.println(device.getUpdates());
        }
        System.out.println("--------");

    }
}
















