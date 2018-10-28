package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService us;
    @RequestMapping("/selectAll")
    public Map selectAll(Integer rows, Integer page){
        System.out.println(123);

        System.out.println(us);
        System.out.println(page+"    "+rows);
        Map map = us.selectAll(page, rows);
        return map;
    }
}
