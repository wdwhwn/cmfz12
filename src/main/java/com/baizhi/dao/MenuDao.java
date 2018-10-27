package com.baizhi.dao;

import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Menu record);

    //手风琴一对多查询
    List<Menu> selectAll();

}