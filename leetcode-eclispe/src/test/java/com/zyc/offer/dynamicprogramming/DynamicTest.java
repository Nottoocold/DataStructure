package com.zyc.offer.dynamicprogramming;

import com.zyc.util.ArrayGenerator;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class DynamicTest {
    final Dynamic d = new Dynamic();
    final int[] num = ArrayGenerator.create(10);

    @Test
    @Order(1)
    void a() {
        System.out.println(Arrays.toString(num));
    }

    @Test
    @Order(2)
    void maxSubArr() {
        int maxSubArr = d.maxSubArr(num);
        System.out.println(maxSubArr);
    }

    @Test
    @Order(3)
    void maxProfit() {
        int maxProfit = d.maxProfit(num);
        System.out.println(maxProfit);
    }

    @Test
    @Order(4)
    void maxValue() {

    }

    @Test
    @Order(5)
    void translateNum() {
        int num = d.translateNum(12457);
        System.out.println(num);
    }
}