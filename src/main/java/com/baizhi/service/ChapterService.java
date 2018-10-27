package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wdwhwn on 2018/10/26.
 */
public interface ChapterService {
    //  添加
    void insert(Chapter record, MultipartFile multipartFile, HttpServletRequest request);
}
