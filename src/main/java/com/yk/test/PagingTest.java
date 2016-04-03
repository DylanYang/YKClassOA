package com.yk.test;

import com.yk.model.AccountEntity;
import com.yk.model.cl.AccountEntityCL;
import platform.DAO.utils.BaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanyang on 12/5/15.
 */
public class PagingTest{
    public static void main(String[] args){
        AccountEntityCL acl = new AccountEntityCL();
        String hql = "from AccountEntity";
        int pageNow = 0;
        int pageSize = 3;
        int pageCount = acl.getAccountPageCount(pageSize);
        List<AccountEntity> list = BaseDAO.pagingQuery(hql,pageNow,pageSize+pageNow);
        System.out.println(list);
//        System.out.println(BaseDAO.getRowCount(hql));
    }
}
