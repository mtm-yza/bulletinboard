package com.bulletinBoard.system.persistance.dao.authority.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.dao.authority.AuthorityDao;
import com.bulletinBoard.system.persistance.entity.Authority;

/**
 * <h2>AuthorityDaoImpl Class</h2>
 * <p>
 * Implementation Class for Displaying AuthorityDaoImpl
 * </p>
 * 
 * @author YeZawAung
 *
 */
/**
 * <h2>AuthorityDaoImpl Class</h2>
 * <p>
 * Process for Displaying AuthorityDaoImpl
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Transactional
@Repository
public class AuthorityDaoImpl implements AuthorityDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * Table Name of Authority
     * </p>
     */
    private static final String TABLE_NAME = "Authority";

    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * SELECT_STMT
     * </p>
     */
    private static final String SELECT_STMT = " FROM " + TABLE_NAME;

    /**
     * <h2>COUNT_STMT</h2>
     * <p>
     * COUNT_STMT
     * </p>
     */
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>dbInsertAuthority</h2>
     * <p>
     * Insert Authority
     * </p>
     * 
     * @param authority Authority
     */
    @Override
    public void dbInsertAuthority(Authority authority) {
        this.sessionFactory.getCurrentSession().save(authority);
    }

    /**
     * <h2>dbGetAuthorityById</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id int
     * @return Authority
     */
    @SuppressWarnings("unchecked")
    @Override
    public Authority dbGetAuthorityById(int id) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE id=:id").toString();
        List<Authority> list = this.getSession().createQuery(stmt).setParameter("id", id).list();
        return (!list.isEmpty()) ? list.get(0) : null;
    }

    /**
     * <h2>dbGetAuthorityCount</h2>
     * <p>
     * Get Authority Count
     * </p>
     * 
     * @return long
     */
    @Override
    public long dbGetAuthorityCount() {
        Long count = (Long) this.getSession().createQuery(COUNT_STMT).uniqueResult();
        return count.intValue();
    }

    /**
     * <h2>getSession</h2>
     * <p>
     * Get Current Session
     * </p>
     *
     * @return
     * @return Session
     */
    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
