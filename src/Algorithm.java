import java.util.ArrayList;

public class Algorithm extends SmartHome{
    static ArrayList<Device> SmartHomeGeräte = new ArrayList<Device>();
    static ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args){
        SmartHome.main(null);

        for (Device a : SmartHomeDevices){
            isDominated(a);
        }
        System.out.println(SmartHomeDevices.get(0).getUpdates());
    }


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

    public static void createUCG(){

    }
}
