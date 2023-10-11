package com.anantha.service;

import java.util.List;

import com.anantha.entity.Parent;

public interface IParentService {
    public List<Parent> getParents(Integer limit, Integer skip) throws Exception;
}
