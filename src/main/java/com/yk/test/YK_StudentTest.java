package com.yk.test;

import com.yk.common.SexConstant;
import com.yk.common.StuPostionConstant;
import com.yk.common.StuSexConstant;
import com.yk.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import platform.DAO.utils.BaseDAO;
import platform.DAO.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanyang on 11/9/15.
 */
public class YK_StudentTest {
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

//        ClassItemEntity classItem = new ClassItemEntity();
//        classItem.setClassNo("42");
//        classItem.setYear(2015);
//        session.save(classItem);
//
//        classItem = new ClassItemEntity();
//        classItem.setClassNo("43");
//        classItem.setYear(2015);
//        session.save(classItem);
        ClassItemEntity classItemEntity = (ClassItemEntity)session.get(ClassItemEntity.class,3);



        StudentEntity stue = new StudentEntity();
        stue.setStu_num("201543100480");
        stue.setStu_name("张航");
        stue.setStu_birthplace("昆明");
        stue.setStu_sex(SexConstant.MALE);
        stue.setClassItem_id(classItemEntity);
        session.save(stue);

        CourseEntity course = (CourseEntity)session.get(CourseEntity.class,1);

        SelectedCourseEntity sc = new SelectedCourseEntity();
        sc.setStu_id(stue);
        sc.setCourse_id(course);
        session.save(sc);

        course = (CourseEntity)session.get(CourseEntity.class,18);

        sc = new SelectedCourseEntity();
        sc.setStu_id(stue);
        sc.setCourse_id(course);
        session.save(sc);

        course = (CourseEntity)session.get(CourseEntity.class,19);

        sc = new SelectedCourseEntity();
        sc.setStu_id(stue);
        sc.setCourse_id(course);
        session.save(sc);


//
//        stue = new StudentEntity();
//        stue.setStu_num("201543100481");
//        stue.setStu_name("晨晨");
//        stue.setStu_birthplace("昆明");
//        stue.setStu_sex(SexConstant.FEMALE);
//        stue.setClassItem_id(classItemEntity);
//        session.save(stue);
//
//        sc = new SelectedCourseEntityCL();
//        sc.setStu_id(stue);
//        sc.setCourse_id(course);
//        session.save(sc);


        tx.commit();
        session.close();

    }
    @Test
    public void queryTest(){
//        from  C c  join fech c.bb b join fetch b.aa a where  a.id=1
//        String hql = "select s.stu_num,s.stu_name,c.course_num,c.course_name,sc.score from SelectedCourseEntityCL as sc left join sc.stu_id as s left join sc.course_id as c where s.id = 6 ";
//        String hql = "from SelectedCourseEntityCL sc";

//        List list = BaseDAO.executeQuery(hql);
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
//        TeacherEntity te = (TeacherEntity)session.get(TeacherEntity.class,1);

        ArrayList aList = new ArrayList();
        String hql = "select t.id from TeacherEntity t where t.staffId = '060177' ";
        int tId = ((Integer)(BaseDAO.executeQuery(hql)).get(0));
        TeacherEntity te = (TeacherEntity)session.get(TeacherEntity.class,tId);
        ClassItemEntity classItem = (ClassItemEntity)session.get(ClassItemEntity.class,te.getClassItem_id().getId());
        te.setClassItem_id(classItem);

        hql = "select s.id from StudentEntity s where s.classItem_id = ?";
        List list = BaseDAO.executeQuery(hql, te.getClassItem_id());
        StudentEntity stu = (StudentEntity)session.get(StudentEntity.class,(Integer)list.get(0));

        System.out.println(stu.getStu_name());
//        for (SelectedCourseEntityCL sc : stu.getSelectedCourse_id()){
//            System.out.println(sc.getCourse_id().getCourse_name()+",成绩:"+sc.get());
//        }
        tx.commit();
        session.close();
    }
    @Test
    public void tt(){
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
        StudentEntity stu = new StudentEntity();
        stu = (StudentEntity)BaseDAO.sessionGetQuery(StudentEntity.class, 1);
        System.out.println(stu);
        tx.commit();
        session.close();
    }
}
