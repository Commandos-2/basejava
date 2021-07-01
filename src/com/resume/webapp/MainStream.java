package com.resume.webapp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        AtomicInteger multiplier = new AtomicInteger(1);

        return Arrays.stream(values).distinct().boxed().sorted(Comparator.reverseOrder()).map((x) -> {
            int result = x * multiplier.get();
            multiplier.updateAndGet(v -> v * 10);
            return result;
        }).reduce(0, Integer::sum);
    }
    private static List<Integer> oddOrEven(List<Integer> integers){
        int sum=0;
        boolean inc=false;
        for (Integer var :integers){
            sum+=var;
        }
        if(sum%2==0){
            inc=true;
        }
        boolean finalInc = inc;
        return integers.stream().filter(x->(((x%2)==0)==finalInc)).collect(Collectors.toList());
    }
}
