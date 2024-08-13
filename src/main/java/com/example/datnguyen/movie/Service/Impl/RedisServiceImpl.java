package com.example.datnguyen.movie.Service.Impl;

import com.example.datnguyen.movie.Service.BaseRedisService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RedisServiceImpl implements BaseRedisService {
    final RedisTemplate<String,Object> redisTemplate;
    @Override
    public void saveKey(String key, String Value) {
        redisTemplate.opsForValue().set(key,Value);
    }

    @Override
    public void saveKeyAndTTL(String key, String value, long ttl) {
        redisTemplate.opsForValue().set(key,value,ttl, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
