package com.godfazheer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class JacksonUtils {
    public static ObjectMapper mapper = new JsonMapper();
    public  static String toJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
