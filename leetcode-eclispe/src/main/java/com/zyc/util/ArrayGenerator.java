package com.zyc.util;

import java.util.Map;
import java.util.random.RandomGenerator;

/**
 * @author zyc
 * @date 2022/10/20
 */
public class ArrayGenerator {
    private ArrayGenerator() {
    }

    private final static RandomGenerator generator = RandomGenerator.getDefault();
    private static final Map<Integer, Integer> map;

    public static int[] create(int length) {
        int[] arr = new int[length];
        int size = num(length);
        int bound = map.get(size) + 1;
        for (int i = 0; i < length; i++) {
            arr[i] = generator.nextInt(bound);
        }
        return arr;
    }

    private static int num(int num) {
        return String.valueOf(num).length();
    }

    static {
        map = Map.ofEntries(
                Map.entry(1, 9),
                Map.entry(2, 99),
                Map.entry(3, 999),
                Map.entry(4, 9999),
                Map.entry(5, 99999),
                Map.entry(6, 999999),
                Map.entry(7, 9999999),
                Map.entry(8, 99999999),
                Map.entry(9, 999999999)
        );
    }

}

