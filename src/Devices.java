import java.util.*;

public class Devices {

    ArrayList<Integer> dienstleistungen = new ArrayList<Integer>();


    public Devices(ArrayList<Integer> dienstleistungen){
        this.dienstleistungen = dienstleistungen;
    }

    public ArrayList<Integer> getDienstleistungen(){
        return dienstleistungen;
    }
}
