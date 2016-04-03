package com.yk.model.cl;

import com.yk.model.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import platform.DAO.utils.BaseDAO;

import javax.servlet.http.HttpServlet;
import java.awt.print.Book;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dylanyang on 12/16/15.
 */
public class StudentEntityCL extends HttpServlet{
    /**
     *
     * @param stuNum
     * @param passwd
     * @return
     */
    public boolean checkStudentExist(String stuNum,String passwd){
        String hql = "select s.stu_num from StudentEntity s where s.stu_num = ?";
        ArrayList stuList = BaseDAO.executeQuery(hql, stuNum);
        if (stuList.size()>0){
            if (stuNum.equals(passwd)) {
                return true;
            }
        }
        return false;
    }
    /**
     * dylan
     * @param classNo
     * @return
     */
    public int getStuRowCount(String classNo){
        String hql = "select count(*) from StudentEntity s where s.stu_class_num = ?";
        return BaseDAO.getRowCount(hql,classNo);
    }

    /**
     * dylan
     * @param pageSize
     * @param classNo
     * @return pageCount
     */
    public int getStuInfoPageCount(int pageSize,String classNo){
        int pageCount = 0;
        int rowCount = this.getStuRowCount(classNo);
        if (rowCount % pageSize == 0){
            pageCount = rowCount/pageSize;
        }else{
            pageCount = rowCount/pageSize+1;
        }
        return pageCount;
    }
    /**
     * dylan
     * @param pageNow
     * @param pageSize
     * @return alist
     */
    public ArrayList getAllStuByPage(int pageNow,int pageSize,String staffId){
        ArrayList aList = new ArrayList();
        String hql = "select t.id from TeacherEntity t where t.staffId = ? ";
        int tId = ((Integer)(BaseDAO.executeQuery(hql,staffId)).get(0));
        TeacherEntity te = (TeacherEntity)BaseDAO.getObjClassBySessionGetQuery(TeacherEntity.class,tId);
        ClassItemEntity classItem = (ClassItemEntity)(BaseDAO.getObjClassBySessionGetQuery(ClassItemEntity.class,te.getClassItem_id().getId()));
        te.setClassItem_id(classItem);

        hql = "select s.id from StudentEntity s where s.classItem_id = ?";
        List list = BaseDAO.executeQuery(hql, te.getClassItem_id());
        StudentEntity stu = null;
        for (int i = 0;i < list.size();i++){
            stu = (StudentEntity)BaseDAO.sessionGetQuery(StudentEntity.class,(Integer)list.get(i));
            aList.add(stu);
        }
//        System.out.println(stu.getStu_name());
//        for (SelectedCourseEntityCL sc : stu.getSelectedCourse_id()){
//            System.out.println(sc.getCourse_id().getCourse_name()+",成绩:"+sc.getScore());
//        }
        return aList;
    }
    /**
     * dylan
     * @param stu_num
     * @return int
     */
    public int getStuIdByStuNum(String stu_num){
        String hql="select s.id from StudentEntity s where s.stu_num = ?";
        List list = BaseDAO.executeQuery(hql, stu_num);
        return  (Integer)(BaseDAO.executeQuery(hql, stu_num).get(0));
    }
    /**
     * dylan
     * @param clazz
     * @param id
     * @return obj
     */
    public Object getStudentEntityById(Class clazz,int id){
        return BaseDAO.getObjClassBySessionGetQuery(clazz,id);
    }

    /**
     * dylan
     * @param stuList
     * @param classItemId
     */
    public void addStudent(ArrayList<StudentEntity> stuList,int classItemId){
        ClassItemEntityCL ccl = new ClassItemEntityCL();
        for (StudentEntity se : stuList){
            se.setStu_num(se.getStu_num());
            se.setStu_name(se.getStu_name());
            ClassItemEntity classItemEntity = (ClassItemEntity)(ccl.getClassItemEntityByClassItemId(ClassItemEntity.class, classItemId));
            se.setClassItem_id(classItemEntity);
            se.setStu_class_num(classItemEntity.getClassNo());
            BaseDAO.add(se);
        }
    }

    /**
     * dylan
     * @return stuList
     */
    public ArrayList<StudentEntity> getAllStudentUserInfo(){
        String hql = "from StudentEntity";
        return BaseDAO.executeQuery(hql);
    }

    /**
     * dylan
     * @param pageNow
     * @param pageSize
     * @return list
     */
    public ArrayList<StudentEntity> getAllStudentByPage(int pageNow,int pageSize){
        String hql = "from StudentEntity s";
        ArrayList list = BaseDAO.pagingQuery(hql, pageNow, pageSize);
        return list;
    }

    /**
     * dylan
     * @param pageNow
     * @param pageSize
     * @param classNo
     * @return list
     */
    public ArrayList<StudentEntity> getStudentOnClassNumByPage(int pageNow,int pageSize,String classNo){
        String hql = "from StudentEntity s where stu_class_num = ?";
        ArrayList list = BaseDAO.pagingQuery(hql, pageNow, pageSize,classNo);
        return list;
    }

    /**
     * dylan
     * @param stuNo
     * @return StudentEntity
     */
    public StudentEntity getStudentEntityByStuNo(String stuNo){
        String hql = "from StudentEntity s where stu_num = ?";
        StudentEntity se = (StudentEntity)BaseDAO.executeQuery(hql,stuNo).get(0);
        return se;
    }
    /**
     * dylan
     * @param se
     */
    public void upStudent(StudentEntity se,int stuId){
        String hql = "update StudentEntity s set s.stu_num=?,s.stu_name=?,s.stu_tel=? where id=?";
        int ret = BaseDAO.executeUpdate(hql,se.getStu_num(),se.getStu_name(),se.getStu_tel(),stuId);
        System.out.println("query influence: " + ret);
    }

    /**
     * dylan
     * @param stuId
     */
    public void delStudentById(int stuId){
        BaseDAO.dellObjByCascade(StudentEntity.class,stuId);
    }

    /**
     * dylan
     * @param stue
     * @param sc
     */
    public void addSelectedCourseGrade(StudentEntity stue,HashSet<SelectedCourseEntity> sc){
        stue.setSelectedCourse_id(sc);
    }

    /**
     * dylan
     * @param filePath
     * @return stuList
     */
    public ArrayList<StudentEntity> stuShowInfoExcelBulkImportToStuEntity(String filePath){
        ArrayList<StudentEntity> stuList = new ArrayList<StudentEntity>();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int colums = sheet.getColumns();
            for(int i = 1;i<rows;i++){
                StudentEntity stue = new StudentEntity();
                Cell cell = sheet.getCell(0,i);
                stue.setStu_num(sheet.getCell(0,i).getContents());
                stue.setStu_name(sheet.getCell(1,i).getContents());
                stue.setStu_tel(Long.valueOf(sheet.getCell(2,i).getContents()));
                stuList.add(stue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            book.close();
        }
        return stuList;
    }

    /**
     * dylan
     * @param classNo
     * @return stuIdList
     */
    public ArrayList<Integer> getStudentIdList(String classNo){
        String hql = "select s.id from StudentEntity s where s.stu_class_num = ?";
        ArrayList<Integer> stuIdList = new ArrayList<Integer>();
        try {
            stuIdList = BaseDAO.executeQuery(hql,classNo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return stuIdList;
    }

    /**
     * dylan
     * @param stuIdList
     */
    public void delAllStudent(ArrayList<Integer> stuIdList){
        for (int id:stuIdList){
            try {
                BaseDAO.dellObjByCascade(StudentEntity.class,id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
