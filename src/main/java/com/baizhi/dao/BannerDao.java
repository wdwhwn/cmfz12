package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {

    Banner selectByPrimaryKey(Integer id);



//    分页查询  轮播图
    List<Banner> selectAllByPage(@Param("start") int start, @Param("rows") int rows);
//    分页查询  个数
    int count();
    //  添加
    void insert(Banner record);
//  修改 状态和日期
    int updateByPrimaryKey(@Param("id") int id, @Param("status") int status);

//  批量删除
    int deleteByPrimaryKey(int[] ids);

//    =====接口方法
//    查询全部
        List<Banner> selectAll();


}