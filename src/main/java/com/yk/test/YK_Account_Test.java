package com.yk.test;

import com.yk.common.AccountRoleConstant;
import com.yk.common.DeptConstant;
import com.yk.common.SexConstant;
import com.yk.model.*;
import com.yk.model.cl.ClassItemEntityCL;
import com.yk.model.cl.CourseEntityCL;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import platform.DAO.utils.BaseDAO;
import platform.DAO.utils.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by dylanyang on 10/11/15.
 */
public class YK_Account_Test {
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


        ClassItemEntity classItem = new ClassItemEntity();
        classItem.setClassNo("43");
        classItem.setYear(2015);
        session.save(classItem);

        StudentEntity stue = new StudentEntity();
        stue.setStu_num("201543100480");
        stue.setStu_name("李彤");
        stue.setStu_birthplace("昆明");
        stue.setStu_sex(SexConstant.MALE);
        stue.setClassItem_id(classItem);
        session.save(stue);

        CourseEntity course = new CourseEntity();
        course.setCourse_name("计算机");
        course.setCourse_num("CS001");
        course.setCredit(2);
        session.save(course);

        TeacherEntity te = new TeacherEntity();
        te.setStaffId("060178");
        te.setClassItem_id(classItem);
        te.setCourse_id(course);
        session.save(te);


        SelectedCourseEntity sc = new SelectedCourseEntity();
        sc.setStu_id(stue);
        sc.setCourse_id(course);
        session.save(sc);

        stue = new StudentEntity();
        stue.setStu_num("201543100481");
        stue.setStu_name("晨晨");
        stue.setStu_birthplace("昆明");
        stue.setStu_sex(SexConstant.FEMALE);
        stue.setClassItem_id(classItem);
        session.save(stue);



        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date=dd.parse("2007-12-12");
        }catch (ParseException e) {
            e.printStackTrace();
        }
        AccountEntity ae = new AccountEntity();
        ae.setUsername("060178");
        ae.setPassword("123");
        ae.setName_ch("道道");
        ae.setCreate_time(date);
        ae.setEmail("tf@163.com");
        ae.setRole(AccountRoleConstant.ADMINISTRATOR);
        ae.setId_card("530102198212130017");
        ae.setDept(DeptConstant.YK_OFFICE);
        ae.setTeacher_id(te);

        session.save(ae);

        CourseEntity ce = new CourseEntity();
        ce.setCourse_num("CS001");
        ce.setCourse_name("计算机");
        ce.setCredit(2);
        session.save(ce);


        tx.commit();
        session.close();
    }
    @Test
    public void addAdmin(){
        ClassItemEntityCL classCL = new ClassItemEntityCL();
        ClassItemEntity classItemEntity = (ClassItemEntity)classCL.getClassItemEntityByClassItemId(ClassItemEntity.class, 51);

        CourseEntityCL courseCL = new CourseEntityCL();
        CourseEntity ce = (CourseEntity)courseCL.getCourseEntity(CourseEntity.class,1);

        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        TeacherEntity te = new TeacherEntity();
        te.setStaffId("000000");
        te.setClassItem_id(classItemEntity);
        te.setCourse_id(ce);
        session.save(te);


        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date=dd.parse("2007-12-12");
        }catch (ParseException e) {
            e.printStackTrace();
        }
        AccountEntity ae = new AccountEntity();
        ae.setUsername("000000");
        ae.setPassword("123");
        ae.setName_ch("道道");
        ae.setCreate_time(date);
        ae.setEmail("tf@163.com");
        ae.setRole(AccountRoleConstant.ADMINISTRATOR);
        ae.setId_card("530102198212130017");
        ae.setDept(DeptConstant.YK_OFFICE);
        ae.setTeacher_id(te);

        session.save(ae);
        tx.commit();
        session.close();
    }
    @Test
    public void addClassItem(){
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        try {
            ClassItemEntity classItem = new ClassItemEntity();
            classItem.setClassNo("42");
            classItem.setYear(2015);
            session.save(classItem);

            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
                throw new RuntimeException(e.getMessage());
            }
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
    @Test
    public void addStu(){
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();

        ClassItemEntity classItem = new ClassItemEntity();
        classItem.setClassNo("001");
        classItem.setYear(2015);
        session.save(classItem);

        StudentEntity stue = new StudentEntity();
        stue.setStu_num("201543100480");
        stue.setStu_name("sea");
        stue.setStu_birthplace("昆明");
        stue.setStu_sex(SexConstant.MALE);
        stue.setClassItem_id(classItem);
        session.save(stue);

        stue = new StudentEntity();
        stue.setStu_num("201543100480");
        stue.setStu_name("sea");
        stue.setStu_birthplace("昆明");
        stue.setStu_sex(SexConstant.MALE);
        stue.setClassItem_id(classItem);
        session.save(stue);

        tx.commit();
        session.close();
    }
    @Test
    public void get(){
        try{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();

            AccountEntity acc = (AccountEntity)session.get(AccountEntity.class,1);
            System.out.println(acc.getUsername());
            TeacherEntity teacher = acc.getTeacher_id();
            System.out.println(teacher.getStaffId());
            ClassItemEntity classItem = teacher.getClassItem_id();
            System.out.println(classItem.getClassNo());
            System.out.println("-------------------------------------------------");
            for (StudentEntity stue:classItem.getSetStudents()){
                System.out.println(stue.getStu_num());
                System.out.println(stue.getStu_name());
                System.out.println(stue.getStu_birthplace());
                classItem = stue.getClassItem_id();
                System.out.println(classItem.getClassNo());
            }
            System.out.println("-------------------------------------------------");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void hqlTest(){
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            String classNoStr = "001";
            String hql = "from ClassItemEntity cl,StudentEntity st where cl.classNo=? and st.classItem_id=cl.id";

            List list = BaseDAO.executeQuery(hql,classNoStr);
            for (int i=0;i < list.size();i++){
                Object[] o = (Object[])list.get(i);
                String classNo = ((ClassItemEntity)o[0]).getClassNo();
                String stu_num = ((StudentEntity)o[1]).getStu_num();
                System.out.println("classNo="+classNo+","+"stu_num="+stu_num);

            }
//            for (AccountEntity acc : list)
//                System.out.println(acc.getUsername());
//            AccountEntity acc = (AccountEntity) session.createQuery(hql).setMaxResults(1).uniqueResult();
//            System.out.println(acc.getUsername());
//
//            hql = "select a.username,a.password from AccountEntity a";
//            query = session.createQuery(hql);
//            List<Object[]> list1 = query.list();
//            for (Object objs[] : list1) {
//                for (Object obj : objs) {
//                    System.out.println(obj);
//                }
//                System.out.println("-------------------------------------------");
//            }
//            System.out.println("-------------------------------------------");
//
//            hql = "select new AccountEntity(a.username,a.password) from AccountEntity a";
//            query = session.createQuery(hql);
//            List<AccountEntity> list2 = (List<AccountEntity>) query.list();
//            for (AccountEntity acc2 : list2) {
//                System.out.println(acc2);
//            }
//
//            hql = "from AccountEntity a where a.id not between 3 and 8";
//            query = session.createQuery(hql);
//            List<AccountEntity> list3 = (List<AccountEntity>) query.list();
//            for (AccountEntity acc3 : list3) {
//                System.out.println(acc3);
//            }
//
//            hql = "from AccountEntity a where a.id=?";
//            query = session.createQuery(hql);
//            query.setInteger(0,1);
//            List<AccountEntity> list4 = (List<AccountEntity>)query.list();
//            System.out.println(list4);
            tx.commit();
            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }
    @Test
    public void delAcc(){
        Class clazz = AccountEntity.class;
        this.excuteDel(clazz,1);
    }
    public void excuteDel(Class clazz,int id){
        try{
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
//            String hql = "delete from AccountEntity as a where a.id = 1";
//            Query query = session.createQuery(hql);
//            int n = query.executeUpdate();
//            System.out.println();

            Object o = (Object)session.get(clazz,id);
            session.delete(o);
            tx.commit();
            session.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestQuery(){
//        String hql = "select a.username,a.password,a.name_ch,a.create_time,a.email,a.role,a.id_card,a.dept t.staffId from AccountEntity a join a.TeacherEntity as t where a.id = t.id";
        String hql = "select c.id from ClassItemEntity c where c.classNo = ?";
        ArrayList list = BaseDAO.executeQuery(hql,"42");
//        ArrayList list = BaseDAO.executeQuery(hql,1);
//        for (int i = 0;i < list.size();i++){
//            Object[] o = (Object[])list.get(0);
//            System.out.println(((AccountEntity)o[0]).getUsername());
//        }
        System.out.println(list);
    }
    @Test
    public void pageTest(){
        String hql = "from CourseEntity c";
        List list = BaseDAO.pagingQuery(hql,5,5);
        System.out.println(list);

    }
}
