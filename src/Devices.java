import java.util.*;

public class Devices {

    ArrayList<Integer> dienstleistungen;
    float version;


    public Devices(ArrayList<Integer> dienstleistungen, float version){
        this.dienstleistungen = dienstleistungen;
        this.version = version;
    }

    public float getVersion() {
        return version;
    }

    public ArrayList<Integer> getDienstleistungen(){
        return dienstleistungen;
    }

}
