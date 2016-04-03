package platform.transcation.utils;

import com.yk.model.AccountEntity;
import com.yk.model.cl.AccountEntityCL;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dylanyang on 12/1/15.
 */
public class ExcelBookUtil {
    AccountEntityCL acl = null;

    /**
     * dylan
     * @param objList
     * @param outPath
     */
    public void excelOutByObjList(List objList,String outPath,int fieldOffset){
        WritableWorkbook book = null;
        try {
            book  = Workbook.createWorkbook(new File(outPath));
            WritableSheet sheet = book.createSheet("sheet",0);
            for (int i = 0;i < objList.size();i++){
                Object obj = objList.get(i);
                Class clazz = obj.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (int j = 0;j < fields.length-fieldOffset;j++){
                    fields[j+fieldOffset].setAccessible(true);
                    Label label = new Label(j,i,String.valueOf(fields[j].get(obj)));
                    sheet.addCell(label);
                }
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

    /**
     * dylan
     *
     * @param clazz       ---Reflect Entity Class
     * @param filePath
     * @param fieldOffset ---beginning filed from definite position
     * @return arrList
     */
    public ArrayList excelIn(Class clazz,String filePath,int fieldOffset){
        ArrayList arList = new ArrayList();
        Workbook book  = null;
        try {
            book = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = book.getSheet(0);
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0;i < sheet.getRows();i++){
                Object obj = clazz.newInstance();
                for (int j = 0;j < fields.length-fieldOffset;j++){
                    fields[j+fieldOffset].setAccessible(true);
                    String con = sheet.getCell(j,i).getContents();
                    if (fields[j+fieldOffset].getType().toString().equals("class java.lang.String")){
                        fields[j+fieldOffset].set(obj,con);
                    }else if (fields[j+fieldOffset].getType().toString().equals("int")){
                        fields[j+fieldOffset].set(obj,Integer.valueOf(con));
                    }else if (fields[j+fieldOffset].getType().toString().equals("class java.lang.Integer")){
                        fields[j+fieldOffset].set(obj,Integer.valueOf(con));
                    }else if(fields[j+fieldOffset].getType().toString().equals("class java.lang.Date")){
                        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = dd.parse(dd.format(new Date()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fields[j+fieldOffset].set(obj,date);
                    }
                }
                arList.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            book.close();
        }
        return arList;
    }

    /**
     *
     * @param outPath
     */
    public void excelInToXLS(String outPath){
//        List<AccountEntity>
    }
}
