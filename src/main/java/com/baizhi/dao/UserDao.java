package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(User record);

//    分页查询全部
    List<User> selectAll(@Param("start") int start,@Param("rows") int rows);
//    统计数量
    int count();
}
