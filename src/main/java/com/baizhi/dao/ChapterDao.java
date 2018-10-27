package com.baizhi.dao;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    int deleteByPrimaryKey(Integer id);
//  添加
    int insert(Chapter record);

    Chapter selectByPrimaryKey(Integer id);

    List<Chapter> selectAll();

    int updateByPrimaryKey(Chapter record);
}