import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Algorithm extends SmartHome {
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<>();

    public static void main(String[] args) {
        SmartHome.main(null);



        System.out.println("Die Updates der Geräte nachdem dominierte Updates entfernt wurden");
        for (Device a : SmartHomeDevices) {
            isDominated(a);
        }

        for (Device device : SmartHomeDevices){
            System.out.println(device.getUpdates());
        }


        List<List<ArrayList<LocalDate>>> allDeviceUpdateDates = new ArrayList<>();
        for (Device c : SmartHomeDevices){
            allDeviceUpdateDates.add(c.updateAge);
        }

        List<List<ArrayList<Integer>>> allDeviceUpdates = new ArrayList<>();
        for (Device b : SmartHomeDevices){
            allDeviceUpdates.add(b.updates);
        }
        System.out.println("Erstellen des UpdateConfigurationGraphs");
        SmartHome.updateConfigurationGraph = cartesianProduct(allDeviceUpdates);
        SmartHome.updateConfigurationGraphDates = cartesianProduct(allDeviceUpdateDates);
        System.out.println(updateConfigurationGraph);

        System.out.println("Entferne ungültige Updatekonfigurationen");
        SmartHome.updateConfigurationGraph = deleteBreakingConfigurations(updateConfigurationGraph);
        System.out.println(updateConfigurationGraph);


        System.out.println("UpdateKonfigurationsgraph mit Updatealter");
        System.out.println(updateConfigurationGraphDates);


        paretoOptimal(updateConfigurationGraphDates);
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

        ArrayList<Integer> toRemoveConfigs = new ArrayList<>();

        for (ArrayList<Integer> dependence : dependencies){
            importantService = dependence.get(1);
            for (List<List<Integer>> configuration : ucg){
                for (List<Integer> up : configuration){
                    allServicesOfConfiguration.addAll(up);
                }
                if (!allServicesOfConfiguration.contains(importantService) && !toRemoveConfigs.contains(ucg.indexOf(configuration))){
                    toRemoveConfigs.add(ucg.indexOf(configuration));
                    //ucg2.remove(configuration);
                    //SmartHome.updateConfigurationGraphDates.remove(ucg.indexOf(configuration));
                }
                allServicesOfConfiguration.clear();
            }

        }
        Collections.sort(toRemoveConfigs, Collections.reverseOrder());


        for (int test : toRemoveConfigs){
            ucg2.remove(test);
            SmartHome.updateConfigurationGraphDates.remove(test);
        }

        return ucg2;
    }


    public static void paretoOptimal(List<List<List<LocalDate>>> updateConfigurationGraphDates){
        boolean dominiert = true;
        List<List<List<LocalDate>>> copy = new ArrayList<>(updateConfigurationGraphDates);

        List<Integer> dominatedConfigurations = new ArrayList<>();

        for (int i=0; i<copy.size(); i++){

            for (int j=0; j<copy.size(); j++){
                if (i != j){
                    for (int k=0; k<copy.get(i).size(); k++){
                        if (copy.get(i).get(k).get(0).isAfter(copy.get(j).get(k).get(0))){
                            dominiert = false;
                        }
                    }
                }else {
                    dominiert = false;
                }
                if (dominiert && !dominatedConfigurations.contains(i)){
                    dominatedConfigurations.add(i);
                }
                dominiert = true;
            }
            dominiert = true;
        }

        Collections.sort(dominatedConfigurations, Collections.reverseOrder());
        for (int dominatedConfiguration : dominatedConfigurations){
            SmartHome.updateConfigurationGraph.remove(dominatedConfiguration);
            SmartHome.updateConfigurationGraphDates.remove(dominatedConfiguration);
        }

        System.out.println("Pareto Optimal:");
        System.out.println(SmartHome.updateConfigurationGraph);


    }



}