package platform.controller;

import com.yk.model.StudentEntity;
import com.yk.model.cl.StudentEntityCL;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;
import java.util.ArrayList;

/**
 * Created by dylanyang on 11/20/15.
 */
@Controller
public class MobileController {
    @RequestMapping(value = "mStuEnquireGrade",method = RequestMethod.GET)
    public String stuGradeInfoPaging(ModelMap map,@RequestParam String stuNo,@RequestParam String passwd,HttpServletRequest request,HttpServletResponse response){
        StudentEntityCL stuCL = new StudentEntityCL();
        if (stuCL.checkStudentExist(stuNo,passwd)){
            StudentEntity se = stuCL.getStudentEntityByStuNo(stuNo);
            map.addAttribute("stuEntity",se);
            return "mobilestuenquire";
        }
        return "index";
    }


}
