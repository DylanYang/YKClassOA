package platform.DAO.utils;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanyang on 12/4/15.
 */
public class BaseDAO {
    /**
     * dylan
     *
     * @param obj
     * @return result
     */
    public static boolean add(Object... obj){
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            for (int i = 0;i < obj.length;i++){
                session.save(obj[i]);
            }
            tx.commit();
            result = true;
        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return result;
    }

    /**
     * dylan
     *
     * @param obj
     * @return resultx1
     */
    public static boolean update(Object obj){
        Session session = null;
        Transaction tx = null;
        boolean result = false;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            result = true;

        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return result;
    }

    /**
     * dylan
     *
     * @param hql
     * @param params
     * @return n
     */
    public static int executeUpdate(String hql,Object... params){
        Session session = null;
        Transaction tx = null;
        int n = 0;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);

            if (params != null && params.length > 0){
                for (int i= 0;i < params.length;i++){
                    query.setParameter(i,params[i]);
                }
            }
            System.out.println("query influenced: " + query.getQueryString());
            n  = query.executeUpdate();
            System.out.println("query influence: " + n);
            tx.commit();
        }catch (Exception e){
           BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return n;
    }

    /**
     * dylan
     *
     * @param hql
     * @param params
     * @return list(Arraylist)
     */
    public static ArrayList executeQuery(String hql,Object... params){
        Session session = null;
        Transaction tx = null;
        ArrayList list = null;

        try{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (params!=null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i,params[i]);
                }
            }
            list = (ArrayList)query.list();
        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return list;
    }
    /**
     * dylan
     *
     * @param hql
     * @param firstNo
     * @param maxNo
     * @param params
     * @return list
     */
    public static ArrayList pagingQuery(String hql,int firstNo,int maxNo,Object... params){
        Session session = null;
        Transaction tx = null;
        ArrayList list = null;

        try{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setFirstResult(firstNo);
            query.setMaxResults(maxNo);
            if (params!=null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i,params[i]);
                }
            }
            list = (ArrayList)query.list();
        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return list;
    }

    /**
     * dylan
     * @param clazz
     * @param id
     * @return obj
     */
    public static Object sessionGetQuery(Class clazz,int id){
        Session session = null;
        Transaction tx = null;
        Object obj = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            obj = session.get(clazz,id);

            tx.commit();
        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return obj;
    }

    /**
     * dylan
     *
     * @param hql
     * @param params
     * @return int
     */
    public static int getRowCount(String hql,Object... params){
        Session session = null;
        Transaction tx = null;
        int count = 0;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (params!=null && params.length > 0){
                for (int i = 0; i < params.length; i++){
                    query.setParameter(i,params[i]);
                }
            }
            List list = query.list();
            count = Integer.parseInt(list.get(0).toString());
        }catch (Exception e){
            BaseDAO.txRollBack(tx,e);
        }finally {
            BaseDAO.closeSession(session);
        }
        return count;
    }
    /**
     * dylan
     *
     * @param clazz
     * @param id
     */
    public static void dellObjByCascade(Class clazz,int id){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try{
            Object o = (Object)session.get(clazz,id);
            session.delete(o);
            tx.commit();
            session.close();

        }catch (Exception e){
            BaseDAO.txRollBack(tx, e);
        }finally {
            BaseDAO.closeSession(session);
        }
    }
    /**
     * dylan
     *
     * @param tx
     * @param e
     */
    public static void txRollBack(Transaction tx,Exception e){
        if (tx!=null){
            tx.rollback();
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * dylan
     *
     * @param session
     */
    public static void closeSession(Session session){
        if (session != null && session.isOpen()){
            session.close();
        }
    }

    /**
     * dylan
     * @param clazz
     * @param id
     * @return Class
     */
    public static Object getObjClassBySessionGetQuery(Class clazz,int id){
        return BaseDAO.sessionGetQuery(clazz,id);
    }
}
