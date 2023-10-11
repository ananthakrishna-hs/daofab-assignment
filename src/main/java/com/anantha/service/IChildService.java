package com.anantha.service;

import java.util.List;

import com.anantha.entity.Child;

/**
 * @author anantha
 */
public interface IChildService {
    /**
     * Fetch transactions by parent ID
     * @param parentId ID of parent txn
     * @return List of children txns
     * @throws Exception
     */
    public List<Child> getChildren(Integer parentId) throws Exception;
}
