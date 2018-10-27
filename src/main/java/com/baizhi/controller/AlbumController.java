package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wdwhwn on 2018/10/25.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService as;
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Album> selectAll(){
        List<Album> albums = as.selectAll1();

        return albums;
    }
    @RequestMapping("/selectOne")
    @ResponseBody
    public Album selectOne(int id){
        Album album = as.selectOne(id);
        System.out.println(album);
        return album;
    }
    @RequestMapping("/selectOne1")
    @ResponseBody
    public Album selectOne1(int id){
        Album album = as.selectOne1(id);
        return album;
    }@RequestMapping("/insert")
    @ResponseBody
    public Boolean insert(MultipartFile multipartFile, Album album, HttpServletRequest request){

        try {
            as.insert(multipartFile,album,request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
