package Com.java.Testing;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {

    public static Map<String, Integer> sortByValueAsc(Map<String, Integer> map) {
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        map.entrySet()
           .stream()
           .sorted((e1, e2) -> e1.getValue() - e2.getValue())
           .forEach(e -> sortedMap.put(e.getKey(), e.getValue()));

        return sortedMap;
    }
}
