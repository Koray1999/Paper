import java.time.LocalDate;
import java.util.*;


public class Algorithm extends SmartHome {
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> subnetworks = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
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
        //System.out.println(SmartHome.updateConfigurationGraph.size());
        //System.out.println(SmartHome.updateConfigurationGraphDates.size());
        System.out.println(updateConfigurationGraph);

        System.out.println("Entferne ungültige Updatekonfigurationen");
        SmartHome.updateConfigurationGraph = deleteBreakingConfigurations(updateConfigurationGraph);
        System.out.println(updateConfigurationGraph);


        System.out.println("UpdateKonfigurationsgraph mit Updatealter");
        System.out.println(updateConfigurationGraphDates);


        paretoOptimal(updateConfigurationGraphDates);

        System.out.println("Subnetzwerke");
        createSubnetworks();


        floodFill(subnetworks);

        rating();


        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
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
        toRemoveConfigs.sort(Collections.reverseOrder());
        System.out.println(toRemoveConfigs);

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

        dominatedConfigurations.sort(Collections.reverseOrder());
        for (int dominatedConfiguration : dominatedConfigurations){
            SmartHome.updateConfigurationGraph.remove(dominatedConfiguration);
            SmartHome.updateConfigurationGraphDates.remove(dominatedConfiguration);
        }

        System.out.println("Pareto Optimal:");
        System.out.println(SmartHome.updateConfigurationGraph);


    }

    public static void createSubnetworks(){
        int dependency;
        ArrayList<Integer> help = new ArrayList<>();

        for (int i=0; i<SmartHome.dependencies.size(); i++){
            dependency = dependencies.get(i).get(1);

            for (ArrayList<Integer> integers : SmartHome.dependencies) {
                if (dependency == integers.get(1) && !help.contains(integers.get(0))) {
                    help.add(integers.get(0));

                    for (Device device : SmartHomeDevices) {
                        for (int k = 0; k < device.getUpdates().size(); k++) {
                            if (device.getUpdates().get(k).contains(dependency) && !help.contains(SmartHomeDevices.indexOf(device))) {
                                help.add(SmartHomeDevices.indexOf(device));
                            }

                        }
                    }

                }
            }

            subnetworks.add(new ArrayList<>(help));
            help.clear();
        }
        System.out.println(subnetworks);
    }

    public static void floodFill(ArrayList<ArrayList<Integer>> subnetworks){
        ArrayList<ArrayList<Integer>> flood = new ArrayList<>();
        ArrayList<Integer> help = new ArrayList<>();
        int device;

        for (int i=0; i<nrOfDevices; i++){
            help.add(i);
            flood.add(new ArrayList<>(help));
            help.clear();
        }

        for (ArrayList<Integer> b : flood){
            device = b.get(0);
            for (ArrayList<Integer> c : subnetworks){
                if (c.contains(device) && Collections.min(c) < Collections.min(b)){
                    b.add(Collections.min(c));
                }
            }
        }

        for (ArrayList<Integer> integers : flood) {
            if (integers.size() == 1) {
                integers.add(integers.get(0));
            }
        }

        //geschummelt
        for (int m=0; m<10; m++){
            for (ArrayList<Integer> a : flood){
                for (int i=0; i<a.size(); i++){
                    int help1 = a.get(i);

                    for (ArrayList<Integer> b : flood){
                        if (b.contains(help1)){
                            if (help1 > Collections.min(b) && !a.contains(Collections.min(b))){
                                a.add(Collections.min(b));
                            }
                        }
                    }
                }
            }
        }

        ArrayList<ArrayList<Integer>> finalSubnetworks = new ArrayList<>();
        ArrayList<Integer> finalSubnetworksHelp = new ArrayList<>();
        int comparison;

        for (int i=0; i<flood.size(); i++){
            comparison = Collections.min(flood.get(i));

            for (ArrayList<Integer> integers : flood) {
                if (comparison == Collections.min(integers)) {
                    finalSubnetworksHelp.add(integers.get(0));
                }
            }
            finalSubnetworks.add(new ArrayList<>(finalSubnetworksHelp));
            finalSubnetworksHelp.clear();
        }


        ArrayList<ArrayList<Integer>> finalSubnetworksWithoutDuplicates = new ArrayList<>(
                new HashSet<>(finalSubnetworks));

        System.out.println("Flood Fill");
        System.out.println(flood);
        System.out.println("Finale Subnetzwerke");
        System.out.println(finalSubnetworksWithoutDuplicates);

    }

    public static void rating(){
        ArrayList<ArrayList<Integer>> ratings = new ArrayList<>();
        ArrayList<Integer> help = new ArrayList<>();
        Random rand = new Random();

        ArrayList<Integer> allRatings = new ArrayList<>();
        int rating = 0;

        for (int i=0; i<services; i++){
            help.add(i);
            help.add(rand.nextInt(10));
            ratings.add(new ArrayList<>(help));
            help.clear();
        }

        for (int i=0; i<updateConfigurationGraph.size(); i++){
            for (int j=0; j<updateConfigurationGraph.get(i).size(); j++){
                for (int k=0; k<updateConfigurationGraph.get(i).get(j).size(); k++){
                    //ratings angucken
                    for (int n=0; n<services; n++){
                        if (ratings.get(n).get(0) == updateConfigurationGraph.get(i).get(j).get(k)){
                            rating = rating + ratings.get(n).get(1);
                        }
                    }
                }
            }
            allRatings.add(rating);
            rating = 0;
        }

        System.out.println("Die bewerteten Konfigurationen");
        System.out.println(allRatings);
    }


}