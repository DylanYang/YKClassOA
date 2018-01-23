package com.yk.model.cl;

import com.yk.model.CourseEntity;
import platform.DAO.utils.BaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanyang on 12/20/15.
 */
public class  CourseEntityCL {
    /**
     * dylan
     * @return arraylist
     */
    public ArrayList getAllCourses(){
        String hql = "from CourseEntity order by id";
        ArrayList arrayList = BaseDAO.executeQuery(hql);
        return arrayList;
    }
    /**
     * dylan
     * @param pageNow
     * @param pageSize
     * @return list
     */
    public ArrayList getAllCourseByPage(int pageNow,int pageSize){
        String hql = "from CourseEntity order by id";
        ArrayList list = BaseDAO.pagingQuery(hql, pageNow, pageSize);
        return list;
    }
    /**
     * dylan
     * @param id
     * @return list
     */
    public List<CourseEntity> getCourseById(int id){
        String hql = "from CourseEntity c where c.id = ?";
        List list = BaseDAO.executeQuery(hql,id);
        return list;
    }
    /**
     * dylan
     * @param cList
     */
    public void addCourse(ArrayList<CourseEntity> cList){
        for (CourseEntity ce : cList){
            ce.setCourse_num(ce.getCourse_num());
            ce.setCourse_name(ce.getCourse_name());
            ce.setCredit(ce.getCredit());
            BaseDAO.add(ce);
        }
    }
    /**
     * dylan
     * @param course
     */
    public void updateCourse(ArrayList<CourseEntity> course){
        String hql = "update CourseEntity c set c.course_num=?,c.course_name=?,c.credit=? where c.id=?";
        int ret = BaseDAO.executeUpdate(hql,course.get(0).getCourse_num(),course.get(0).getCourse_name(),course.get(0).getCredit(),course.get(0).getId());
        System.out.println("query influence: " + ret);
    }
    /**
     * dylan
     * @param courseId
     */
    public void delCourse(int courseId){
//        String hql = "delete from CourseEntity c where c.id = ?";
        BaseDAO.dellObjByCascade(CourseEntity.class,courseId);
//        BaseDAO.executeQuery(hql,courseId);
    }
    /**
     * dylan
     * @return rowCount
     */
    public int getCourseRowCount(){
        String hql = "select count(*) from CourseEntity";
        return BaseDAO.getRowCount(hql);
    }
    /**
     * dylan
     * @param pageSize
     * @return pageCount
     */
    public int getCoursePageCount(int pageSize){
        int pageCount = 0;
        int rowCount = this.getCourseRowCount();
        if (rowCount % pageSize == 0){
            pageCount = rowCount/pageSize;
        }else{
            pageCount = rowCount/pageSize+1;
        }
        return pageCount;
    }

    /**
     * dylan
     * @param clazz
     * @param id
     * @return obj
     */
    public Object getCourseEntity(Class clazz,int id){
        return  BaseDAO.getObjClassBySessionGetQuery(clazz,id);
    }

    /**
     * dylan
     * @return subject count
     */
    public int getSubjectTotalCount(){
        String hql = "select count(*) from CourseEntity";
        return BaseDAO.getRowCount(hql);
    }
}
