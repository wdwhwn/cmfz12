package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/31.
 */
@Controller
@RequestMapping("/test")
public class TestMsgController {
    @Autowired
    private UserService us;
    @RequestMapping("/check")
    @ResponseBody
    public Map check(String phone, String  code){
        Map map=new HashMap();
        boolean b = us.selectByPhone(phone);
        Jedis jedis= new Jedis();
        String code1=jedis.get("code");
        System.out.println(code1);
        if(b==true && code.equals(code1)){
                   map.put("result","success");
           return  map;
        }else{
            map.put("result","fail");
            return map;
        }
    }

}
