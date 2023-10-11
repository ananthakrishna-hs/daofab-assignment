package com.anantha.service;

import java.util.List;

import com.anantha.entity.Parent;

/**
 * @author anantha
 */
public interface IParentService {
    /**
     * Fetch all parent txns
     * @param limit Limit of txns per page
     * @param skip Offset of pages to skip
     * @return Paginated list of parent txns
     * @throws Exception
     */
    public List<Parent> getParents(Integer limit, Integer skip) throws Exception;

    /**
     * Fetch parent txn by ID
     * @param parentId ID of txn
     * @return Transaction
     * @throws Exception
     */
    public Parent getParentById(Integer parentId) throws Exception;
}
