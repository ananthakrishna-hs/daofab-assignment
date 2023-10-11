package com.anantha.service;

import java.util.List;

import com.anantha.entity.Child;

public interface IChildService {
    public List<Child> getChildren(Integer parentId) throws Exception;
}
