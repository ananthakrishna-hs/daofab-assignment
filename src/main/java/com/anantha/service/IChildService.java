package com.anantha.service;

import com.anantha.entity.Children;

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
    public Children getChildrenByParentId(Integer parentId) throws Exception;
}
