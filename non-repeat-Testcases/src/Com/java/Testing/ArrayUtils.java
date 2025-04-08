package Com.java.Testing;

import java.util.*;

public class ArrayUtils {

    public static List<Integer> getPairWithSum10(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int complement = 10 - num;
            if (seen.contains(complement)) {
                return Arrays.asList(complement, num);
            }
            seen.add(num);
        }

        return Collections.emptyList();
    }
}

