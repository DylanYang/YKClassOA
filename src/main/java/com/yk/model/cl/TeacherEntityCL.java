package com.yk.model.cl;

import platform.DAO.utils.BaseDAO;

import java.util.List;

/**
 * Created by dylanyang on 12/20/15.
 */
public class TeacherEntityCL {
    /**
     * dylan
     * @return acclist
     */
    public List getAllTeacherAccount(){
        String hql = "from AccountEntity a,TeacherEntity t where t.id =a.id";
        List alist = BaseDAO.executeQuery(hql);
        return alist;
    }
}
