package Com.java.Testing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    @Test
    void testGetPairWithSum10_Found() {
        int[] input = {1, 2, 3, 7, 5};
        List<Integer> expected = Arrays.asList(3, 7);
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertEquals(10, result.get(0) + result.get(1));
    }

    @Test
    void testGetPairWithSum10_NotFound() {
        int[] input = {1, 2, 3};
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertTrue(result.isEmpty(), "Expected no pair to be found");
    }

    @Test
    void testGetPairWithSum10_MultiplePairs() {
        int[] input = {1, 9, 2, 8, 3, 7};
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertEquals(10, result.get(0) + result.get(1)); // only checks one valid pair
    }

    @Test
    void testGetPairWithSum10_EmptyArray() {
        int[] input = {};
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPairWithSum10_SingleElement() {
        int[] input = {10};
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPairWithSum10_dounle() {
        int[] input = {10,0};
        List<Integer> result = ArrayUtils.getPairWithSum10(input);
        assertEquals(10,result.get(0) + result.get(1));
    }
}
