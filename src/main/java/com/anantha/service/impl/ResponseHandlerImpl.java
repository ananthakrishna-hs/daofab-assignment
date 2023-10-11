package com.anantha.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anantha.service.IResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anantha
 */
@Service
public class ResponseHandlerImpl implements IResponseHandler {
  public ResponseEntity<Object> generateResponse(Boolean status, String message, Object responseData, HttpStatus httpStatus) {
    Map<String, Object> map = new HashMap<String, Object>();
    
    map.put("status", status);
    map.put("message", message);
    map.put("data", responseData);

    return new ResponseEntity<Object>(map, httpStatus);
  }
}
