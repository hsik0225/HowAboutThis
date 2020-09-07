package com.naru.howaboutthis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectToJsonConverter() {}

    public static String ObjectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
