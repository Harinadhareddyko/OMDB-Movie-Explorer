package com.OMDB.api;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private final int MAX_SIZE;
    private final long EXPIRY_MS;
   

    private Map<String, CacheItem> cache;

    public LRUCache(int maxSize, long expiryMs) {
        this.MAX_SIZE = maxSize;
        this.EXPIRY_MS = expiryMs;

        this.cache = new LinkedHashMap<String, CacheItem>(maxSize, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, CacheItem> eldest) {
                return size() > MAX_SIZE;
            }
        };
    }

    public synchronized void put(String key, String data) {
        cache.put(key, new CacheItem(data));
    }

    public synchronized String get(String key) {
        CacheItem item = cache.get(key);

        if (item == null)
            return null;

        long now = System.currentTimeMillis();
        if (now - item.getTimestamp() > EXPIRY_MS) {
            cache.remove(key);  // expired
            return null;
        }

        return item.getData();
    }
}
