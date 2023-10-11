package com.anantha.service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anantha.entity.Parent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParentService {
    public List<Parent> getParents(Integer limit, Integer skip) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Parent>> typeReference = new TypeReference<List<Parent>>(){};
        System.out.println(System.getProperty("user.dir"));
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/Parent.json");

        try {
            List<Parent> parentList = mapper.readValue(inputStream, typeReference);
            Collections.sort(parentList, (a, b) -> a.getId().compareTo(b.getId()));

            if (parentList.size() < (limit + skip)) {
                System.out.println(parentList.get(skip));
                return parentList.subList(skip, parentList.size());
            }
            
            return parentList.subList(skip, skip + limit);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Unable to read file");
        }
    }
}