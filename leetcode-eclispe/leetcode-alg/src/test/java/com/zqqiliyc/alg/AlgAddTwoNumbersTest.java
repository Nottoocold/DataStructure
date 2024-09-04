package com.zqqiliyc.alg;

import com.zqqiliyc.datastruct.node.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author zqqiliyc
 * @since 2024-09-04
 */
class AlgAddTwoNumbersTest {

    private final AlgAddTwoNumbers alg = new AlgAddTwoNumbers();

    @Test
    void addTwoNumbers() {
        ListNode<Integer> l1 = ListNode.fromArray(new Integer[]{9, 9, 9, 9, 9, 9, 9});
        ListNode<Integer> l2 = ListNode.fromArray(new Integer[]{9, 9, 9, 9});
        ListNode<Integer> result = alg.addTwoNumbers(l1, l2);
        assertEquals(Arrays.toString(new int[]{8, 9, 9, 9, 0, 0, 0, 1}), result.toString());
    }
}