package com.bulletinBoard.system.persistance.dao.authority.impl;

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
@Transactional
@Repository
public class AuthorityDaoImpl implements AuthorityDao {

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
     * <h2>dbGetAuthorityCount</h2>
     * <p>
     * Get Authority Count
     * </p>
     * 
     * @return long
     */
    @Override
    public long dbGetAuthorityCount() {
        Long count = (Long) this.getSession().createQuery("SELECT COUNT(id) FROM Authority").uniqueResult();
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
