package com.heavengate.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	
	public static String toJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch(JsonProcessingException ex) {
			ex.printStackTrace(System.err);
		}
		return null;
	}
}
