package com.yk.model.cl;

import com.yk.common.AccountRoleConstant;
import com.yk.common.DeptConstant;
import com.yk.model.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import platform.DAO.utils.HibernateUtil;
import platform.transcation.utils.ExcelBookUtil;

import platform.DAO.utils.BaseDAO;

import javax.servlet.http.HttpServlet;
import org.hibernate.Transaction;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dylanyang on 11/25/15.
 */
public class AccountEntityCL extends HttpServlet{
    /**
     * dylan
     * @param  username, passwd, accountType
     * @return boolean
     */
    public boolean checkUser(String username,String passwd){
        //get user info
//        List<AccountEntity> acclist = session.createCriteria(AccountEntity.class).addOrder(Order.desc("id")).list();
        String hql = "from AccountEntity order by id ";
        List<AccountEntity> acclist = BaseDAO.executeQuery(hql);
        for (AccountEntity ae:acclist){
            if (username.equals(ae.getUsername()) && passwd.equals(ae.getPassword())){
                return true;
            }
        }
        return false;
    }
    /**
     * dylan
     * @param AccountType
     * @return accType
     */
    public int checkUserLoginRole(int AccountType,String staffId){
        String hql = "from AccountEntity a,TeacherEntity t  where a.teacher_id = t.id and t.staffId = ?";
        List list = BaseDAO.executeQuery(hql,staffId);
        for (int i = 0;i < list.size();i++){
            Object[] o = (Object[])list.get(i);
            int accRole = ((AccountEntity)o[0]).getRole();
            if (accRole == AccountType){
                if (AccountType == AccountRoleConstant.ADMINISTRATOR) {
                    return AccountRoleConstant.ADMINISTRATOR;
                }else if (AccountType == AccountRoleConstant.CLASS_MASTER){
                    return AccountRoleConstant.CLASS_MASTER;
                }else {return -1;}
            }else {return -1;}
        }
        return -1;
    }
    /**
     * dylan
     *
     * @return accountList
     */
    public List<AccountEntity> getAllAccount(){
        //get user info
        String hql = "from AccountEntity a order by id";
//        List<AccountEntity> acclist = session.createCriteria(AccountEntity.class).addOrder(Order.asc("id")).list();
        List<AccountEntity> acclist = BaseDAO.executeQuery(hql);
        return acclist;
    }
    /**
     * dylan
     * @param pageNow
     * @param pageSize
     * @return arraylist
     */
    public ArrayList getAllAccountByPage(int pageNow,int pageSize){
        String hql = "from AccountEntity a,TeacherEntity t,ClassItemEntity c where a.teacher_id = t.id and t.classItem_id = c.id";
        ArrayList list = BaseDAO.pagingQuery(hql, pageNow, pageSize);
//        ArrayList arrayList1 = new ArrayList();
//        ArrayList arrayList2 = new ArrayList();
//        AccountEntity ae = new AccountEntity();
//        TeacherEntity te = new TeacherEntity();
//        Object[] obj = new Object[list.size()];
//        for (int i=0;i<list.size();i++){
//            Object[] o = (Object[])list.get(i);
//            te.setStaffId(((TeacherEntity) o[1]).getStaffId());
//            ae.setUsername(((AccountEntity) o[0]).getUsername());
//            ae.setPassword(((AccountEntity) o[0]).getPassword());
//            ae.setName_ch(((AccountEntity) o[0]).getName_ch());
//            ae.setCreate_time(((AccountEntity) o[0]).getCreate_time());
//            ae.setEmail(((AccountEntity) o[0]).getEmail());
//            ae.setRole(((AccountEntity) o[0]).getRole());
//            ae.setId_card(((AccountEntity) o[0]).getId_card());
//            ae.setDept(((AccountEntity) o[0]).getDept());
//            arrayList1.add(te);
//            arrayList1.add(ae);
//            obj[i] = arrayList1;
//        }
        return list;
    }
    /**
     * dylan
     *
     * @param id
     * @return list
     */
    public List<AccountEntity> getAccountById(int id){
//        String hql = "from AccountEntity a,TeacherEntity t where a.id = ? and a.id = t.id";
        String hql = "from AccountEntity a where a.id = ? ";
        List list = list = BaseDAO.executeQuery(hql,id);
        return list;
    }
    /**
     * dylan
     * @param username
     * @return acc_id
     */
    public int getAccountIdByUserName(String username){
        String hql = "select a.id from AccountEntity a where a.username = ?";
        return (Integer)BaseDAO.executeQuery(hql,username).get(0);
    }
    /**
     * dylan
     *
     * @param account
     */
    public void addAccount(ArrayList<AccountEntity> account,String classNo){
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        TeacherEntity te = null;
        for (AccountEntity ae : account){
            try {
                date = dd.parse(dd.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            ae.setUsername(account.get(i).getUsername());
//            ae.setPassword(account.get(i).getPassword());
//            ae.setName_ch(account.get(i).getName_ch());
//            ae.setCreate_time(date);
//            ae.setEmail(account.get(i).getEmail());
//            ae.setRole(account.get(i).getRole());
//            ae.setId_card(account.get(i).getId_card());
//            ae.setDept(account.get(i).getDept());
            ae.setUsername(ae.getUsername());
            ae.setPassword(ae.getPassword());
            ae.setName_ch(ae.getName_ch());
            ae.setCreate_time(date);
            ae.setEmail(ae.getEmail());
            ae.setRole(ae.getRole());
            ae.setId_card(ae.getId_card());
            ae.setDept(ae.getDept());
            te = new TeacherEntity();
            te.setStaffId(ae.getUsername());
            ClassItemEntityCL ccl = new ClassItemEntityCL();
            int classItemId = ccl.getClassItemId(classNo);
            ClassItemEntity classItemEntity = (ClassItemEntity) BaseDAO.getObjClassBySessionGetQuery(ClassItemEntity.class, classItemId);
            te.setClassItem_id(classItemEntity);
            ae.setTeacher_id(te);
            BaseDAO.add(te,ae);
        }
    }

    /**
     * dylan
     * @param account
     */
    public void addAccount(ArrayList<AccountEntity> account){
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        TeacherEntity te = null;
        for (AccountEntity ae : account){
            try {
                date = dd.parse(dd.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ae.setUsername(ae.getUsername());
            ae.setPassword(ae.getPassword());
            ae.setName_ch(ae.getName_ch());
            ae.setCreate_time(date);
            ae.setEmail(ae.getEmail());
            ae.setRole(ae.getRole());
            ae.setId_card(ae.getId_card());
            ae.setDept(ae.getDept());
            te = new TeacherEntity();
            te.setStaffId(ae.getUsername());
            ae.setTeacher_id(te);
            te.setStaffId(ae.getUsername());
            BaseDAO.add(ae,te);
        }
    }
    /**
     * dylan
     *
     * @param account
     */
    public void updateAccount(ArrayList<AccountEntity> account,ArrayList<TeacherEntity> te){
        String hql = "update AccountEntity a  set a.username=?,a.password=?,a.name_ch=?,a.email=?,a.role=?,a.id_card=?,a.dept=? where a.id=?";

        int ret = BaseDAO.executeUpdate(hql, account.get(0).getUsername(), account.get(0).getPassword(), account.get(0).getName_ch(), account.get(0).getEmail(),
                account.get(0).getRole(), account.get(0).getId_card(), account.get(0).getDept(), account.get(0).getId() );
        hql = "update TeacherEntity t set t.staffId=? where t.id=?";
        ret = BaseDAO.executeUpdate(hql,account.get(0).getUsername(),te.get(0).getId());
        System.out.println("query influence: " + ret);
    }
    /**
     * dylan
     *
     * @param userId
     */
    public void delAccountById(int userId){
//        String hql = "delete from AccountEntity a where a.id = ?";
        BaseDAO.dellObjByCascade(AccountEntity.class,userId);
//        BaseDAO.executeUpdate(hql, userId);
    }
    /**
     * dylan
     *
     * @return rowCount
     */
    public int getAccountRowCount(){
        String hql = "select count(*) from AccountEntity";
        return BaseDAO.getRowCount(hql);
    }
    /**
     * dylan
     *
     * @param pageSize
     * @return pageCount
     */
    public int getAccountPageCount(int pageSize){
        int pageCount = 0;
        int rowCount = this.getAccountRowCount();
//        pageCount = rowCount/pageSize;
        if (rowCount % pageSize == 0){
            pageCount = rowCount/pageSize;
        }else{
            pageCount = rowCount/pageSize+1;
        }
        return pageCount;
    }
    /**
     * dylan
     *
     * @return String
     */
    public String getDeptChinese(int deptType){
        String typeStr;
        switch (deptType){
            case DeptConstant.YK_DEAN_OFFICE:
                typeStr = "院长办公室";
                break;
            case DeptConstant.YK_VICE_DEAN_OFFICE:
                typeStr = "副院长办公室";
                break;
            case DeptConstant.YK_SECRETARY_OFFICE:
                typeStr = "书记办公室";
                break;
            case DeptConstant.YK_OFFICE:
                typeStr = "院办公室";
                break;
            case DeptConstant.YK_GENERAL_DEPT:
                typeStr = "综合办公室";
                break;
            case DeptConstant.YK_STUDENT_DEPT:
                typeStr = "学工办";
                break;
            case DeptConstant.YK_STUDY_DEPT:
                typeStr = "教务办";
                break;
            case DeptConstant.YK_YOUTH_COMMITTEE:
                typeStr = "团委";
                break;
            case DeptConstant.YK_ENGLISH_SECTION:
                typeStr = "英语教研室";
                break;
            case DeptConstant.YK_CHINESE_SECTION:
                typeStr = "语文教研室";
                break;
            case DeptConstant.YK_COMPUTER_SECTION:
                typeStr = "计算机教研室";
                break;
            case DeptConstant.YK_POLITICS_SECTION:
                typeStr = "政治教研室";
                break;
            default:
                typeStr = "数学教研室";
                break;
        }
        return typeStr;
    }

    /**
     * dylan
     * @param deptType
     * @return
     */
    public int getDeptByChinese(String deptType){
        int typeInt = 0;
        if (deptType.equals("院长办公室")) {
            typeInt = DeptConstant.YK_DEAN_OFFICE;
        }else if (deptType.equals("副院长办公室")) {
            typeInt = DeptConstant.YK_VICE_DEAN_OFFICE;
        }else if (deptType.equals("书记办公室")) {
            typeInt = DeptConstant.YK_SECRETARY_OFFICE;
        }else if (deptType.equals("院办公室")) {
            typeInt = DeptConstant.YK_OFFICE;
        }else if (deptType.equals("综合办公室")){
            typeInt = DeptConstant.YK_GENERAL_DEPT;
        }else if (deptType.equals("学工办")) {
            typeInt = DeptConstant.YK_STUDENT_DEPT;
        }else if (deptType.equals("教务办")) {
            typeInt = DeptConstant.YK_STUDY_DEPT;
        }else if (deptType.equals("团委")) {
            typeInt = DeptConstant.YK_YOUTH_COMMITTEE;
        }else if (deptType.equals("英语教研室")) {
            typeInt = DeptConstant.YK_ENGLISH_SECTION;
        }else if (deptType.equals("语文教研室")) {
            typeInt = DeptConstant.YK_CHINESE_SECTION;
        }else if (deptType.equals("计算机教研室")) {
            typeInt = DeptConstant.YK_COMPUTER_SECTION;
        }else if (deptType.equals("政治教研室")) {
            typeInt = DeptConstant.YK_POLITICS_SECTION;
        }else if (deptType.equals("数学教研室")) {
            typeInt = DeptConstant.YK_MATH_SECTION;
        }
        return typeInt;
    }
    /**
     * dylan
     *
     * @return list
     */
    public List getDeptList(){
        List list = new ArrayList();
        list.add("院长办公室");
        list.add("副院长办公室");
        list.add("书记办公室");
        list.add("院办公室");
        list.add("综合办公室");
        list.add("学工办");
        list.add("教务办");
        list.add("团委");
        list.add("英语教研室");
        list.add("语文教研室");
        list.add("计算机教研室");
        list.add("政治教研室");
        list.add("数学教研室");
        return list;
}

    /**
     * dylan
     *
     * @param roleType
     * @return String
     */
    public String getRoleChinese(int roleType){
        String typeStr = "";
        switch (roleType){
            case AccountRoleConstant.ADMINISTRATOR:
                typeStr = "管理员";
                break;
            case AccountRoleConstant.CLASS_MASTER:
                typeStr = "班主任";
                break;
        }
        return typeStr;
    }

    /**
     * dylan
     * @param roleType
     * @return typeInt
     */
    public int getRoleByChinese(String roleType){
        int typeInt = 0;
        if (roleType.equals("管理员")) {
            typeInt = AccountRoleConstant.ADMINISTRATOR;
        }else if (roleType.equals("班主任")) {
            typeInt = AccountRoleConstant.CLASS_MASTER;
        }
        return typeInt;
    }
    public void excelOutToAccount(String outPath){
        List<AccountEntity> accountList = this.getAllAccount();
//        File directory = new File("..");
//        String outPaht = "";
//        try {
////            outPaht = System.getProperty("user.dir");
////             outPaht = directory.getCanonicalPath()+"/webapps/YKClassOA/downloads/book.xls";/Users/dylanyang/IdeaProjects/YKClassOA/resources/book.xls
//            outPaht = "/Users/dylanyang/IdeaProjects/YKClassOA/target/YKClassOA/downloads/book.xls";
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        ExcelBookUtil excelBook = new ExcelBookUtil();
        excelBook.excelOutByObjList(accountList,outPath,1);
    }
    /**
     * dylan
     *
     * @param clazz     ---Class Name
     * @param filePath  ---excel file path
     * @param offset    ---beginning field of excel
     */
    public void excelBulkImportToAccountDBOnCommon(Class clazz,String filePath,int offset){
        ExcelBookUtil bookUtil = new ExcelBookUtil();
        AccountEntity ae = new AccountEntity();
        ArrayList<AccountEntity> arrayList = bookUtil.excelIn(clazz,filePath,offset);
        this.addAccount(arrayList);
    }

    /**
     * dylan
     * @param clazz
     * @param filePath
     */
    public void excelBulkImportToAccountDB(Class clazz,String filePath){
        AccountEntity ae = new AccountEntity();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int colums = sheet.getColumns();
            for(int i = 1;i<rows;i++){
                SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                TeacherEntity te = null;
                    try {
                        date = dd.parse(dd.format(new Date()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ae.setUsername(sheet.getCell(0,i).getContents());
                    ae.setPassword(sheet.getCell(1,i).getContents());
                    ae.setName_ch(sheet.getCell(2,i).getContents());
                    ae.setCreate_time(date);
                    ae.setEmail(sheet.getCell(3,i).getContents());
                    ae.setRole(this.getRoleByChinese(sheet.getCell(4, i).getContents()));
                    ae.setId_card(sheet.getCell(5,i).getContents());
                    ae.setDept(this.getDeptByChinese(sheet.getCell(6, i).getContents()));
                    te = new TeacherEntity();
                    te.setStaffId(sheet.getCell(0,i).getContents());
                    ClassItemEntityCL ccl = new ClassItemEntityCL();
                    int classItemId = ccl.getClassItemId(sheet.getCell(7,i).getContents());
                    ClassItemEntity classItemEntity = (ClassItemEntity) BaseDAO.getObjClassBySessionGetQuery(ClassItemEntity.class, classItemId);
                    te.setClassItem_id(classItemEntity);
                    ae.setTeacher_id(te);
                    BaseDAO.add(te,ae);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            book.close();
        }
    }

    /**
     * dylan
     * @param clazz
     * @param filePath
     */
    public void excelBulkImportToClassItemDB(Class clazz,String filePath){
        ClassItemEntity ce = new ClassItemEntity();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            int colums = sheet.getColumns();
            for(int i = 1;i<rows;i++){
                ce.setClassNo(sheet.getCell(0,i).getContents());
                ce.setYear((Integer.parseInt(sheet.getCell(1,i).getContents())));
                BaseDAO.add(ce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            book.close();
        }
    }

    /**
     * dylan
     * @param clazz
     * @param accIdList
     */
    public void delAllAccount(Class clazz,ArrayList<Integer> accIdList){
        for (int id:accIdList){
            try {
                BaseDAO.dellObjByCascade(clazz,id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * dylan
     * @param username
     * @return accList
     */
    public ArrayList<Integer> getAccountIdList(String username){
        ArrayList<Integer> accIdList = new ArrayList<Integer>();
        String hql = "select a.id from AccountEntity a where username <> ?";
        try {
            accIdList = BaseDAO.executeQuery(hql,username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accIdList;
    }
}
