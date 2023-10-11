package com.anantha.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHandler {
  public ResponseEntity<Object> generateResponse(Boolean status, String message, Object responseData, HttpStatus httpStatus) {
    Map<String, Object> map = new HashMap<String, Object>();
    
    map.put("status", status);
    map.put("message", message);
    map.put("data", responseData);

    return new ResponseEntity<Object>(map, httpStatus);
  }
}
