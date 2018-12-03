package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by wdwhwn on 2018/10/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;
    @RequestMapping("/selectAll")
    @ResponseBody
    public Map selectAll(int rows, int page){
        Map map = us.selectAll(page, rows);
        return map;
    }

    /**
     * excel文件的导入功能
     * @param file  上传的文件
     * @throws IOException
     */
    @RequestMapping("/import")
    @ResponseBody
    public void import1(MultipartFile file) throws IOException {
        //        英文名字  用来拼接get方法
        List<String> enList = Arrays.asList("dharmaName", "name", "sex","headPic", "location","province", "city", "sign", "phonenum", "password", "salt", "status", "regdate","guruId");
//
        List<String> chList = Arrays.asList("法号", "姓名", "性别","头像","所在地", "省份", "城市", "签名", "电话号码", "密码", "加密", "状态", "注册日期","上师id");
        InputStream inputStream = file.getInputStream();
//       工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        工作表
        HSSFSheet sheet = workbook.getSheet("sheet0");
//  计算出最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
       List<User> list=new ArrayList();
        for (int i = 1; i <= lastRowNum; i++) {
//        行
            HSSFRow row = sheet.getRow(i);
                Cell cell = row.getCell(0);
                String dharmaName = cell.getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                int sex = (int) row.getCell(2).getNumericCellValue();
                String headPic = row.getCell(3).getStringCellValue();
                String location = row.getCell(4).getStringCellValue();
                String province = row.getCell(5).getStringCellValue();
                String city = row.getCell(6).getStringCellValue();
                String sign = row.getCell(7).getStringCellValue();
                String phonenum = row.getCell(8).getStringCellValue();
                String password = row.getCell(9).getStringCellValue();
                String salt = row.getCell(10).getStringCellValue();
                String status =row.getCell(11).getStringCellValue();
                Date regdate = row.getCell(12).getDateCellValue();
                int guruId = (int) row.getCell(13).getNumericCellValue();
                User user = new User(0, headPic, dharmaName, name, sex,location, province, city, sign, phonenum, password, salt, status, regdate,guruId);
                list.add(user);

        }
        us.insert(list);
    }

    /**
     * 全部导出
     * 1. 从数据库中查询全部
     * 2. 创建work对象、sheet对象、row对象，且设定输出格式（字体类型、字体颜色、居中等）
     * 3. 输出标题行
     * 4. 将从数据库中查询的结果遍历到row行中
     * 5. 通过反射的形式，将属性值添加到单元格中   反射结果为object，再通过条件分支判断
     * 6.  将文件以附件下载方式响应给浏览器
     * @param response 用来执行下载功能
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<User> users = us.selectAll1();
//        中文名字  导出字段名
            List<String> chList = Arrays.asList("法号", "姓名", "性别", "头像","所在地","省份", "城市", "签名", "电话号码", "密码", "加密", "状态", "注册日期","上师id");
//        英文名字  用来拼接get方法
            List<String> enList = Arrays.asList("dharmaName", "name", "sex","headPic","location", "province", "city", "sign", "phonenum", "password", "salt", "status", "regdate","guruId");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
//        设置标题栏 单元格属性 ，居中，字体类型、加粗和颜色
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("隶书");
            font.setColor(Font.COLOR_RED);
            cellStyle.setFont(font);
//       创建标题行
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i <chList.size() ; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                System.out.println(chList.get(i));
                cell.setCellValue(chList.get(i));
            }
//            创建单元格疏输出的日期格式
        CellStyle cellStyle1 = workbook.createCellStyle();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-mm-dd hh:mm:ss");
            cellStyle1.setDataFormat(format);

//            输出输出   通过反射
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            Class<? extends User> aClass = users.get(i).getClass();
            for (int j = 0; j < enList.size(); j++) {
                String s = "get" + enList.get(j).substring(0, 1).toUpperCase() + enList.get(j).substring(1);
                HSSFCell cell = row1.createCell(j);
                Object invoke = aClass.getDeclaredMethod(s, null).invoke(users.get(i), null);
//               条件分支   强制类型转换
                if(invoke instanceof Date ){
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue((Date) invoke);
                    sheet.setColumnWidth(j,22*256);
                }else if(invoke instanceof Integer){
                    cell.setCellValue((Integer)invoke);
                }else{
                    cell.setCellValue((String)invoke);
                }
            }
        }
//      以下载的形式输出到磁盘  设置响应头（以附件的形式下载）、输出字符编码
            long time = new Date().getTime();
            String s=time+"文件.xls";
            try {
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/vnd.ms-excel");
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     *
     * @param response
     * @param titles
     * @param fileds
     */
//    自定义导出
        @RequestMapping("/customerExport")
        @ResponseBody
        public void customerExport(HttpServletResponse response,String titles,String fileds){
            List<User> users = us.selectAll1();
            System.out.println(titles+" "+fileds);
            List<String> chList = Arrays.asList(titles.split(","));
            List<String> enList = Arrays.asList(fileds.split(","));

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
//        设置标题栏 单元格属性
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("隶书");
            font.setColor(Font.COLOR_RED);
            cellStyle.setFont(font);
//       创建标题行
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i <chList.size() ; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                System.out.println(chList.get(i));
                cell.setCellValue(chList.get(i));
            }
//            创建单元格疏输出的日期格式
            CellStyle cellStyle1 = workbook.createCellStyle();
            HSSFDataFormat dataFormat = workbook.createDataFormat();
            short format = dataFormat.getFormat("yyyy-mm-dd hh:mm:ss");
            cellStyle1.setDataFormat(format);

//            输出输出
            for (int i = 0; i < users.size(); i++) {
                HSSFRow row1 = sheet.createRow(i+1);
                Class<? extends User> aClass = users.get(i).getClass();
                for (int j = 0; j < enList.size(); j++) {
                    String s = "get" + enList.get(j).substring(0, 1).toUpperCase() + enList.get(j).substring(1);
                    HSSFCell cell = row1.createCell(j);
                    Object invoke = null;
                    try {
                        invoke = aClass.getDeclaredMethod(s, null).invoke(users.get(i), null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if(invoke instanceof Date ){
                        cell.setCellStyle(cellStyle1);
                        cell.setCellValue((Date) invoke);
                        sheet.setColumnWidth(j,22*256);
                    }else if(invoke instanceof Integer){
                        cell.setCellValue((Integer)invoke);
                    }else{
                        cell.setCellValue((String)invoke);
                    }
                }
            }
//      以下载的形式输出到磁盘
            long time = new Date().getTime();
            String s=time+"文件.xls";
            try {
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/vnd.ms-excel");
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @RequestMapping("/login")
        @ResponseBody
        public Map login(String phone,String password,String code){
            Map login = us.login(phone, password, code);
            return login;
        }
        @RequestMapping("/register")
        @ResponseBody
        public Map register(String phone,String password){
        Map register = us.register(phone, password);
        return register;
        }


        @RequestMapping("/update")
        @ResponseBody
        public Map update(User user1){
        System.out.println(user1);
        boolean b = us.updateByPrimaryKey(user1);
        Map map=new HashMap();
        if(b==true) {
                User user = us.selectByPrimaryKey(user1.getId());
                map.put("password", user.getPassword());
                map.put("farmington", user.getDharmaName());
                map.put("uid", user.getId());
                map.put("nickname", user.getName());
                map.put("gender", user.getSex());
                map.put("photo", user.getHeadPic());
                map.put("location", user.getLocation());
                map.put("province", user.getProvince());
                map.put("city", user.getCity());
                map.put("description", user.getSign());
                map.put("phone", user.getPhonenum());
                return map;
            }else{
                map.put("errno","-200");
                map.put("error_msg","该手机号已经存在");
                return map;
            }
        }

        @RequestMapping("/selectAll1")
        @ResponseBody
    public List<User> selectAll1(int uid){
            List<User> users = us.selectAll2(uid);
            return users;
        }

    }


