package org.sen4ik.utils;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.builder.EqualsBuilder;
import com.jayway.jsonpath.JsonPath;

import java.lang.reflect.Type;
import java.util.Map;

@Slf4j
public class JsonUtil {

    public String responseToJson(ValidatableResponse response){
        return response.extract().asString();
    }

    public String getStringFromJsonResponse(ValidatableResponse response, String jsonPath){
        log.info("CALLED: getStringFromJsonResponse()");
        String jsonBody = response.extract().asString();
        String str = JsonPath.read(jsonBody, jsonPath);
        log.info(jsonPath + ": " + str);
        return str;
    }

    public int getIntFromJsonResponse(ValidatableResponse response, String jsonPath) {
        log.info("CALLED: getIntFromJsonResponse()");
        String jsonBody = response.extract().asString();
        return getIntFromJsonResponse(jsonBody, jsonPath);
    }

    public int getIntFromJsonResponse(String json, String jsonPath){
        log.info("CALLED: getIntFromJsonResponse()");
        int i = JsonPath.read(json, jsonPath);
        log.info(jsonPath + ": " + i);
        return i;
    }

    public static String objectToJson_withNulls(Object obj){
        log.info("CALLED: objectToJson_withNulls()");
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(obj);
        log.info("json: " + json);
        return json;
    }

    public static String objectToJson_withoutNulls(Object obj){
        log.info("CALLED: objectToJson_withoutNulls()");
        String json = new Gson().toJson(obj);
        log.info("json: " + json);
        return json;
    }

    public static Object objectFromJson(String json, Class<?> cl){
        log.info("CALLED: objectFromJson()");
        Gson gson = new Gson();
        return gson.fromJson(json, cl);
    }

    private boolean compareResponseWithExpected(ValidatableResponse response, Object expectedObj){
        log.info("CALLED: compareResponseWithExpected()");
        String json = response.extract().asString();
        Object resultObj = new Gson().fromJson(json, expectedObj.getClass());
        boolean result = EqualsBuilder.reflectionEquals(resultObj, expectedObj);
        return result;
    }

    public JsonElement jsonToJsonElement(String json){
        log.info("CALLED: jsonToJsonElement()");
        JsonElement jsonElement =  new JsonParser().parse(json).getAsJsonArray();
        return jsonElement;
    }

    public JsonElement objectToJsonElement(Object obj){
        return jsonToJsonElement(objectToJson_withNulls(obj));
    }

    public boolean compareResponseWithObject(ValidatableResponse response, Object obj){
        log.info("CALLED: compareResponseWithObject()");
        String json = response.extract().asString();
        JsonElement responseArray =  jsonToJsonElement(json);
        JsonElement expectedArray =   objectToJsonElement(obj);
        return responseArray.equals(expectedArray);
    }

    public MapDifference<String, Object> compareTwoJsonsAndGetTheDifference(String json1, String json2) {
        log.info("CALLED: compareTwoJsonsAndGetTheDifference()");
        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> firstMap = g.fromJson(json1, mapType);
        Map<String, Object> secondMap = g.fromJson(json2, mapType);
        log.info(Maps.difference(firstMap, secondMap).toString());
        return Maps.difference(firstMap, secondMap);
    }

}
