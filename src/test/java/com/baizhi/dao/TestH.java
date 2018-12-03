package com.baizhi.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wdwhwn on 2018/10/31.
 */

public class TestH extends TestC{
   @Autowired
   private UserDao ud;
    @Test
    public void testH(){
        int i = ud.count1();
        System.out.println(i);
    }
}
