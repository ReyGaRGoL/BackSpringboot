package com.example.postmanagement.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(PostNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> exepcionHandler(PostNotFoundException ex){
		Map<String,String> map = new HashMap();
		map.put("message", ex.getMessage());
		return map;
	}

}
