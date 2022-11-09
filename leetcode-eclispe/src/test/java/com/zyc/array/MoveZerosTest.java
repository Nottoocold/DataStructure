package com.zyc.array;

import com.zyc.util.ArrayGenerator;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class MoveZerosTest {

    @Test
    @Order(1)
    void moveZeroes() {
        int[] ints = ArrayGenerator.create(10);
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Order(2)
    @Test
    void moveZeroes_1() {
        int[] ints = ArrayGenerator.create(10);
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeroes_1(ints);
        System.out.println(Arrays.toString(ints));
    }
}
