package com.anantha.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.anantha.entity.Parent;
import com.anantha.entity.Parents;
import com.anantha.service.IParentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author anantha
 */
@Service
public class ParentServiceImpl implements IParentService {
    private static final Logger log = LogManager.getLogger(ParentServiceImpl.class.getName());

    public Parents getAllParents(Integer limit, Integer page) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Parent>> typeReference = new TypeReference<List<Parent>>(){};
        System.out.println(System.getProperty("user.dir"));
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/Parent.json");

        try {
            List<Parent> parentList = mapper.readValue(inputStream, typeReference);
            Collections.sort(parentList, (a, b) -> a.getId().compareTo(b.getId()));

            log.debug("Fetching records for limit:" + limit + "; page:" + page);

            List<Parent> subList = new ArrayList<Parent>();

            Integer skip = (page - 1) * limit;

            if (parentList.size() < (limit + skip)) {
                subList = parentList.subList(skip, parentList.size());
            } else {
                subList = parentList.subList(skip, skip + limit);
            }
            
            return new Parents(parentList.size(), subList);
        } catch (IndexOutOfBoundsException i) {
            return new Parents(0, new ArrayList<Parent>());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("Unable to read file");
        }
    }

    public Parent getParentById(Integer parentId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Parent>> typeReference = new TypeReference<List<Parent>>(){};
        System.out.println(System.getProperty("user.dir"));
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/Parent.json");

        try {
            List<Parent> parentList = mapper.readValue(inputStream, typeReference);
            Collections.sort(parentList, (a, b) -> a.getId().compareTo(b.getId()));

            log.debug("Fetching records for parent with ID:" + parentId);

            Parent element = parentList.stream().filter(ele -> ele.getId().equals(parentId)).findFirst().orElse(null);

            return element;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("Unable to read file");
        }
    }
}