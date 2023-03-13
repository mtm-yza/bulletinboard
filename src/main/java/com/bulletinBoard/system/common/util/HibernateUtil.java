package com.bulletinBoard.system.common.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bulletinBoard.system.persistance.entity.Post;


@EnableTransactionManagement
public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        return new Configuration()
                .addPackage("com.bulletinBoard.persistance.entity")
                .addAnnotatedClass(Post.class)
                .configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
