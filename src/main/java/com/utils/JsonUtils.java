package com.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
    public static JsonObject getJsonFromString(String data){
        return new JsonParser()
                .parse(data)
                .getAsJsonObject();
    }

    public static Object getObjectFromJson(String json, Object object)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(json, object.getClass());
        }catch (Exception ignored){}

        return object;
    }

    public static String getJsonFromObject(Object object)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.writeValueAsString(object);
        }catch (Exception ignored){}

        return "";
    }

    public static String[] getStringArray(JsonArray jsonArray) {
        String[] stringArray = null;
        if (jsonArray != null) {
            int length = jsonArray.size();
            stringArray = new String[length];
            for (int i = 0; i < length; i++) {
                stringArray[i] = jsonArray.get(i).getAsString();
            }
        }
        return stringArray;
    }
}
