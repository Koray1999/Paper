import java.util.*;

public class Test1 {
    public static void main (String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
        list2.add(list);



        System.out.println(list2);
        list.clear();
    }
}
