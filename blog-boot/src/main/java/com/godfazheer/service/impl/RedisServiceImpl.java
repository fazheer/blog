package com.godfazheer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.service.RedisService;
import com.godfazheer.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl  implements RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /*
    * 把排序好的数据放进去*/
    @Override
    public <T> Long setListToZSet(String key, List<T> list) throws JsonProcessingException {
        long count = 0L;
        for (T data : list) {
            String value = JacksonUtils.toJsonString(data);
            stringRedisTemplate.opsForZSet().add(key,value,count++);
        }
        return  count;
    }

    @Override
    public  Long getZSetRank(String key, String member) {
        return stringRedisTemplate.opsForZSet().rank(key, member);
    }
    @Override
    public Set<String> getZSetRangeByRank(String key,Long rank) {
        return stringRedisTemplate.opsForZSet().range(key, rank, rank);
    }

    @Override
    public  Long getZSetSize(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }


    @Override
    public <T> void setObject(String key, T data) throws JsonProcessingException {
        String value = JacksonUtils.toJsonString(data);
        stringRedisTemplate.opsForValue().set(key,value);
    }
    @Override
    public void incrValue(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }
    @Override
    public <T> void setObject(String key, T data, Integer timeout, TimeUnit timeUnit) throws JsonProcessingException {
        String value = JacksonUtils.toJsonString(data);
        stringRedisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    @Override
    public <T> T getObejct(String key, Class<T> type) throws JsonProcessingException {
        String data = stringRedisTemplate.opsForValue().get(key);
        return JacksonUtils.mapper.readValue(data, type);
    }

    @Override
    public <T> long setHash(String key, String hashKey, T value) throws JsonProcessingException {
        String jsonString = JacksonUtils.toJsonString(value);
        stringRedisTemplate.opsForHash().put(key,hashKey,jsonString);
        return 0;
    }

    @Override
    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }
    @Override
    public void delKey(List<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    @Override
    public Boolean setExpire(String key, long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, timeout, timeUnit);
    }
}
