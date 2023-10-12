package com.anantha.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anantha.entity.Child;
import com.anantha.entity.Children;
import com.anantha.entity.Parent;
import com.anantha.entity.Parents;
import com.anantha.service.impl.ChildServiceImpl;
import com.anantha.service.impl.ParentServiceImpl;
import com.anantha.service.impl.ResponseHandlerImpl;

/*
 * Main route prefix
 * @author anantha
 */
@RestController
@RequestMapping("/api")
public class ParentController {
  @Autowired
  private ParentServiceImpl parentService;

  @Autowired
  private ResponseHandlerImpl responseService;

  @Autowired
  private ChildServiceImpl childService;

  /**
   * To fetch all parents
   * @param limit Items per page
   * @param skip Offset of list
   * @return Paginated response
   */
  @GetMapping("/parents")
  public ResponseEntity<Object> getParents(@RequestParam(name = "size", required = false, defaultValue = "2") Integer limit, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
    try {
      Parents parents = parentService.getAllParents(limit, page);

      Map<String, Object> payload = new HashMap<String, Object>();
      payload.put("list", parents.getList());
      payload.put("size", parents.getLength());

      return responseService.generateResponse(true, "List fetched successfully", payload, HttpStatus.OK);
    } catch (Exception e) {
      return responseService.generateResponse(false, e.getMessage(), new ArrayList<Parent>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * To fetch children list by parent ID
   * @param parentId ID of parent transaction
   * @return
   */
  @GetMapping("/children")
  public ResponseEntity<Object> getChildren(@RequestParam(name = "parentId") Integer parentId) {
    try {
      Parent parent = parentService.getParentById(parentId);

      if (null == parent) {
        return responseService.generateResponse(false, "Invalid Parent ID",  new ArrayList<Child>(), HttpStatus.NOT_FOUND);
      }
      Children data = childService.getChildrenByParentId(parentId);

      Map<String, Object> payload = new HashMap<String, Object>();
      payload.put("parent", parent);
      payload.put("children", data.getList());
      payload.put("size", data.getLength());

      return responseService.generateResponse(true, "List fetched successfully", payload, HttpStatus.OK);
    } catch (Exception e) {
      return responseService.generateResponse(false, e.getMessage(), new ArrayList<Parent>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
