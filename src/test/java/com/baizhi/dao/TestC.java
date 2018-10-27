package com.baizhi.dao;

import com.baizhi.App;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestC {
    @Autowired
    private MenuDao md;
    @Autowired
    private BannerService bs;

    @Autowired
    private AdminService as;
    @Test
    public void test(){
        List<Menu> menus = md.selectAll();
        for (Menu menu : menus) {
            System.out.println(menu.getTitle());
            List<Menu> list1=menu.getList();
            for (Menu menu1 : list1) {
                System.out.print(menu1.getTitle()+" ");
            }
            System.out.println();
        }
    }
    @Test
    public void test1(){
        Map map = bs.selectAllByPage(1, 3);
        List<Banner> list= (List<Banner>) map.get("rows");
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }
    @Test
    public void test2(){
        Banner ba=new Banner(0 ,"name","12.jpg", 0, new Date(), "miaoshu");
        bs.insert(ba);
    }@Test
    public void test3(){
        bs.updateByPrimaryKey(1,0);
    }@Test
    public void test4(){
        int[] ids=new int[]{1};
        bs.deleteByPrimaryKey(ids);
    }
}
