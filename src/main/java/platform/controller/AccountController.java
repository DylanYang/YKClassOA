package platform.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transaction;

/**
 * Created by dylanyang on 11/20/15.
 */
@Controller
//@RequestMapping("/")
public class AccountController {
    Session session = null;
    Transaction tx = null;

}
