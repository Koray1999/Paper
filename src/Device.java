import java.util.*;

public class Device {
    int version;
    ArrayList<Integer> services;
    ArrayList<ArrayList<Integer>> updates;

    public Device(int version, ArrayList<Integer> dienstleistungen, ArrayList<ArrayList<Integer>> updates){
        this.version = version;
        this.services = dienstleistungen;
        this.updates = updates;

        if(version != 0){
            this.services = updates.get(version);
        }
    }

    public float getVersion() {
        return version;
    }

    public ArrayList<Integer> getServices(){
        return services;
    }

    public ArrayList<ArrayList<Integer>> getUpdates(){
        return updates;
    }

}
