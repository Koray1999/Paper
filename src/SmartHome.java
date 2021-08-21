import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeDevices = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
    static List<List<List<Integer>>> updateConfigurationGraph = new ArrayList<>();
    static List<List<List<LocalDate>>> updateConfigurationGraphDates = new ArrayList<>();
    static int nrOfDevices = 2;
    static int nrOfUpdatesPerDevice = 2;
    static int nrOfServicesPerDevice = 2;

    public static void main (String[] args){

        for(int i=0; i<nrOfDevices; i++){
            SmartHomeDevices.add(createDevice(nrOfUpdatesPerDevice, nrOfServicesPerDevice));
        }

        dependencies = createDependency(SmartHomeDevices, nrOfServicesPerDevice);

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
















