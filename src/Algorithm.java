import java.util.ArrayList;
import java.util.List;


public class Algorithm extends SmartHome {
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<>();

    public static void main(String[] args) {
        SmartHome.main(null);

        for (Device a : SmartHomeDevices) {
            isDominated(a);
        }

        System.out.println("Die Geräte nachdem dominierte Updates entfernt wurden");
        System.out.println(SmartHomeDevices.get(0).getUpdates());
        System.out.println(SmartHomeDevices.get(1).getUpdates());

        List<List<ArrayList<Integer>>> allDeviceUpdates = new ArrayList<>();
        for (Device b : SmartHomeDevices){
            allDeviceUpdates.add(b.updates);
        }
        System.out.println("Erstellen des UpdateConfigurationGraphs");
        System.out.println(allDeviceUpdates);
        SmartHome.updateConfigurationGraph = cartesianProduct(allDeviceUpdates);
        System.out.println(updateConfigurationGraph);

        System.out.println("Entferne ungültige Updatekonfigurationen");
        SmartHome.updateConfigurationGraph = deleteBreakingConfigurations(updateConfigurationGraph);
        System.out.println(updateConfigurationGraph);

        System.out.println("Alter der Updates");
        System.out.println(SmartHomeDevices.get(0).getUpdatesAge());
        System.out.println(SmartHomeDevices.get(1).getUpdatesAge());
    }

    public static void isDominated(Device device) {
        int counter = 0;
        int index;

        updatesCopy.addAll(device.getUpdates());

        for (ArrayList<Integer> b : updatesCopy) {
            counter++;
            for (int i = counter; i < updatesCopy.size(); i++) {
                if (updatesCopy.get(i).containsAll(b)) {
                    index = device.getUpdates().indexOf(b);
                    device.getUpdates().remove(b);
                    device.getUpdatesAge().remove(index);
                    break;
                }
            }
        }
        updatesCopy.clear();
    }

    public static <T> List<List<List<T>>> cartesianProduct(List<List<ArrayList<T>>> lists) {
        List<List<List<T>>> resultLists = new ArrayList<>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<>());
            return resultLists;
        } else {
            List<ArrayList<T>> firstList = lists.get(0);
            List<List<List<T>>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (List<T> condition : firstList) {
                for (List<List<T>> remainingList : remainingLists) {
                    ArrayList<List<T>> resultList = new ArrayList<>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }



    public static List<List<List<Integer>>> deleteBreakingConfigurations(List<List<List<Integer>>> ucg){
        int importantService;
        List<List<List<Integer>>> ucg2 = new ArrayList<>(ucg);

        ArrayList<Integer> allServicesOfConfiguration = new ArrayList<>();

        for (ArrayList<Integer> dependence : dependencies){
            importantService = dependence.get(1);
            for (List<List<Integer>> configuration : ucg){
                for (List<Integer> up : configuration){
                    allServicesOfConfiguration.addAll(up);
                }
                if (!allServicesOfConfiguration.contains(importantService)){
                    ucg2.remove(configuration);
                }
                allServicesOfConfiguration.clear();
            }

        }
        return ucg2;
    }


    public static void paretoOptimal(){
        List<Integer> test = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();

        for (int i=0; i<updateConfigurationGraph.size()-1; i++){
            for (int j=0; j<updateConfigurationGraph.size()-1; j++){
                for (int k=0; k<10; k++){
                    test = updateConfigurationGraph.get(i).get(j);
                    test2 = updateConfigurationGraph.get(k+1).get(j);
                }
            }
        }
    }

}