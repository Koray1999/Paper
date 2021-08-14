import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class kartesischesProdukt extends SmartHome{

    public static void main(String[] args){
        SmartHome.main(null);
        System.out.println("-----------------------------------------------------------------------");

        Set<Integer> device1 = new HashSet<>();
        Set<Integer> device3 = new HashSet<>();
        Set<Integer> device2 = new HashSet<>();
        Set<Integer> device4 = new HashSet<>();

        device1.add(SmartHomeDevices.get(0).getUpdates().get(0).get(0));
        device1.add(SmartHomeDevices.get(0).getUpdates().get(0).get(1));
        device3.add(SmartHomeDevices.get(0).getUpdates().get(1).get(0));
        device3.add(SmartHomeDevices.get(0).getUpdates().get(1).get(1));

        Set<Set<?>> test = new HashSet<>();
        test.add(device1);
        test.add(device3);

        device2.add(SmartHomeDevices.get(1).getUpdates().get(0).get(0));
        device2.add(SmartHomeDevices.get(1).getUpdates().get(0).get(1));
        device4.add(SmartHomeDevices.get(1).getUpdates().get(1).get(0));
        device4.add(SmartHomeDevices.get(1).getUpdates().get(1).get(1));

        Set<Set<?>> test2 = new HashSet<>();
        test2.add(device2);
        test2.add(device4);

        Set<?> targetSet = Set.copyOf(SmartHomeDevices.get(1).getUpdates());



        System.out.println(test);
        System.out.println(test2);



        System.out.println(cartesianProduct(test, test2));


        System.out.println("-----------------------------------------------------------------------");
/*
        Set<Integer> test = new HashSet();
        test.add(1);
        test.add(2);


        Set<Integer> test2 = new HashSet();
        test2.add(3);
        test2.add(4);


        Set<Set<?>> lel = new HashSet<>();
        lel.add(test);
        lel.add(test2);


        Set<Integer> test3 = new HashSet();
        test3.add(5);
        test3.add(6);


        Set<Integer> test4 = new HashSet();
        test4.add(7);
        test4.add(8);

        Set<Integer> test5 = new HashSet();
        test5.add(9);
        test5.add(10);


        Set<Set<?>> lol = new HashSet<>();
        lol.add(test3);
        lol.add(test4);

        Set<Set<?>> lul = new HashSet<>();
        lul.add(test5);
        lul.add(test5);

        System.out.println(lel);
        System.out.println(lol);
        System.out.println(lul);


        System.out.println(cartesianProduct(lel, lol));
 */

        Set<Set<?>> esWirdKlappen = new HashSet<>();

        Set<Integer> inshallah = new HashSet<>();

        for (Device device : SmartHomeDevices){
            for (ArrayList<Integer> update : device.getUpdates()){
                for (Integer service : update){
                    inshallah.add(service);
                }
                esWirdKlappen.add(inshallah);
            }
        }



        System.out.println(inshallah);
        System.out.println(esWirdKlappen);


    }



    public static Set<Set<Set<Object>>> cartesianProduct(Set<Set<?>>... sets) {
        if (sets.length < 2)
            throw new IllegalArgumentException(
                    "Can't have a product of fewer than two sets (got " +
                            sets.length + ")");

        return _cartesianProduct(0, sets);
    }

    private static Set<Set<Set<Object>>> _cartesianProduct(int index, Set<Set<?>>... sets) {
        Set<Set<Set<Object>>> ret = new HashSet<Set<Set<Object>>>();
        if (index == sets.length) {
            ret.add(new HashSet<Set<Object>>());
        } else {
            for (Set<?> obj : sets[index]) {
                for (Set<Set<Object>> set : _cartesianProduct(index+1, sets)) {
                    set.add((Set<Object>) obj);
                    ret.add(set);
                }
            }
        }
        return ret;
    }

}

