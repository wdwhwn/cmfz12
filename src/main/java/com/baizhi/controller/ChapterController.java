package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by wdwhwn on 2018/10/26.
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService cs;
    @RequestMapping("/insert")
    @ResponseBody
    public Boolean insert(Chapter chapter, MultipartFile multipartFile, HttpServletRequest request){
        try {
            cs.insert(chapter,multipartFile,request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } @RequestMapping("/download")
    @ResponseBody
    public boolean dowload(String url,String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/audion");
            File file = new File(realPath+"/"+url);
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(name,"UTF-8"));
            FileUtils.copyFile(file,response.getOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
