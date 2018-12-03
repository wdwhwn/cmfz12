package com.baizhi.controller1;

import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import com.baizhi.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/30.
 */
@Controller
@RequestMapping("/firstPage")
public class FirstPageController {
    @Autowired
   private FirstPageService fps;

    @RequestMapping("/page")
    @ResponseBody
    public Map page(Integer uid,String type,String sub_type){
        System.out.println("12345646");
        Map page = fps.page(uid, type, sub_type);
        return page;
    }

}
