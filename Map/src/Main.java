import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Map<Integer, Integer> input = new HashMap();
        Map<String, Integer> bck = new HashMap();
        int ii=0;
        while ( ii <= 5)
        {
            input.put(ii, ii);
            int res = input.get(ii);
            System.out.println("\nOld value of map"+res);
            input.put(ii, ii);
            ii++;

        }
    }
}