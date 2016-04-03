package com.yk.test;

import com.yk.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import platform.DAO.utils.HibernateUtil;


/**
 * Created by dylanyang on 10/3/15.
 */
public class UserTest {
    Session session = null;
    Transaction tx = null;

    @Test
    public void sechmDDL(){
        Configuration cfg = new Configuration().configure();
        SchemaExport schema = new SchemaExport(cfg);
        schema.setFormat(true).create(true,true);
    }
    @Test
    public void addUser(){
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();

        UserEntity ue = new UserEntity();
        ue.setName("悟道");
        ue.setPasswd("123");
        ue.setAge(12);
        session.save(ue);

        tx.commit();
        session.close();

    }
}