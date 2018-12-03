package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/30.
 */
@Service
public class WenServiceImpl implements  WenService{
    @Autowired
    private UserDao ud;
    @Autowired
    private AlbumDao ad;
    @Autowired
    private ChapterDao cd;
    public Map wen(int uid, int id){
        User user = ud.selectByPrimaryKey(uid);
        Map map=new HashMap();
        if(user==null){
            map.put("error","用户登录错误");
        }else {
            Album album = ad.selectByPrimaryKey(id);
            List<Chapter> chapters = cd.selectAll1(id);
            map.put("introduction", album);
            map.put("list", chapters);
        }
        return map;
    }
}
