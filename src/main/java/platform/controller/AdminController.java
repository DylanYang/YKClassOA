package platform.controller;

import com.yk.common.AccountRoleConstant;
import com.yk.model.AccountEntity;
import com.yk.model.ClassItemEntity;
import com.yk.model.CourseEntity;
import com.yk.model.TeacherEntity;
import com.yk.model.cl.AccountEntityCL;
import com.yk.model.cl.ClassItemEntityCL;
import com.yk.model.cl.CourseEntityCL;
import com.yk.model.cl.StudentEntityCL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import platform.DAO.utils.BaseDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dylanyang on 11/25/15.
 */
@Controller
@RequestMapping("/")
public class AdminController {
    private int pageSize = 5;
    AccountEntityCL acl = null;
    CourseEntityCL ccl = null;
    @RequestMapping(value = "admin",method = RequestMethod.GET)
    public String adminLogin(){
        return "bklogin";
    }
    @RequestMapping(value = "checkuser",method = RequestMethod.POST)
    public String checkUser(ModelMap map,@RequestParam String username,@RequestParam String passwd,@RequestParam String acctype,
                            HttpServletRequest req,HttpServletResponse res){
        acl = new AccountEntityCL();
        int accountType = Integer.parseInt(acctype);
        int loginRole = acl.checkUserLoginRole(accountType,username);
        //check account staffId & passwd correct and loginType matching in DB
        if (acl.checkUser(username,passwd)) {
            if (loginRole != -1 && acl.checkUserLoginRole(accountType,username) == AccountRoleConstant.ADMINISTRATOR) {
                return "admin";
            }else if (loginRole != -1 && acl.checkUserLoginRole(accountType,username) == AccountRoleConstant.CLASS_MASTER) {
                AccountEntity ae = (AccountEntity)acl.getAccountById(acl.getAccountIdByUserName(username)).get(0);
                TeacherEntity te = (TeacherEntity) BaseDAO.getObjClassBySessionGetQuery(TeacherEntity.class, ae.getTeacher_id().getId());
                ClassItemEntity cte = (ClassItemEntity)BaseDAO.getObjClassBySessionGetQuery(ClassItemEntity.class,te.getClassItem_id().getId());
                ArrayList list = new ArrayList();
                list.add(ae);
                list.add(cte);
                map.addAttribute("accInfolist", list);
                //set session manager
                req.getSession().setMaxInactiveInterval(1800);
                req.getSession().setAttribute("staffId", ae.getUsername());
                req.getSession().setAttribute("classItemId", ae.getTeacher_id().getClassItem_id().getId());
                return "classmaster";
            }
        }
        //-1 error go back login and send err msg
        if (loginRole == -1){
            map.addAttribute("logErrStr","员工号或密码或类型错误！");
        }else {
            map.addAttribute("logErrStr",null);
        }
        return "bklogin";
    }
    @RequestMapping(value = "adduserpage",method = RequestMethod.GET)
    public String addUser(ModelMap map,HttpServletRequest req,HttpServletResponse res){
        return "adadduser";
    }
    @RequestMapping(value = "adduser",method = RequestMethod.GET)
    public String addUser(ModelMap map,@RequestParam String username,@RequestParam String passwd,@RequestParam String name_ch,
                          @RequestParam String email,@RequestParam int role,
                          @RequestParam String idCard, @RequestParam int dept,@RequestParam String classNo
            ,HttpServletRequest req,HttpServletResponse res){
        acl = new AccountEntityCL();
        ArrayList<AccountEntity> account = new ArrayList<AccountEntity>();
        AccountEntity ae = new AccountEntity();
        ae.setUsername(username);
        ae.setPassword(passwd);
        ae.setName_ch(name_ch);
        ae.setEmail(email);
        ae.setRole(role);
        ae.setId_card(idCard);
        ae.setDept(dept);
        account.add(ae);
        acl.addAccount(account,classNo);
        this.accountPaging(map, 0, req, res);
        return  "aduserinfo";
    }
    @RequestMapping(value = "adaddcoursepage",method = RequestMethod.GET)
    public String addCourse(ModelMap map,HttpServletRequest request,HttpServletResponse response){return "adaddcourse";}
    @RequestMapping(value = "adaddcourse",method = RequestMethod.GET)
    public String addCourse(ModelMap map,@RequestParam String course_num,@RequestParam String course_name,@RequestParam int credit,
                            HttpServletRequest request,HttpServletResponse response){
        ccl = new CourseEntityCL();
        ArrayList<CourseEntity> cList = new ArrayList<CourseEntity>();
        CourseEntity ce = new CourseEntity();
        ce.setCourse_num(course_num);
        ce.setCourse_name(course_name);
        ce.setCredit(credit);
        cList.add(ce);
        ccl.addCourse(cList);
        this.adCoursePaging(map,0,request,response);
        return "adcourseInfo";
    }
    @RequestMapping(value = "upuserpage",method = RequestMethod.GET)
    public String updateUser(ModelMap map,@RequestParam String userId,
                             HttpServletResponse req,HttpServletResponse res){
        int id = Integer.parseInt(userId);
        acl = new AccountEntityCL();
        ArrayList accList = (ArrayList)acl.getAccountById(id);
        AccountEntity ae  = new AccountEntity();
        ae = (AccountEntity)accList.get(0);
        map.addAttribute("account",ae);
        return "adupdateuser";
    }
    @RequestMapping(value = "upcoursepage",method = RequestMethod.GET)
    public String upCourse(ModelMap map,@RequestParam String courseId,
                           HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(courseId);
        ccl = new CourseEntityCL();
        ArrayList aList = (ArrayList)ccl.getCourseById(id);
        CourseEntity ce =(CourseEntity)aList.get(0);
        map.addAttribute("courses",ce);
        return "adupcourse";
    }
    @RequestMapping(value = "adupcourse",method = RequestMethod.GET)
    public String upCourse(ModelMap map,@RequestParam String course_num,@RequestParam String course_name,@RequestParam int credit,
                           @RequestParam int id,
                           HttpServletRequest request,HttpServletResponse response){
        ccl = new CourseEntityCL();
        ArrayList<CourseEntity> cList = new ArrayList<CourseEntity>();
        CourseEntity ce = new CourseEntity();
        ce.setId(id);
        ce.setCourse_num(course_num);
        ce.setCourse_name(course_name);
        ce.setCredit(credit);
        cList.add(ce);
        ccl.updateCourse(cList);
        this.adCoursePaging(map,0,request,response);
        return "adcourseInfo";
    }
    @RequestMapping(value = "upuser",method = RequestMethod.GET)
    public String updateUser(ModelMap map,@RequestParam int id,@RequestParam String username,@RequestParam String password,@RequestParam String name_ch,
                             @RequestParam String email,@RequestParam int role,
                             @RequestParam String id_card, @RequestParam int dept,
                             HttpServletRequest req,HttpServletResponse res){
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
        this.accountPaging(map,0,req,res);
        return "aduserinfo";
    }
    @RequestMapping(value = "userinfo",method = RequestMethod.GET)
    public String userInfo(ModelMap map,HttpServletRequest req,HttpServletResponse res){
        acl = new AccountEntityCL();
        this.accountPaging(map, 0, req, res);
        return "aduserinfo";
    }
    @RequestMapping(value = "delUser",method = RequestMethod.GET)
    public String delUser(ModelMap map,@RequestParam int userId,HttpServletRequest req,HttpServletResponse res){
        acl = new AccountEntityCL();
        acl.delAccountById(userId);
        this.accountPaging(map,0,req,res);
        return "aduserinfo";
    }
    @RequestMapping(value = "delCourse",method = RequestMethod.GET)
    public String delCourse(ModelMap map,@RequestParam int courseId,HttpServletRequest request,HttpServletResponse response){
        ccl = new CourseEntityCL();
        ccl.delCourse(courseId);
        this.adCoursePaging(map,0,request,response);
        return "adcourseInfo";
    }
    @RequestMapping(value = "accExcelOut",method = RequestMethod.GET)
    public String accountExcelOut(ModelMap map,HttpServletRequest req,HttpServletResponse res){
        String outPath = req.getSession().getServletContext().getRealPath("/")+"downloads/book.xls";
        acl = new AccountEntityCL();
        acl.excelOutToAccount(outPath);
        return "addownload";
    }
    @RequestMapping(value = "adUpLoadAccPage",method = RequestMethod.GET)
    public String accountExcelIn(ModelMap map,HttpServletRequest req,HttpServletResponse res){return "adaccupload";}
    @RequestMapping(value = "accExcelIn",method = RequestMethod.POST)
    public String accountExcelIn(ModelMap map,@RequestParam(value = "filePath",required = false)MultipartFile file,HttpServletRequest req,HttpServletResponse res){
        String path = req.getSession().getServletContext().getRealPath("/")+"uploads/";
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
        acl = new AccountEntityCL();
        acl.excelBulkImportToAccountDB(AccountEntity.class, path + fileName);
//        map.addAttribute("fileUrl", req.getContextPath()+"/uploads/"+fileName);

//        try {
//            if (!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                return "";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        this.accountPaging(map,0,req,res);
        return "aduserinfo";
    }
    @RequestMapping(value = "classItemExcelIn",method = RequestMethod.POST)
    public String classItemExcelIn(ModelMap map,@RequestParam(value = "filePath",required = false)MultipartFile file,HttpServletRequest req,HttpServletResponse res){
        String path = req.getSession().getServletContext().getRealPath("/")+"uploads/";
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
        acl = new AccountEntityCL();
        acl.excelBulkImportToClassItemDB(ClassItemEntity.class, path + fileName);
        this.accountPaging(map,0,req,res);
        return "aduserinfo";
    }

    @RequestMapping(value = "accountPaging",method = RequestMethod.GET)
    public String accountPaging(ModelMap map,@RequestParam int pageNow,HttpServletRequest request,HttpServletResponse response){
        ArrayList arrayList = null;
        acl = new AccountEntityCL();
        arrayList = acl.getAllAccountByPage(pageNow*pageSize, pageSize);
        map.addAttribute("allAccount",arrayList);
        map.addAttribute("pageNow",pageNow);
        return "aduserinfo";
    }
    @RequestMapping(value = "adCourseInfoPage",method = RequestMethod.GET)
    public String adCoursePaging(ModelMap map,@RequestParam int pageNow,HttpServletRequest request,HttpServletResponse response){
        ArrayList arrayList = null;
        CourseEntityCL ccl = new CourseEntityCL();
        arrayList = ccl.getAllCourseByPage(pageNow*pageSize,pageSize);
        map.addAttribute("allCourses",arrayList);
        map.addAttribute("pageNow",pageNow);
        return "adcourseInfo";
    }
    @RequestMapping(value = "adUpLoadClassItemPage",method = RequestMethod.GET)
    public String adUpLoadClassItemPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        return "adclassitemupload";
    }
    @RequestMapping(value = "adDelAccPage",method = RequestMethod.GET)
    public String adDelAccPage(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        return "adaccdelallpage";
    }
    @RequestMapping(value = "delAllAccount",method = RequestMethod.GET)
    public String delAllAccount(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        AccountEntityCL accCl = new AccountEntityCL();
        ArrayList<Integer> accIdList = accCl.getAccountIdList("000000");
        accCl.delAllAccount(AccountEntity.class,accIdList);
        this.accountPaging(map, 0, request, response);
        return  "aduserinfo";
    }
    @RequestMapping(value = "adquit",method = RequestMethod.GET)
    public String adQuit(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        return "bklogin";
    }
}