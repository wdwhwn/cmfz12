package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/23.
 */
public interface BannerService {
//    分页查询
    Map selectAllByPage(int page, int rows);

    //    添加
   void   insert(Banner record);

    //   修改
    void updateByPrimaryKey(int id, int status);
//    批量删除
    void deleteByPrimaryKey(int[] ids);

}
