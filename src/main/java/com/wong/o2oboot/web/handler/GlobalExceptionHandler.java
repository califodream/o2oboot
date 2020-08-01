package com.wong.o2oboot.web.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, Object> handle(Exception e){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("success", false);
		if(e instanceof RuntimeException) {
			log.error("ERROR: " + e.getMessage());
			modelMap.put("errMsg", e.getMessage());
		}
		else {
			log.error("ERROR: " + e.getMessage());
			modelMap.put("errMsg", "Unknown Error");
		}
		return modelMap;
	}
	
}
