package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Created by wdwhwn on 2018/10/28.
 */

public class TestE extends TestC{
    @Autowired
    private UserService us;
    @Autowired
    private UserDao ud;
    @Test
    public void test1(){
       /* List<User> users = ud.selectAll(0, 1);
        System.out.println(users);*/
        Map map = us.selectAll(1, 1);
        System.out.println(map);
    }
    @Test
    public  void test2(){



    }
}
