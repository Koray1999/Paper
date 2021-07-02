import java.util.ArrayList;

public class Test {
    public static void main (String[] args) {

        int[] gerät1= {1, 2, 3};
        int[] gerät2= {3,4};
        MyPair a = new MyPair(1,3);
        MyPair b = new MyPair(2,4);
        ArrayList<MyPair> list1 = new ArrayList<MyPair>();
        list1.add(a);
        list1.add(b);
        System.out.println(list1);

    }
}
