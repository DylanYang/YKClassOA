package com.yk.model.cl;

import platform.DAO.utils.BaseDAO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dylanyang on 12/20/15.
 */
public class ClassItemEntityCL {
    /**
     * dylan
     * @param classNo
     * @return classItem Id
     */
    public int getClassItemId(String classNo){
        String hql = "select c.id from ClassItemEntity c where c.classNo = ?";
        return  (Integer) BaseDAO.executeQuery(hql, classNo).get(0);//get classItem id
    }
    /**
     * dylan
     * @param clazz
     * @param id
     * @return obj
     */
    public Object getClassItemEntityByClassItemId(Class clazz,int id){
        return BaseDAO.getObjClassBySessionGetQuery(clazz,id);
    }

    /**
     * dylan
     * @param id
     * @return classNo
     */
    public String getClassNoByClassItemId(int id){
        String hql = "select c.classNo from ClassItemEntity c where c.id = ?";
        String classNo = (String)BaseDAO.executeQuery(hql,id).get(0);
        return classNo;
    }

    /**
     * dylan
     * @param key
     * @param request
     * @return id
     */
    public int getClassIdFromSessionRequest(String key,HttpServletRequest request){
        return (Integer)request.getSession().getAttribute(key);
    }
}
