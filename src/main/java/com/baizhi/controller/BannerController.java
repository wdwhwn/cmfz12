package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wdwhwn on 2018/10/23.
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bs;
    @RequestMapping("/selectAllByPage")
    public @ResponseBody Map selectAllByPage( int rows, int page){
        Map map = bs.selectAllByPage(page, rows);
        return map;
    }
    @RequestMapping("/insert")
    public @ResponseBody boolean  insert(MultipartFile multipartFile, Banner banner, HttpServletRequest request){
//        设定存储文件夹
//        生成uuid，并形成一个不重复的和上传文件后缀一致的名字
//         将文件传到项目的文件夹中，并且设定其名字为新生成的名字
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file=new File(realPath+"/upload");
        if(!file.exists()){
            file.mkdir();
        }

        String s = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String newName=s+"."+extension;

        try {
            multipartFile.transferTo(new File(file,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        banner.setUrl(newName);
        try {
            bs.insert(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @RequestMapping("/update")
    public @ResponseBody boolean  update(int id,int status){
        try {
           bs.updateByPrimaryKey(id,status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return false;
        }
    }
    @RequestMapping("/delete")
    public @ResponseBody Boolean  delete(int[] ids){
        try {
           bs.deleteByPrimaryKey(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;


    }


}
