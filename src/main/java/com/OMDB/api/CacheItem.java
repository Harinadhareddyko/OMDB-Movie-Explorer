package com.OMDB.api;

public class CacheItem {
    private String data;
    private long timestamp;

    public CacheItem(String data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public String getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}