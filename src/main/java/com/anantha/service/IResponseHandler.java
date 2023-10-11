package com.anantha.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author anantha
 */
public interface IResponseHandler {
  /**
   * Build custom response
   * @param status Final status for operation
   * @param message Response message
   * @param responseData Data to be sent
   * @param httpStatus HTTP status to be sent
   * @return
   */
  public ResponseEntity<Object> generateResponse(Boolean status, String message, Object responseData, HttpStatus httpStatus);
}