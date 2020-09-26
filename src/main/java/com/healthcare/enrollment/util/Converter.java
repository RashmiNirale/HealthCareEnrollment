package com.healthcare.enrollment.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.enrollment.controller.EnrollmentController;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
