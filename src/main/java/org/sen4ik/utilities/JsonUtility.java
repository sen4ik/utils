package org.sen4ik.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.PathNotFoundException;

public class JsonUtility {

	public static Object jsonToObject(String json, Class<?> clazz) throws JsonProcessingException {
		return new ObjectMapper().readValue(json, clazz);
	}

	public static String objectToJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

	public static String objectToJson(Object object, JsonInclude.Include include) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(include);
		return mapper.writeValueAsString(object);
	}

	public static String jacksonPrettyPrint(String uglyJson) throws JsonProcessingException {
		if (uglyJson != null && !uglyJson.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(uglyJson));
		}
		return null;
	}

	public static boolean isPathPresent(DocumentContext dc, String jsonPath) {
		try {
			dc.read(jsonPath);
			return true;
		} catch (PathNotFoundException e) {
			return false;
		}
	}

}