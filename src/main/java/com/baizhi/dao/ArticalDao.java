package com.baizhi.dao;

import com.baizhi.entity.Artical;
import java.util.List;

public interface ArticalDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Artical record);

    Artical selectByPrimaryKey(Integer id);

    List<Artical> selectAll();

    int updateByPrimaryKey(Artical record);
//    =======首页接口=====
//    查询最新的两篇 上师文章
    List<Artical> selectByGuru(int guruId);
//    查询最新的两篇  其它上师文章
    List<Artical> selectByGuru1(int guruId);
}