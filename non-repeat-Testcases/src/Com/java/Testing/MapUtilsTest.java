package Com.java.Testing;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapUtilsTest {

    @Test
    void testSortByValueAsc() {
        Map<String, Integer> input = new HashMap<>();
        input.put("banana", 2);
        input.put("apple", 4);
        input.put("cherry", 1);

        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("cherry", 1);
        expected.put("banana", 2);
        expected.put("apple", 4);

        Map<String, Integer> result = MapUtils.sortByValueAsc(input);

        assertEquals(expected, result);
    }
}
