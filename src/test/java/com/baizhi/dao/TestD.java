package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wdwhwn on 2018/10/25.
 */
public class TestD extends  TestC{
    @Autowired
    private AlbumDao ad;
    @Test
    public void test() {
        List<Album> albums = ad.selectAll1();
        for (Album album : albums) {
            System.out.println(album);
        }
    }
    @Test
    public void test1(){
        Album album = ad.selectOne(1);
        Album album1 = ad.selectOne1(15);
        System.out.println(album);
        System.out.println(album1);
    }
    @Test
    public void test2(){
        int i=430/450;
    }
}
