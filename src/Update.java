import java.util.ArrayList;

public class Update {

    int version;
    ArrayList<Integer> dienstleistungen;

    public Update(int version, ArrayList<Integer> dienstleistungen){
        this.version = version;
        this.dienstleistungen = dienstleistungen;
    }

    public ArrayList<Integer> getDienstleistungen(){
        return dienstleistungen;
    }


}
