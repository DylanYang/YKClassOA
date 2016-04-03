package com.yk.test;

import com.yk.model.AccountEntity;
import com.yk.model.cl.AccountEntityCL;
import platform.transcation.utils.ExcelBookUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanyang on 11/30/15.
 */
public class ExcelOITest {
    Session session = null;
    Transaction tx = null;
    AccountEntityCL acl = null;
    public void excelOut(List accList){
        WritableWorkbook book = null;
        try {
            book  = Workbook.createWorkbook(new File("/Users/dylanyang/IdeaProjects/YKClassOA/resources/book.xls"));
            WritableSheet sheet = book.createSheet("sheet1",0);
            acl = new AccountEntityCL();
            Label label00 = new Label(0,0,"id");
            Label label01 = new Label(1,0,"用户名");
            Label label02 = new Label(2,0,"密码");
            Label label03 = new Label(3,0,"姓名");
            Label label04 = new Label(4,0,"日期");
            Label label05 = new Label(5,0,"邮箱");
            Label label06 = new Label(6,0,"角色");
            Label label07 = new Label(7,0,"身份证");
            Label label08 = new Label(8,0,"部门");
            sheet.addCell(label00);
            sheet.addCell(label01);
            sheet.addCell(label02);
            sheet.addCell(label03);
            sheet.addCell(label04);
            sheet.addCell(label05);
            sheet.addCell(label06);
            sheet.addCell(label07);
            sheet.addCell(label08);
            for (int i = 0;i < accList.size();i++){
                AccountEntity ae = (AccountEntity)accList.get(i);
                Label label0 = new Label(0,i+1,String.valueOf(ae.getId()));
                Label label1 = new Label(1,i+1,String.valueOf(ae.getUsername()));
                Label label2 = new Label(2,i+1,String.valueOf(ae.getPassword()));
                Label label3 = new Label(3,i+1,String.valueOf(ae.getName_ch()));
                Label label4 = new Label(4,i+1,String.valueOf(ae.getCreate_time()));
                Label label5 = new Label(5,i+1,String.valueOf(ae.getEmail()));
                Label label6 = new Label(6,i+1,String.valueOf(String.valueOf(acl.getRoleChinese(ae.getRole()))));
                Label label7 = new Label(7,i+1,String.valueOf(ae.getId_card()));
                Label label8 = new Label(8,i+1,String.valueOf(acl.getDeptChinese(ae.getDept())));
                sheet.addCell(label0);
                sheet.addCell(label1);
                sheet.addCell(label2);
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
            }
            book.write();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                book.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void excelOutToAccount(){
        acl = new AccountEntityCL();
        acl.excelOutToAccount("");
    }
    public void excelIn(){
        ArrayList<AccountEntity> account = new ArrayList<AccountEntity>();
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File("/Users/dylanyang/IdeaProjects/YKClassOA/resources/book.xls"));
            Sheet sheet = book.getSheet(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            for (int i = 0;i < sheet.getRows();i++){
                AccountEntity ae = new AccountEntity();
                Cell cell = sheet.getCell(0, i);
                ae.setUsername(String.valueOf(sheet.getCell(0, i).getContents()));
                ae.setPassword(String.valueOf(sheet.getCell(1, i).getContents()));
                ae.setName_ch(String.valueOf(sheet.getCell(2, i).getContents()));
                date = sdf.parse(sheet.getCell(3, i).getContents());
                ae.setCreate_time(date);
                ae.setEmail(String.valueOf(sheet.getCell(4, i).getContents()));
                ae.setRole(Integer.valueOf(sheet.getCell(5, i).getContents()));
                ae.setId_card(String.valueOf(sheet.getCell(6, i).getContents()));
                ae.setDept(Integer.valueOf(sheet.getCell(7, i).getContents()));
                account.add(ae);
            }
            acl = new AccountEntityCL();
            acl.addAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void excelInByReflect(){
        String path = "/Users/dylanyang/IdeaProjects/YKClassOA/resources/book.xls";
        ExcelBookUtil bookUtil = new ExcelBookUtil();
        AccountEntity ae = new AccountEntity();
        ArrayList<AccountEntity> arrayList = bookUtil.excelIn(AccountEntity.class,path,2);
    }
    public static void main(String[] args){
        ExcelOITest et = new ExcelOITest();
//        AccountEntityCL acl = new AccountEntityCL();
//        List<AccountEntity> accountList =  acl.getAllAccount();
//        et.excelOut(accountList);
//        et.excelOutToAccount();
//        et.excelIn();
        et.excelInByReflect();
    }
}
