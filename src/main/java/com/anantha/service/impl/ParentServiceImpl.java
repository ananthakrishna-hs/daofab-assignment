package com.anantha.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.anantha.entity.Parent;
import com.anantha.service.IParentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParentServiceImpl implements IParentService {
    private static final Logger log = LogManager.getLogger(ParentServiceImpl.class.getName());

    public List<Parent> getParents(Integer limit, Integer skip) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Parent>> typeReference = new TypeReference<List<Parent>>(){};
        System.out.println(System.getProperty("user.dir"));
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/Parent.json");

        try {
            List<Parent> parentList = mapper.readValue(inputStream, typeReference);
            Collections.sort(parentList, (a, b) -> a.getId().compareTo(b.getId()));

            log.debug("Fetching records for limit:" + limit + "; skip:" + skip);

            if (parentList.size() < (limit + skip)) {
                System.out.println(parentList.get(skip));
                return parentList.subList(skip, parentList.size());
            }
            
            return parentList.subList(skip, skip + limit);
        } catch (IndexOutOfBoundsException i) {
            return new ArrayList<Parent>();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("Unable to read file");
        }
    }
}