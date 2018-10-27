package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/25.
 */
public interface AlbumService {
    List<Album> selectAll1();
    //   查询专辑  通过专辑id
    Album selectOne(int id);

    //     查询专辑 通过二级类别
    Album selectOne1(int id);
//    添加
    void insert(MultipartFile multipartFile, Album album, HttpServletRequest request);
}
