package com.baizhi.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wdwhwn on 2018/10/25.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
@Autowired
private AlbumDao ad;
    @Override
    public List<Album> selectAll1() {

        List<Album> albums = ad.selectAll1();


        return albums;
    }

    @Override
    public Album selectOne(int id) {
        Album album = ad.selectOne(id);
        return album;
    }

    @Override
    public Album selectOne1(int id) {
        Album album = ad.selectOne1(id);
        return album;

    }

    @Override
    public void insert(MultipartFile multipartFile, Album album, HttpServletRequest request) {
        //        设定存储文件夹
//        生成uuid，并形成一个不重复的和上传文件后缀一致的名字
//         将文件传到项目的文件夹中，并且设定其名字为新生成的名字
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file=new File(realPath+"/upload");
        if(!file.exists()){
            file.mkdir();
        }

        String s = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String newName=s+"."+extension;

        try {
            multipartFile.transferTo(new File(file,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setCoverimg(newName);


        ad.insert(album);
    }


}
