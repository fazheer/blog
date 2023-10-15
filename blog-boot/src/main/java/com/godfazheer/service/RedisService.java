package com.godfazheer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    <T> Long setListToZSet(String key, List<T> list) throws JsonProcessingException;
     Long getZSetRank(String key, String member) throws JsonProcessingException;

    Set<String> getZSetRangeByRank(String key, Long rank);

    Long getZSetSize(String key) throws JsonProcessingException;

    <T> void setObject(String key, T data) throws JsonProcessingException;

    void incrValue(String key);

    <T> void setObject(String key, T data, Integer timeout, TimeUnit timeUnit) throws JsonProcessingException;

    <T> T getObejct(String key, Class<T> type) throws JsonProcessingException;

    <T> long setHash(String key, String hashKey, T value) throws JsonProcessingException;

    void delKey(String key) throws JsonProcessingException;

    void delKey(List<String> keys) throws JsonProcessingException;

    Boolean setExpire(String key, long timeout, TimeUnit timeUnit);
}
