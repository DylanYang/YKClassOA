package com.yk.model.cl;

import com.yk.model.CourseEntity;
import com.yk.model.SelectedCourseEntity;
import com.yk.model.StudentEntity;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import platform.DAO.utils.BaseDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by dylanyang on 12/24/15.
 */
public class SelectedCourseEntityCL {
    /**
     * dylan
     *
     * @param clazz
     * @param id
     * @return obj
     */
    public Object getSelectedCourseEntity(Class clazz,int id){
        return  BaseDAO.getObjClassBySessionGetQuery(clazz,id);
    }

    /**
     * dylan
     * @param sce
     */
    public void saveStuGrade(SelectedCourseEntity sce){
        BaseDAO.add(sce);
    }

    /**
     * dylan
     * @param gradeMap
     * @param stuNum
     */
    public void addStuGrade(HashMap gradeMap,String stuNum){
        SelectedCourseEntityCL scCL = new SelectedCourseEntityCL();
        StudentEntityCL stuCL = new StudentEntityCL();
        int stuId = stuCL.getStuIdByStuNum(stuNum);
        StudentEntity stu = (StudentEntity)stuCL.getStudentEntityById(StudentEntity.class, stuCL.getStuIdByStuNum(stuNum));

        ArrayList alist = new ArrayList();

        CourseEntityCL cl = new CourseEntityCL();
        CourseEntity ce = null;

        SelectedCourseEntity sc001 = new SelectedCourseEntity();
//        sc001.setOrd_score((Integer)gradeMap.get("CS001_ord_score"));
//        sc001.setEnd_score((Integer)gradeMap.get("CS001_end_score"));
        sc001.setFinal_result((String)gradeMap.get("CS001_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS001_course_id"));
        sc001.setCourse_id(ce);
        sc001.setStu_id(stu);
        scCL.saveStuGrade(sc001);

        SelectedCourseEntity sc002 = new SelectedCourseEntity();
//        sc002.setOrd_score((Integer) gradeMap.get("CS002_ord_score"));
//        sc002.setEnd_score((Integer) gradeMap.get("CS002_end_score"));
        sc002.setFinal_result((String)gradeMap.get("CS002_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS002_course_id"));
        sc002.setCourse_id(ce);
        sc002.setStu_id(stu);
        scCL.saveStuGrade(sc002);

        SelectedCourseEntity sc003 = new SelectedCourseEntity();
//        sc003.setOrd_score((Integer)gradeMap.get("CS003_ord_score"));
//        sc003.setEnd_score((Integer) gradeMap.get("CS003_end_score"));
        sc003.setFinal_result((String)gradeMap.get("CS003_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS003_course_id"));
        sc003.setCourse_id(ce);
        sc003.setStu_id(stu);
        scCL.saveStuGrade(sc003);

        SelectedCourseEntity sc004 = new SelectedCourseEntity();
//        sc004.setOrd_score((Integer) gradeMap.get("CS004_ord_score"));
//        sc004.setEnd_score((Integer) gradeMap.get("CS004_end_score"));
        sc004.setFinal_result((String)gradeMap.get("CS004_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS004_course_id"));
        sc004.setCourse_id(ce);
        sc004.setStu_id(stu);
        scCL.saveStuGrade(sc004);

        SelectedCourseEntity sc005 = new SelectedCourseEntity();
//        sc005.setOrd_score((Integer) gradeMap.get("CS005_ord_score"));
//        sc005.setEnd_score((Integer) gradeMap.get("CS005_end_score"));
        sc005.setFinal_result((String)gradeMap.get("CS005_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS005_course_id"));
        sc005.setCourse_id(ce);
        sc005.setStu_id(stu);
        scCL.saveStuGrade(sc005);

        SelectedCourseEntity sc006 = new SelectedCourseEntity();
//        sc006.setOrd_score((Integer) gradeMap.get("CS006_ord_score"));
//        sc006.setEnd_score((Integer) gradeMap.get("CS006_end_score"));
        sc006.setFinal_result((String)gradeMap.get("CS006_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer)gradeMap.get("CS006_course_id"));
        sc006.setCourse_id(ce);
        sc006.setStu_id(stu);
        scCL.saveStuGrade(sc006);

        SelectedCourseEntity sc007 = new SelectedCourseEntity();
//        sc007.setOrd_score((Integer) gradeMap.get("CS007_ord_score"));
//        sc007.setEnd_score((Integer) gradeMap.get("CS007_end_score"));
        sc007.setFinal_result((String) gradeMap.get("CS007_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS007_course_id"));
        sc007.setCourse_id(ce);
        sc007.setStu_id(stu);
        scCL.saveStuGrade(sc007);

        SelectedCourseEntity sc008 = new SelectedCourseEntity();
//        sc008.setOrd_score((Integer) gradeMap.get("CS008_ord_score"));
//        sc008.setEnd_score((Integer) gradeMap.get("CS008_end_score"));
        sc008.setFinal_result((String) gradeMap.get("CS008_final_result"));
        ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS008_course_id"));
        sc008.setCourse_id(ce);
        sc008.setStu_id(stu);
        scCL.saveStuGrade(sc008);

        HashSet<SelectedCourseEntity> scSet = new HashSet<SelectedCourseEntity>();
        scSet.add(sc001);
        scSet.add(sc002);
        scSet.add(sc003);
        scSet.add(sc004);
        scSet.add(sc005);
        scSet.add(sc006);
        scSet.add(sc007);
        scSet.add(sc008);

        stu.setSelectedCourse_id(scSet);

        stuCL.addSelectedCourseGrade(stu,scSet);
    }

    /**
     * dylan
     * @param gradeList
     */
    public void stuGradeExcelBulkImportToSCEntity(ArrayList<HashMap> gradeList){
        SelectedCourseEntityCL scCL = new SelectedCourseEntityCL();
        StudentEntityCL stuCL = new StudentEntityCL();


        ArrayList alist = new ArrayList();

        CourseEntityCL cl = new CourseEntityCL();
        CourseEntity ce = null;

        SelectedCourseEntity sc001 = null;
        SelectedCourseEntity sc002 = null;
        SelectedCourseEntity sc003 = null;
        SelectedCourseEntity sc004 = null;
        SelectedCourseEntity sc005 = null;
        SelectedCourseEntity sc006 = null;
        SelectedCourseEntity sc007 = null;
        SelectedCourseEntity sc008 = null;

        for(int i=0;i<gradeList.size();i++) {
            HashMap gradeMap = gradeList.get(i);
            String stuNum = (String)gradeMap.get("stuNum");
            int stuId = stuCL.getStuIdByStuNum(stuNum);
            StudentEntity stu = (StudentEntity)stuCL.getStudentEntityById(StudentEntity.class, stuCL.getStuIdByStuNum(stuNum));

            sc001 = new SelectedCourseEntity();
//            sc001.setOrd_score((Double)(gradeMap.get("CS001_ord_score")));
//            sc001.setEnd_score((Double) gradeMap.get("CS001_end_score"));
            sc001.setFinal_result((String) gradeMap.get("CS001_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS001_course_id"));
            sc001.setCourse_id(ce);
            sc001.setStu_id(stu);
            scCL.saveStuGrade(sc001);

            sc002 = new SelectedCourseEntity();
//            sc002.setOrd_score((Double) gradeMap.get("CS002_ord_score"));
//            sc002.setEnd_score((Double) gradeMap.get("CS002_end_score"));
            sc002.setFinal_result((String) gradeMap.get("CS002_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS002_course_id"));
            sc002.setCourse_id(ce);
            sc002.setStu_id(stu);
            scCL.saveStuGrade(sc002);

            sc003 = new SelectedCourseEntity();
//            sc003.setOrd_score((Double) gradeMap.get("CS003_ord_score"));
//            sc003.setEnd_score((Double) gradeMap.get("CS003_end_score"));
            sc003.setFinal_result((String) gradeMap.get("CS003_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS003_course_id"));
            sc003.setCourse_id(ce);
            sc003.setStu_id(stu);
            scCL.saveStuGrade(sc003);

            sc004 = new SelectedCourseEntity();
//            sc004.setOrd_score((Double) gradeMap.get("CS004_ord_score"));
//            sc004.setEnd_score((Double) gradeMap.get("CS004_end_score"));
            sc004.setFinal_result((String) gradeMap.get("CS004_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS004_course_id"));
            sc004.setCourse_id(ce);
            sc004.setStu_id(stu);
            scCL.saveStuGrade(sc004);

            sc005 = new SelectedCourseEntity();
//            sc005.setOrd_score((Double) gradeMap.get("CS005_ord_score"));
//            sc005.setEnd_score((Double) gradeMap.get("CS005_end_score"));
            sc005.setFinal_result((String) gradeMap.get("CS005_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS005_course_id"));
            sc005.setCourse_id(ce);
            sc005.setStu_id(stu);
            scCL.saveStuGrade(sc005);

            sc006 = new SelectedCourseEntity();
//            sc006.setOrd_score((Double) gradeMap.get("CS006_ord_score"));
//            sc006.setEnd_score((Double) gradeMap.get("CS006_end_score"));
            sc006.setFinal_result((String) gradeMap.get("CS006_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS006_course_id"));
            sc006.setCourse_id(ce);
            sc006.setStu_id(stu);
            scCL.saveStuGrade(sc006);

            sc007 = new SelectedCourseEntity();
//            sc007.setOrd_score((Double) gradeMap.get("CS007_ord_score"));
//            sc007.setEnd_score((Double) gradeMap.get("CS007_end_score"));
            sc007.setFinal_result((String) gradeMap.get("CS007_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS007_course_id"));
            sc007.setCourse_id(ce);
            sc007.setStu_id(stu);
            scCL.saveStuGrade(sc007);

            sc008 = new SelectedCourseEntity();
//            sc008.setOrd_score((Double) gradeMap.get("CS008_ord_score"));
//            sc008.setEnd_score((Double) gradeMap.get("CS008_end_score"));
            sc008.setFinal_result((String) gradeMap.get("CS008_final_result"));
            ce = (CourseEntity) cl.getCourseEntity(CourseEntity.class, (Integer) gradeMap.get("CS008_course_id"));
            sc008.setCourse_id(ce);
            sc008.setStu_id(stu);
            scCL.saveStuGrade(sc008);
        }

        HashSet<SelectedCourseEntity> scSet = new HashSet<SelectedCourseEntity>();
        scSet.add(sc001);
        scSet.add(sc002);
        scSet.add(sc003);
        scSet.add(sc004);
        scSet.add(sc005);
        scSet.add(sc006);
        scSet.add(sc007);
        scSet.add(sc008);

        StudentEntity stu = new StudentEntity();
        stu.setSelectedCourse_id(scSet);

        stuCL.addSelectedCourseGrade(stu,scSet);
    }
    /**
     * dylan
     * @param filePath
     * @return gradeList
     */
    public ArrayList<HashMap> extractStuGradeFromExcel(String filePath){
        ArrayList<HashMap> gradeList = new ArrayList<HashMap>();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int colums = sheet.getColumns();
            CourseEntityCL ccl = new CourseEntityCL();
            ArrayList<CourseEntity> cList = ccl.getAllCourses();


            for(int i = 2;i<rows;i++){
                StudentEntity stue = new StudentEntity();
                Cell cell = sheet.getCell(0,i);

                HashMap gradeMap = new HashMap();

                gradeMap.put("stuNum",String.valueOf(sheet.getCell(0,i).getContents()));

                gradeMap.put("CS001_course_id",cList.get(0).getId());
//                gradeMap.put("CS001_ord_score",Double.valueOf(sheet.getCell(2,i).getContents()));
//                gradeMap.put("CS001_end_score",Double.valueOf(sheet.getCell(3, i).getContents()));
                gradeMap.put("CS001_final_result",sheet.getCell(2, i).getContents());

                gradeMap.put("CS002_course_id",cList.get(1).getId());
//                gradeMap.put("CS002_ord_score",Double.valueOf(sheet.getCell(5, i).getContents()));
//                gradeMap.put("CS002_end_score",Double.valueOf(sheet.getCell(6, i).getContents()));
                gradeMap.put("CS002_final_result",sheet.getCell(3, i).getContents());

                gradeMap.put("CS003_course_id",cList.get(2).getId());
//                gradeMap.put("CS003_ord_score",Double.valueOf(sheet.getCell(8, i).getContents()));
//                gradeMap.put("CS003_end_score",Double.valueOf(sheet.getCell(9, i).getContents()));
                gradeMap.put("CS003_final_result",sheet.getCell(4, i).getContents());

                gradeMap.put("CS004_course_id",cList.get(3).getId());
//                gradeMap.put("CS004_ord_score",Double.valueOf(sheet.getCell(11, i).getContents()));
//                gradeMap.put("CS004_end_score",Double.valueOf(sheet.getCell(12, i).getContents()));
                gradeMap.put("CS004_final_result",sheet.getCell(5, i).getContents());

                gradeMap.put("CS005_course_id",cList.get(4).getId());
//                gradeMap.put("CS005_ord_score",Double.valueOf(sheet.getCell(14, i).getContents()));
//                gradeMap.put("CS005_end_score",Double.valueOf(sheet.getCell(15, i).getContents()));
                gradeMap.put("CS005_final_result",sheet.getCell(6, i).getContents());

                gradeMap.put("CS006_course_id",cList.get(5).getId());
//                gradeMap.put("CS006_ord_score",Double.valueOf(sheet.getCell(17, i).getContents()));
//                gradeMap.put("CS006_end_score",Double.valueOf(sheet.getCell(18, i).getContents()));
                gradeMap.put("CS006_final_result",sheet.getCell(7, i).getContents());

                gradeMap.put("CS007_course_id",cList.get(6).getId());
//                gradeMap.put("CS007_ord_score",Double.valueOf(sheet.getCell(20, i).getContents()));
//                gradeMap.put("CS007_end_score",Double.valueOf(sheet.getCell(21, i).getContents()));
                gradeMap.put("CS007_final_result",sheet.getCell(8, i).getContents());

                gradeMap.put("CS008_course_id",cList.get(7).getId());
//                gradeMap.put("CS008_ord_score",Double.valueOf(sheet.getCell(20, i).getContents()));
//                gradeMap.put("CS008_end_score",Double.valueOf(sheet.getCell(21, i).getContents()));
                gradeMap.put("CS008_final_result",sheet.getCell(9, i).getContents());

                gradeList.add(gradeMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            book.close();
        }
        return gradeList;
    }
}

