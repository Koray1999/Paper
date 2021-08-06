import java.util.ArrayList;

public class SmartHome extends GeneratorFinal{
    static ArrayList<Device> SmartHomeDevices = new ArrayList<Device>();
    static ArrayList<ArrayList<Integer>> dependencies = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<ArrayList<Integer>>();
    static int nrOfDevices = 2;
    static int nrOfUpdatesPerDevice = 2;
    static int nrOfServicesPerDevice = 2;

    public static void main (String[] args){
        for(int i=0; i<nrOfDevices; i++){
            SmartHomeDevices.add(createDevice(nrOfUpdatesPerDevice, nrOfServicesPerDevice));
        }

        System.out.println("Die Geräte:");
        System.out.println(SmartHomeDevices);
        System.out.println("--------");

        System.out.println("Die Abhängigkeiten");
        dependencies = createDependency(SmartHomeDevices, nrOfServicesPerDevice);
        System.out.println(dependencies);
        System.out.println("--------");

        System.out.println("Die Updates jedes einezlnen Geräts");
        System.out.println(SmartHomeDevices.get(0).getUpdates());
        System.out.println(SmartHomeDevices.get(1).getUpdates());
        System.out.println("--------");



    }

}
















