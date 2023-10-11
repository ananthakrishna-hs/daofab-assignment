package com.anantha.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IResponseHandler {
  public ResponseEntity<Object> generateResponse(Boolean status, String message, Object responseData, HttpStatus httpStatus);
}