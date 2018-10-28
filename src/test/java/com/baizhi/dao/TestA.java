/*
package com.baizhi.dao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
*/
/**
 * Created by wdwhwn on 2018/10/28.
 *//*

public class TestA {
        @Test
    public void test() throws IOException {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("第一个工作表");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("第一个单元格");
            workbook.write(new FileOutputStream(new File("e:/a.xls")));
        }
        @Test
    public void test1() throws IOException {
            Workbook workbook=new HSSFWorkbook(new FileInputStream("e:/a.xls"));
            Sheet sheet = workbook.getSheet("第一个工作表");
            int lastRowNum = sheet.getLastRowNum();
            String str=null;
            for(int i=0;i<=lastRowNum;i++){
                Row row = sheet.getRow(i);
                 str= row.getCell(0).getStringCellValue();
            }
            System.out.println(str);
        }
       @Test
    public void test2() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> userList = Arrays.asList(new User(1, "A", new Date()), new User(2, "B", new Date(0)), new User(3, "C", new Date()));
        List<String> strings = Arrays.asList("id", "username", "date");
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("第一个工作表");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
           Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(Font.COLOR_RED);
            font.setFontName("楷书");
            cellStyle.setFont(font);
//         设置日期格式
           DataFormat dataFormat = workbook.createDataFormat();
           short format = dataFormat.getFormat("yyyy年mm月dd日 HH时mm分ss秒");
           CellStyle cellStyle1 = workbook.createCellStyle();
           cellStyle1.setDataFormat(format);

//        标题行
        Row row1 = sheet.createRow(0);
        for (int  i= 0; i < strings.size(); i++) {
            Cell cell = row1.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(strings.get(i));
        }
//        添加数据
        for (int i = 0; i < userList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Class<? extends User> aClass = userList.get(i).getClass();
            for (int j = 0; j < strings.size(); j++) {
                String s = "get" + strings.get(j).substring(0, 1).toUpperCase() + strings.get(j).substring(1);
                Object invoke = aClass.getDeclaredMethod(s, null).invoke(userList.get(i), null);
                if(invoke instanceof Date){
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue((Date)invoke);
                    sheet.setColumnWidth(j,35*256);
                }else if(invoke instanceof Integer){
                    Cell cell = row.createCell(j);
                    cell.setCellValue((Integer)invoke);
                }else {
                    Cell cell = row.createCell(j);
                    cell.setCellValue((String)invoke);

                }

            }
        }
        workbook.write(new FileOutputStream(new File("e:/aa.xls")));
    }
}
*/
