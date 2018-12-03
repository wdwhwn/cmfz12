package com.baizhi.controller1;

import com.baizhi.service.WenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/30.
 */
@Controller
@RequestMapping("/wen")
public class WenController {
    @Autowired
    private WenService ws;
    @RequestMapping("/wen")
    @ResponseBody
    public Map wen(int uid, int id){
        Map wen = ws.wen(uid, id);
        return wen;
    }


}
