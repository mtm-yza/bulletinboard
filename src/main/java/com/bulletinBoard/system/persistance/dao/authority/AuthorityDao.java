package com.bulletinBoard.system.persistance.dao.authority;

import com.bulletinBoard.system.persistance.entity.Authority;

/**
 * <h2>AuthorityDao Class</h2>
 * <p>
 * Process for Displaying AuthorityDao
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface AuthorityDao {

    /**
     * <h2>dbInsertAuthority</h2>
     * <p>
     * Insert Authority
     * </p>
     *
     * @param authority
     */
    public void dbInsertAuthority(Authority authority);

    /**
     * <h2>dbGetAuthorityById</h2>
     * <p>
     * Get AuthorityById
     * </p>
     *
     * @param id int
     * @return Authority
     */
    public Authority dbGetAuthorityById(int id);

    /**
     * <h2>dbGetAuthorityCount</h2>
     * <p>
     * Get Authority Count
     * </p>
     *
     * @return long
     */
    public long dbGetAuthorityCount();
}
