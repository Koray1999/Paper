import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    public static void main (String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        ArrayList<Integer> list5 = new ArrayList<Integer>();
        ArrayList<Integer> list6 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> hilfsArray = new ArrayList<ArrayList<Integer>>();

        list.add(1);
        list.add(2);
        list.add(3);

        list5.add(1);
        list5.add(2);
        list5.add(3);

        list2.add(7);
        list2.add(8);
        list2.add(9);

        list3.add(7);
        list3.add(8);
        list3.add(9);

        list6.add(2);
        list6.add(5);
        list6.add(7);

        ArrayList<ArrayList<Integer>> list4 = new ArrayList<ArrayList<Integer>>();
        list4.add(list);
        list4.add(list5);
        list4.add(list2);
        list4.add(list3);
        list4.add(list6);
        list4.add(list6);



        ArrayList<ArrayList<Integer>> hilfsArray2 = new ArrayList<ArrayList<Integer>>();

        System.out.println(list4);

        hilfsArray2.add(list);
        hilfsArray2.add(list5);
        hilfsArray2.add(list2);
        hilfsArray2.add(list3);
        hilfsArray2.add(list6);
        hilfsArray2.add(list6);

        int h = 0;
        for (ArrayList<Integer> a : hilfsArray2){
            Boolean isDominated = false;
            for (int k=0; k < 1; k++){
            if (hilfsArray2.get(k).containsAll(a)){
                isDominated = true;
            }
            }
            if (isDominated){
                list4.remove(h);
                isDominated = false;
            }
            h++;
        }
        System.out.println(list4);

/*
        for (int i=0; i<3; i++){
            int j = i+1;
            while (j<3){
                if (hilfsArray2.get(j).containsAll(hilfsArray2.get(i))){
                    list4.remove(i);
                    break;
                }else {
                    j++;
                }
            }

            for (int j=i+1; j<3; j++){
                if (hilfsArray2.get(j).containsAll(hilfsArray2.get(i))){
                    list4.remove(i);
                    j = 3;
                }
            }

        }

 */
    }
}
