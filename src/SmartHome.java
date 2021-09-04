import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeDevices = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
    static List<List<List<Integer>>> updateConfigurationGraph = new ArrayList<>();
    static List<List<List<LocalDate>>> updateConfigurationGraphDates = new ArrayList<>();
    static int nrOfDevices = 3;
    static int nrOfUpdatesPerDevice = 5;
    static int nrOfServicesPerDevice = 2;
    static int nrOfDependencies = 2;
    static int services = 50;

    public static void main (String[] args){

        for(int i=0; i<nrOfDevices; i++){
            SmartHomeDevices.add(createDevice(nrOfUpdatesPerDevice, nrOfServicesPerDevice, services));
        }

        dependencies = createDependencies2(nrOfDependencies);

        System.out.println("Die Geräte:");
        System.out.println(SmartHomeDevices);
        System.out.println("--------");

        System.out.println("Die Abhängigkeiten");
        System.out.println(dependencies);
        System.out.println("--------");

        System.out.println("Die Updates jedes einezlnen Geräts");
        for (Device device : SmartHomeDevices){
            System.out.println(device.getUpdates());
        }
        System.out.println("--------");

    }

}
















