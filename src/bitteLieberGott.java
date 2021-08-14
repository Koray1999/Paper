import java.util.ArrayList;
import java.util.List;

public class bitteLieberGott extends SmartHome {

    public static void main(String[] args){
        SmartHome.main(null);

        List<List<ArrayList<Integer>>> help = new ArrayList<>();

        for (Device geraet : SmartHomeDevices){
           help.add(geraet.updates);
        }
        System.out.println("............................................");
        System.out.println(help);
        System.out.println(cartesianProduct(help));

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
