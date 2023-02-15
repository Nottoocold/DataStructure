package com.zyc.datastructure.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zyc
 * @package: com.zyc.datastructure.tree
 * @description:
 * @create_time 2022/12/5 - 周一
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrieTreeTest {
    private static final TrieTree trieTree = new TrieTree();

    @BeforeAll
    @Order(1)
    static void add() {
        try (BufferedReader reader =
                     Files.newBufferedReader(
                             Paths.get("src\\main\\resources", "word.txt"),
                             StandardCharsets.UTF_8)) {
            String word;
            while ((word = reader.readLine()) != null) {
                trieTree.add(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("build ok.");
    }

    @Test
    @Order(2)
    void search() {
        boolean f = trieTree.search("bcdefgh");
        Assertions.assertTrue(f);
        f = trieTree.search("dasdsaf");
        Assertions.assertFalse(f);
    }

    @Test
    @Order(3)
    void startsWith() {
        boolean f = trieTree.startsWith("bcd");
        Assertions.assertTrue(f);
    }

    @Test
    @Order(5)
    void remove() {
        trieTree.remove("dasda");
    }

    @Test
    @Order(4)
    void getSize() {
        int size = trieTree.getSize();
        Assertions.assertEquals(18815, size);
    }
}