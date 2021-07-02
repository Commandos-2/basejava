package com.resume.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MainStream {
    public static void main(String[] args) {
        int[] array = {1, 5, 4, 3, 7, 7, 3, 9};
        int[] array2 = {2, 8, 1, 8, 2, 9, 4};
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(array2).boxed().collect(Collectors.toList());
        System.out.println(minValue(array));
        System.out.println(minValue(array2));
        System.out.println(oddOrEven(list));
        System.out.println(oddOrEven(list2));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> result = integers.stream().collect(Collectors.groupingBy(x -> x % 2 == 0));
        return result.get(result.get(false).size() % 2 != 0);
    }
}
