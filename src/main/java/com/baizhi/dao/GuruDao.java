package com.baizhi.dao;

import com.baizhi.entity.Guru;
import java.util.List;

public interface GuruDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Guru record);

    Guru selectByPrimaryKey(Integer id);

    List<Guru> selectAll();

    int updateByPrimaryKey(Guru record);
}