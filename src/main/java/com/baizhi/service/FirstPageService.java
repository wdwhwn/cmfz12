package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ArticalDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Artical;
import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/30.
 */
@Controller
@Transactional
public class FirstPageService {
    @Autowired
    private BannerDao bd;
    @Autowired
    private AlbumDao ad;
    @Autowired
    private ArticalDao atd;
    @Autowired
    private UserDao ud;

    public Map page(Integer uid,String type,String sub_type){
        Map map=new HashMap();
        User user = ud.selectByPrimaryKey(uid);
        System.out.println(user);
        if(user==null){
                map.put("error","登录失败");
            return map;
        }else {
//        轮播图
            List<Banner> list = bd.selectAll();
//        音频
            List<Album> albums = ad.selectAll1();
//        查询上师id
            int i = ud.selectById(uid);
//        文章
            List<Artical> articals = null;

            if ("ssyj".equals(sub_type)) {
                articals = atd.selectByGuru(i);
            }
            if ("xmfy".equals("sub_type")) {
                articals = atd.selectByGuru1(i);
            }
            map.put("header", list);
            map.put("album", albums);
            map.put("aritcal", articals);
            System.out.println(map);
            return map;

        }
    }


}
