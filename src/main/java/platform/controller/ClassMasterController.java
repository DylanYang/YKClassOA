package platform.controller;

import com.yk.model.AccountEntity;
import com.yk.model.ClassItemEntity;
import com.yk.model.StudentEntity;
import com.yk.model.TeacherEntity;
import com.yk.model.cl.*;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dylanyang on 11/20/15.
 */
@Controller
//@RequestMapping("/")
public class ClassMasterController {
    Session session = null;
    Transaction tx = null;
    StudentEntityCL scl = null;
    int pageSize = 5;
//    @RequestMapping(value = "clmStuInfoPage",method = RequestMethod.GET)
//    public String clmStuInfo(ModelMap map,HttpServletRequest request,HttpServletResponse response){
//        return "clmstuInfo";
//    }
//    @RequestMapping(value = "clmStuInfo",method = RequestMethod.GET)
//    public String clmStuInfo(ModelMap map,@RequestParam String str,HttpServletRequest request,HttpServletResponse response){
//        return "";
//    }
    @RequestMapping(value = "stuGradeInfoPaging",method = RequestMethod.GET)
    public String stuGradeInfoPaging(ModelMap map,@RequestParam int pageNow,@RequestParam String staffId,HttpServletRequest request,HttpServletResponse response){
        ArrayList arrayList = null;
        scl = new StudentEntityCL();
        ArrayList aList = scl.getAllStuByPage(pageNow*pageSize, pageSize,staffId);
        map.addAttribute("allStuInfo",aList);
        map.addAttribute("pageNow",pageNow);
        return "clmstuInfo";
    }
    @RequestMapping(value = "stuAddGradePage",method = RequestMethod.GET)
    public String clmStuAddGradePage(ModelMap map,HttpServletRequest request,HttpServletResponse response){return "clmaddstugrade";}
//    @RequestParam int CS001_course_id,@RequestParam String CS001_ord_score,@RequestParam int CS001_end_score,@RequestParam int CS001_final_result,
//    @RequestParam int CS002_course_id,@RequestParam int CS002_ord_score,@RequestParam int CS002_end_score,@RequestParam int CS002_final_result,
//    @RequestParam int CS003_course_id,@RequestParam int CS003_ord_score,@RequestParam int CS003_end_score,@RequestParam int CS003_final_result,
//    @RequestParam int CS004_course_id,@RequestParam int CS004_ord_score,@RequestParam int CS004_end_score,@RequestParam int CS004_final_result,
//    @RequestParam int CS005_course_id,@RequestParam int CS005_ord_score,@RequestParam int CS005_end_score,@RequestParam int CS005_final_result,
//    @RequestParam int CS006_course_id,@RequestParam int CS006_ord_score,@RequestParam int CS006_end_score,@RequestParam int CS006_final_result,
//    @RequestParam int CS007_course_id,@RequestParam int CS007_ord_score,@RequestParam int CS007_end_score,@RequestParam int CS007_final_result,
    @RequestMapping(value = "stuAddGrade",method = RequestMethod.GET)
    public String clmStuAddGrade(ModelMap map,@RequestParam int pageNow,@RequestParam String staffId,@RequestParam String stuNum,
                                 @RequestParam int CS001_course_id,@RequestParam String CS001_final_result,
                                 @RequestParam int CS002_course_id,@RequestParam String CS002_final_result,
                                 @RequestParam int CS003_course_id,@RequestParam String CS003_final_result,
                                 @RequestParam int CS004_course_id,@RequestParam String CS004_final_result,
                                 @RequestParam int CS005_course_id,@RequestParam String CS005_final_result,
                                 @RequestParam int CS006_course_id,@RequestParam String CS006_final_result,
                                 @RequestParam int CS007_course_id,@RequestParam String CS007_final_result,
                                 HttpServletRequest request,HttpServletResponse response){

        HashMap gradeMap = new HashMap();
        gradeMap.put("CS001_course_id",CS001_course_id);
        gradeMap.put("CS001_final_result",CS001_final_result);

        gradeMap.put("CS002_course_id",CS002_course_id);
        gradeMap.put("CS002_final_result",CS002_final_result);

        gradeMap.put("CS003_course_id",CS003_course_id);
        gradeMap.put("CS003_final_result",CS003_final_result);

        gradeMap.put("CS004_course_id",CS004_course_id);
        gradeMap.put("CS004_final_result",CS004_final_result);

        gradeMap.put("CS005_course_id",CS005_course_id);
        gradeMap.put("CS005_final_result",CS005_final_result);

        gradeMap.put("CS006_course_id",CS006_course_id);
        gradeMap.put("CS006_final_result",CS006_final_result);

        gradeMap.put("CS007_course_id",CS007_course_id);
        gradeMap.put("CS007_final_result",CS007_final_result);

        SelectedCourseEntityCL sCL = new SelectedCourseEntityCL();
        sCL.addStuGrade(gradeMap,stuNum);

        this.stuGradeInfoPaging(map,0,staffId,request,response);
        return "clmstuInfo";
    }
    @RequestMapping(value = "stuAddStuPage",method = RequestMethod.GET)
    public String clmStuAddStuPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){return "clmaddstuadd";}
    @RequestMapping(value = "stuAddStu",method = RequestMethod.GET)
    public String clmStuAddStuPage(ModelMap map,@RequestParam String stu_no,@RequestParam String stu_name,
                                                @RequestParam long stu_tel,@RequestParam int classItemId,
                                   HttpServletRequest request,HttpServletResponse response){
        StudentEntityCL stuCL = new StudentEntityCL();
        ArrayList<StudentEntity> stuList = new ArrayList<StudentEntity>();
        StudentEntity se = new StudentEntity();
        se.setStu_num(stu_no);
        se.setStu_name(stu_name);
        se.setStu_tel(stu_tel);
        stuList.add(se);
        stuCL.addStudent(stuList,classItemId);
//        map.addAttribute("stuList",stuCL.getAllStudentUserInfo());
//        return "clmstuuserinfo";
        this.clmStuShowInfoPage(map,0,request,response);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "stuShowInfo",method = RequestMethod.GET)
    public String clmStuShowInfoPage(ModelMap map,@RequestParam int pageNow,
                                     HttpServletRequest request,HttpServletResponse response){
        StudentEntityCL stuCl = new StudentEntityCL();
        ClassItemEntityCL cItemCL = new ClassItemEntityCL();
        int classItemId = (Integer)request.getSession().getAttribute("classItemId");
        String classNo = cItemCL.getClassNoByClassItemId(classItemId);
        ArrayList<StudentEntity> stuList = stuCl.getStudentOnClassNumByPage(pageNow*pageSize, pageSize, classNo);
        map.addAttribute("stuList",stuList);
        map.addAttribute("pageNow",pageNow);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "stuUpdateGradePage",method = RequestMethod.GET)
    public String stuUpdateGradePage(ModelMap map,@RequestParam int stuId,
                           HttpServletRequest request,HttpServletResponse response){
        ArrayList arrayList = null;
        scl = new StudentEntityCL();
        StudentEntity stue = (StudentEntity)scl.getStudentEntityById(StudentEntity.class, stuId);
        map.addAttribute("stuEntity",stue);
        return "clmstuupdategrade";
    }
    @RequestMapping(value = "upstushowinfo",method = RequestMethod.GET)
    public String upStuShowInfo(ModelMap map,@RequestParam String id,@RequestParam String stu_num,
                                             @RequestParam String stu_name,@RequestParam long stu_tel,
                                    HttpServletRequest request,HttpServletResponse response){
        int stId = Integer.parseInt(id);
        StudentEntityCL stuCl = new StudentEntityCL();
        ClassItemEntityCL classItemCl = new ClassItemEntityCL();
        StudentEntity se = new StudentEntity();
        se.setStu_name(stu_name);
        se.setStu_num(stu_num);
        se.setStu_tel(stu_tel);
        int classItemId =  (Integer)request.getSession().getAttribute("classItemId");
        se.setClassItem_id((ClassItemEntity)classItemCl.getClassItemEntityByClassItemId(ClassItemEntity.class,classItemId));
        stuCl.upStudent(se,stId);
        map.addAttribute("stuEntity",se);

        this.clmStuShowInfoPage(map,0,request,response);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "upstushowinfopage",method = RequestMethod.GET)
    public String upStuShowInfoPage(ModelMap map,@RequestParam String stuId,
                                    HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(stuId);
        StudentEntityCL stuCl = new StudentEntityCL();
        StudentEntity se = (StudentEntity)stuCl.getStudentEntityById(StudentEntity.class, id);
        map.addAttribute("stuEntity",se);
        return "clmstushowinfoupdate";
    }
    @RequestMapping(value = "delStudent",method = RequestMethod.GET)
    public String delStudent(ModelMap map,@RequestParam int stuId,HttpServletRequest request,HttpServletResponse response){
        StudentEntityCL stuCL = new StudentEntityCL();
        stuCL.delStudentById(stuId);

        this.clmStuShowInfoPage(map,0,request,response);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "stuShowInfoUpLoadPage",method = RequestMethod.GET)
    public String stuShowInfoUpLoadPage(ModelMap map,HttpServletRequest req,HttpServletResponse res){return "clmstushowinfoupload";}
    @RequestMapping(value = "stuShowInfoExcelIn",method = RequestMethod.POST)
    public String stuShowInfoExcelIn(ModelMap map,@RequestParam(value = "filePath",required = false)MultipartFile file,
                                     @RequestParam int classItemId,
                                     HttpServletRequest request,HttpServletResponse response){
        StudentEntityCL stuCL = new StudentEntityCL();
        String path = request.getSession().getServletContext().getRealPath("/")+"uploads/";
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<StudentEntity> stuList = stuCL.stuShowInfoExcelBulkImportToStuEntity(path + fileName);
        stuCL.addStudent(stuList,classItemId);
        this.clmStuShowInfoPage(map,0,request,response);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "stuGradeUploadPage",method = RequestMethod.GET)
    public String stuGradeUploadPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){return "clmstugradeupload";}
    @RequestMapping(value = "stuGradeExcelIn",method = RequestMethod.POST)
    public String stuGradeExcelIn(ModelMap map,@RequestParam(value = "filePath",required = false)MultipartFile file,
                                     @RequestParam int classItemId,
                                     HttpServletRequest request,HttpServletResponse response){
        SelectedCourseEntityCL scL = new SelectedCourseEntityCL();
        String path = request.getSession().getServletContext().getRealPath("/")+"uploads/";
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path,fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<HashMap> gradeList = scL.extractStuGradeFromExcel(path + fileName);
        scL.stuGradeExcelBulkImportToSCEntity(gradeList);
        String staffId = (String)request.getSession().getAttribute("staffId");
        this.stuGradeInfoPaging(map,0,staffId,request,response);
        return "clmstuInfo";
    }
    @RequestMapping(value = "stuDelAllPage",method = RequestMethod.GET)
    public String stuDelAllPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){return "clmstudelallpage";}
    @RequestMapping(value = "delAllStudent",method = RequestMethod.GET)
    public String delAllStudent(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        ClassItemEntityCL cItemCL = new ClassItemEntityCL();
        StudentEntityCL stuCL = new StudentEntityCL();
        int classItemId = (Integer)request.getSession().getAttribute("classItemId");
        String classNo = cItemCL.getClassNoByClassItemId(classItemId);
        ArrayList stuIdList = stuCL.getStudentIdList(classNo);
        stuCL.delAllStudent(stuIdList);
        this.clmStuShowInfoPage(map,0,request,response);
        return "clmstushowInfo";
    }
    @RequestMapping(value = "clmShowPersonalInfoPage",method = RequestMethod.GET)
    public String showPersonalInfoPage(ModelMap map,@RequestParam String staffId,
                                       HttpServletRequest request,HttpServletResponse response){
        AccountEntityCL accountCL = new AccountEntityCL();
        int accId = accountCL.getAccountIdByUserName(staffId);
        AccountEntity ae = (AccountEntity)accountCL.getAccountById(accId).get(0);
        map.addAttribute("account",ae);
        return "clmshowpersonalinfo";
    }
    @RequestMapping(value = "clmUpdatePersonalInfoPage",method = RequestMethod.GET)
    public String updatePersonalInfo(ModelMap map,@RequestParam String accountId,
                             HttpServletRequest req,HttpServletResponse res){
        int id = Integer.parseInt(accountId);
        AccountEntityCL acl = new AccountEntityCL();
        ArrayList accList = (ArrayList)acl.getAccountById(id);
        AccountEntity ae  = new AccountEntity();
        ae = (AccountEntity)accList.get(0);
        map.addAttribute("account",ae);
        return "clmupdatepersonalinfopage";
    }
    @RequestMapping(value = "clmUpdatePersonalInfo",method = RequestMethod.GET)
    public String clmUpdatePersonalInfo(ModelMap map,@RequestParam int id,@RequestParam String username,@RequestParam String password,@RequestParam String name_ch,
                             @RequestParam String email,@RequestParam int role,
                             @RequestParam String id_card, @RequestParam int dept,
                             HttpServletRequest request,HttpServletResponse response){
        AccountEntityCL acl = new AccountEntityCL();
        ArrayList<AccountEntity> accList = new ArrayList<AccountEntity>();
        AccountEntity ae = new AccountEntity();
        ae.setId(id);
        ae.setUsername(username);
        ae.setPassword(password);
        ae.setName_ch(name_ch);
        ae.setEmail(email);
        ae.setRole(role);
        ae.setId_card(id_card);
        ae.setDept(dept);
        accList.add(ae);
        ArrayList<TeacherEntity> teList = new ArrayList<TeacherEntity>();
        TeacherEntity teacher = new TeacherEntity();
        teacher.setId(id);
        teacher.setStaffId(username);
        teList.add(teacher);
        acl.updateAccount(accList,teList);
        this.showPersonalInfoPage(map,username,request,response);
        return "clmshowpersonalinfo";
    }
    @RequestMapping(value = "clmquit",method = RequestMethod.GET)
    public String clmQuit(ModelMap map, HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        return "bklogin";
    }
}
