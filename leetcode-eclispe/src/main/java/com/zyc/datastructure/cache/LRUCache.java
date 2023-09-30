package com.zyc.datastructure.cache;

import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private int capacity; // Entry的最大数量

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity, float loadFactor) {
        super(capacity, loadFactor, true);
        this.capacity = capacity;
    }

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
