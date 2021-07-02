import java.util.*;

public class Device {
    String name;
    int[] dienstleistungen;
    int version;
    public int[][] versions = {{1,2,3}, {1,2,3}, {1,2,4}};
    //int[][] updates;

    public Device(String name, int version, int[] dienstleistungen, int[][] versions){
        this.name = name;
        this.dienstleistungen = versions[1];
        this.version = version;
        //this.versions = versions;

    }
    public static void main (String[] args){
        int[][] versions = {{1,2,3}, {1,2,3}, {1,2,4}};
        System.out.println(Arrays.toString(versions[1]));
    }
    /*public  int[][] getUpdates(){

    }*/
    //test
}
