import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    public static void main (String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        ArrayList<Integer> list5 = new ArrayList<Integer>();
        ArrayList<Integer> list6 = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        list3.add(1);
        list3.add(2);
        list3.add(3);

        list2.add(1);
        list2.add(2);
        list2.add(4);

        list5.add(7);
        list5.add(8);
        list5.add(5);

        list6.add(2);
        list6.add(5);
        list6.add(7);

        ArrayList<ArrayList<Integer>> list4 = new ArrayList<ArrayList<Integer>>();
        list4.add(list);
        list4.add(list3);
        list4.add(list2);
        list4.add(list5);
        list4.add(list6);
        list4.add(list6);
        list4.add(list6);

        ArrayList<ArrayList<Integer>> hilfsArray2 = new ArrayList<ArrayList<Integer>>();


        hilfsArray2.add(list);
        hilfsArray2.add(list3);
        hilfsArray2.add(list2);
        hilfsArray2.add(list5);
        hilfsArray2.add(list6);
        hilfsArray2.add(list6);
        hilfsArray2.add(list6);

        System.out.println(list4);
        System.out.println(hilfsArray2);

        int zaehler = 0;
        for (ArrayList<Integer> a : hilfsArray2){
            zaehler++;
            for (int i = zaehler; i<hilfsArray2.size(); i++){
                if (hilfsArray2.get(i).containsAll(a)){
                    list4.remove(a);
                    break;
                }
            }
        }
        System.out.println(list4);

    }
}
