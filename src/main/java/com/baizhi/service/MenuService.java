package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;

/**
 * Created by wdwhwn on 2018/10/23.
 */
public interface MenuService {
    //手风琴一对多查询
    List<Menu> selectAll();
}
