package com.anantha.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anantha.entity.Child;
import com.anantha.entity.Parent;
import com.anantha.service.impl.ChildServiceImpl;
import com.anantha.service.impl.ParentServiceImpl;
import com.anantha.service.impl.ResponseHandlerImpl;

@RestController
@RequestMapping("/api")
public class ParentController {
  @Autowired
  private ParentServiceImpl parentService;

  @Autowired
  private ResponseHandlerImpl responseService;

  @Autowired
  private ChildServiceImpl childService;

  @GetMapping("/parents")
  public ResponseEntity<Object> getParents(@RequestParam(name = "size", required = false, defaultValue = "2") Integer limit, @RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip) {
    try {
      List<Parent> list = parentService.getParents(limit, skip);

      return responseService.generateResponse(true, "List fetched successfully", list, HttpStatus.OK);
    } catch (Exception e) {
      return responseService.generateResponse(false, e.getMessage(), new ArrayList<Parent>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/children")
  public ResponseEntity<Object> getChildren(@RequestParam(name = "parentId") Integer parentId) {
    try {
      Parent parent = parentService.getParentById(parentId);

      if (null == parent) {
        return responseService.generateResponse(false, "Invalid Parent ID",  new ArrayList<Child>(), HttpStatus.NOT_FOUND);
      }
      List<Child> list = childService.getChildren(parentId);

      Map<String, Object> data = new HashMap<String, Object>();
      data.put("parent", parent);
      data.put("children", list);

      return responseService.generateResponse(true, "List fetched successfully", data, HttpStatus.OK);
    } catch (Exception e) {
      return responseService.generateResponse(false, e.getMessage(), new ArrayList<Parent>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
