package com.OMDB.api;


public class CacheService {

    private static final int MAX_SIZE = 100;           // max 100 cached items
    private static final long EXPIRY = 5 * 60 * 1000;  // 5 minutes TTL

    private static LRUCache cache = new LRUCache(MAX_SIZE, EXPIRY);
    public static void save(String key, String data) {
        cache.put(key, data);
    }

    public static String get(String key) {
        return cache.get(key);
    }
}
