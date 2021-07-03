import java.util.*;

public class Device {
    int version;
    ArrayList<Integer> dienstleistungen;
    ArrayList<ArrayList<Integer>> updates;

    public Device(int version, ArrayList<Integer> dienstleistungen, ArrayList<ArrayList<Integer>> updates){
        this.version = version;
        this.dienstleistungen = dienstleistungen;
        this.updates = updates;

        if(version != 0){
            this.dienstleistungen = updates.get(version);
        }
    }

    public float getVersion() {
        return version;
    }

    public ArrayList<Integer> getDienstleistungen(){
        return dienstleistungen;
    }

    public ArrayList<ArrayList<Integer>> getUpdates(){
        return updates;
    }

}
