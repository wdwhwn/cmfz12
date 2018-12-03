package com.baizhi.dao;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by wdwhwn on 2018/10/31.
 */
public class TestG {
    @Test
    public void testa(){
        Jedis jedis= new Jedis();
        String code1=jedis.get("code");
        System.out.println(code1);
    }
}
