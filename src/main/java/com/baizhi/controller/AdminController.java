package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by wdwhwn on 2018/10/23.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService as;
    @RequestMapping("login")
    public String login(Admin admin, String enCode, HttpSession session){
        boolean f=as.login(admin);
        String str= (String) session.getAttribute("rightCode");
        session.setAttribute("name",admin.getName());
        if(!enCode.equals(str)){
            return "forward:/login.jsp?error=验证码输入错误";
        }
        if(f==false){
            return "forward:/login.jsp?error1=登录名或者密码错误";
        }
            return "redirect:/main/main.jsp";
    }
}
