import java.time.LocalDate;
import java.util.*;

public class Device {
    int version;
    ArrayList<Integer> services;
    ArrayList<ArrayList<Integer>> updates;
    ArrayList<ArrayList<LocalDate>> updateAge;

    public Device(int version, ArrayList<Integer> dienstleistungen, ArrayList<ArrayList<Integer>> updates, ArrayList<ArrayList<LocalDate>> updatesAge){
        this.version = version;
        this.services = dienstleistungen;
        this.updates = updates;
        this.updateAge = updatesAge;

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

    public ArrayList<ArrayList<LocalDate>> getUpdatesAge(){
        return updateAge;
    }

}
