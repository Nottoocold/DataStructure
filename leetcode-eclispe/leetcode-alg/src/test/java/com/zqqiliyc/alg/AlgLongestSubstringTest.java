package com.zqqiliyc.alg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author zqqiliyc
 * @since 2024-09-05
 */
class AlgLongestSubstringTest {

    private final AlgLongestSubstring alg = new AlgLongestSubstring();

    @Test
    void longestSubstring() {
        String string = "abefdabfe";
        String longested = alg.longestSubstring(string);
        assertEquals("abefd", longested);
    }

    @Test
    void lengthOfLongestSubstring() {
        String string = "knfjsjhjkda";
        int length = alg.lengthOfLongestSubstring(string);
        assertEquals(5, length);
    }
}