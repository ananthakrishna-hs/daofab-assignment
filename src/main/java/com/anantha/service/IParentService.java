package com.anantha.service;

import com.anantha.entity.Parent;
import com.anantha.entity.Parents;

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
    public Parents getAllParents(Integer limit, Integer skip) throws Exception;

    /**
     * Fetch parent txn by ID
     * @param parentId ID of txn
     * @return Transaction
     * @throws Exception
     */
    public Parent getParentById(Integer parentId) throws Exception;
}
