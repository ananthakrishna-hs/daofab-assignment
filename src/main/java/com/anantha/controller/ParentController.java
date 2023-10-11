package com.anantha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anantha.entity.Parent;
import com.anantha.service.ParentService;
import com.anantha.service.ResponseHandler;

@RestController
@RequestMapping("/api")
public class ParentController {
  @Autowired
  private ParentService parentService;

  @Autowired
  private ResponseHandler responseService;

  @GetMapping("/parents")
  public ResponseEntity<Object> getParents(@RequestParam(name = "size", required = false, defaultValue = "2") Integer limit, @RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip) {
    try {
      List<Parent> list = parentService.getParents(limit, skip);

      return responseService.generateResponse(true, "List fetched successfully", list, HttpStatus.OK);
    } catch (Exception e) {
      return responseService.generateResponse(false, e.getMessage(), new ArrayList<Parent>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
