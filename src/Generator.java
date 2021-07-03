import java.util.*;

public class Generator {
    public static void main(String[] args){
        Random rand = new Random();
        int anzahlGeräte = 10;

        ArrayList<Devices> konfiguration = new ArrayList<Devices>();
        ArrayList<Integer> dienstleistungen = new ArrayList<Integer>();

        for(int j=0; j<anzahlGeräte; j++){
            int anzahlDienstleistungenProGerät = rand.nextInt(10)+1;
            dienstleistungen.clear();
            for (int i=0; i < anzahlDienstleistungenProGerät; i++)
            {
                dienstleistungen.add(i);
            }
            float version = rand.nextFloat();
            konfiguration.add(new Devices(dienstleistungen,version));
            System.out.println(konfiguration.get(j).getDienstleistungen().toString());
            System.out.println(konfiguration.get(j).getVersion());
        }
    }
}
