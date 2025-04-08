import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 2);
        map.put("apple", 4);
        map.put("cherry", 1);

        Map<String, Integer> sortedMap2 = new TreeMap<>(map);
        System.out.println(sortedMap2);


        // Step 1: Create a map
        Map<String, Integer> sortedMap = new HashMap<>();
        map.put("banana", 2);
        map.put("apple", 4);
        map.put("cherry", 1);



        map.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue() - e2.getValue())
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
        // Step 3: Print it
        //System.out.println(sortedMap);
    }






    }




