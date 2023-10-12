package com.anantha.service.impl;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.anantha.entity.Child;
import com.anantha.entity.Children;
import com.anantha.service.IChildService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author anantha
 */
@Service
public class ChildServiceImpl implements IChildService  {
    private static final Logger log = LogManager.getLogger(ChildServiceImpl.class.getName());

    public Children getChildrenByParentId(Integer parentId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Child>> typeReference = new TypeReference<List<Child>>(){};
        System.out.println(System.getProperty("user.dir"));
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/Child.json");

        try {
            List<Child> childrenList = mapper.readValue(inputStream, typeReference);
            Collections.sort(childrenList, (a, b) -> a.getId().compareTo(b.getId()));

            log.debug("Fetching records for parent:" + parentId);

            List<Child> subList = childrenList.stream().filter(element -> element.getParentId().equals(parentId)).toList();
            
            Children data = new Children(subList.size(), subList);

            return data;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("Unable to read file");
        }
    }
}
