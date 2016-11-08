package proyectobdii;

import java.util.ArrayList;

/**
 *
 * @author santiago
 */
public class Comparator {

    private static ArrayList<Diff> differences = new ArrayList<Diff>() ;


   
    public static void addDiff(Diff d){
        differences.add(d);
    
    }

    public static ArrayList<Diff> getDifferences() {
        return differences;
    }

    public static void setDifferences(ArrayList<Diff> differences) {
        Comparator.differences = differences;
    }
    
}
