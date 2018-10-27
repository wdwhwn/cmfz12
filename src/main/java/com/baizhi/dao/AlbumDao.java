package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    int deleteByPrimaryKey(Integer id);



    Album selectByPrimaryKey(Integer id);

    List<Album> selectAll();

    int updateByPrimaryKey(Album record);
//  专辑和章节表左连接查询
    List<Album> selectAll1();
//    分页查询的条数
    int count();

//   查询专辑  通过专辑id
     Album selectOne(int id);

//     查询专辑 通过二级类别
    Album selectOne1(int id);
//    添加专辑
    void insert(Album album);

}