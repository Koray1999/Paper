import java.util.ArrayList;

public class Algorithm extends SmartHome{
    static ArrayList<Device> SmartHomeGeräte = new ArrayList<Device>();
    static ArrayList<ArrayList<Integer>> abhängigkeiten = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args){

        SmartHomeGeräte = SmartHome.SmartHomeGeräte;
        abhängigkeiten = SmartHome.abhängigkeiten;
    }
}
