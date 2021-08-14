import java.util.ArrayList;
import java.util.List;


public class Algorithm extends SmartHome {
    static ArrayList<ArrayList<Integer>> updatesCopy = new ArrayList<ArrayList<Integer>>();

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
        System.out.println(cartesianProduct(allDeviceUpdates));
    }

    public static void isDominated(Device device) {
        int counter = 0;

        for (ArrayList<Integer> a : device.getUpdates()) {
            updatesCopy.add(a);
        }

        for (ArrayList<Integer> b : updatesCopy) {
            counter++;
            for (int i = counter; i < updatesCopy.size(); i++) {
                if (updatesCopy.get(i).containsAll(b)) {
                    device.getUpdates().remove(b);
                    break;
                }
            }
        }
        updatesCopy.clear();
    }

    public static <T> List<List<List<T>>> cartesianProduct(List<List<ArrayList<T>>> lists) {
        List<List<List<T>>> resultLists = new ArrayList<List<List<T>>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<List<T>>());
            return resultLists;
        } else {
            List<ArrayList<T>> firstList = lists.get(0);
            List<List<List<T>>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (List<T> condition : firstList) {
                for (List<List<T>> remainingList : remainingLists) {
                    ArrayList<List<T>> resultList = new ArrayList<List<T>>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

}