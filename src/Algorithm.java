import java.util.ArrayList;


public class Algorithm extends SmartHome {
    static ArrayList<ArrayList<Integer>> updateConfigurationGraph = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> updateConfiguration = new ArrayList<Integer>();

    public static void main(String[] args) {
        SmartHome.main(null);

        for (Device a : SmartHomeDevices) {
            isDominated(a);
        }

        System.out.println("Die Geräte nachdem dominierte Updates entfernt wurden");
        System.out.println(SmartHomeDevices.get(0).getUpdates());
        System.out.println(SmartHomeDevices.get(1).getUpdates());
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

    public static void createUCG() {
        ArrayList<Integer> update = new ArrayList<>();

        for (int i = 0; i < SmartHomeDevices.size(); i++) {
            for (int j = 0; j < SmartHomeDevices.get(i).getUpdates().size(); j++) {
                for (ArrayList<Integer> a : SmartHomeDevices.get(i).getUpdates()) {

                }
            }
        }
    }
}