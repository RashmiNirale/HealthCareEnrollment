package com.healthcare.enrollment.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Converter {

	ObjectMapper mapper = new ObjectMapper();

	public Object convert(Object from, Class<?> clazz) {
		try {
			return mapper.readValue(mapper.writeValueAsString(from), clazz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
