package com.example.datnguyen.movie.Service;

public interface BaseRedisService {
    void saveKey(String key,String Value);
    void saveKeyAndTTL(String key,String value,long ttl);
    Boolean hasKey(String key);
}
