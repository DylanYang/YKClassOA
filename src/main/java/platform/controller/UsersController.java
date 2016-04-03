package platform.controller;

import com.yk.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import platform.DAO.utils.HibernateUtil;

import java.util.List;


/**
 * Created by dylanyang on 10/3/15.
 */
@Controller
@RequestMapping("/")
public class UsersController {
    Session session = null;
    Transaction tx = null;
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
    }
    @RequestMapping(value = "users",method = RequestMethod.GET)
    public String showUserInfo(ModelMap map){
        session = HibernateUtil.getSession();
        List<UserEntity> list = session.createCriteria(UserEntity.class).addOrder(Order.desc("id")).list();
        map.addAttribute("users",list);
        session.close();
        return "result";
    }
}
