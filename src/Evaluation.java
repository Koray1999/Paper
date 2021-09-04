public class Evaluation extends  Algorithm{

    public static void main (String[] args){
        for (int i=1; i<10; i++){
            nrOfDevices = i;
            Algorithm.main(null);

            SmartHomeDevices.clear();
            dependencies.clear();
            updateConfigurationGraph.clear();
            updateConfigurationGraphDates.clear();
        }
    }
}
