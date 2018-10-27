package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * Created by wdwhwn on 2018/10/26.
 */
@Service
@Transactional
public class ChapterServiceImpl implements  ChapterService{
    @Autowired
    private ChapterDao cd;

    @Override
    public void insert(Chapter record, MultipartFile multipartFile, HttpServletRequest request) {
        //        设定存储文件夹
//        生成uuid，并形成一个不重复的和上传文件后缀一致的名字
//         将文件传到项目的文件夹中，并且设定其名字为新生成的名字
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file=new File(realPath+"/audio");
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
        String uuid=UUID.randomUUID().toString();
        String[] split = uuid.split("-");
        StringBuffer s1=new StringBuffer();
        for (String s2 : split) {
            s1.append(s2);
        }
        record.setId(s1.toString());
        record.setUrl(newName);
        //        获取音频的大小
        long size = multipartFile.getSize();
//        计算多少兆
        String s3=size/(1024*1024)+"";
        String s4=(size%(1024*1024)+"").substring(0,2);
        String size1=s3+"."+s4+"Mb";
        record.setSize(size1);
//        获取音频的时长
        File source=new File(realPath+"/audio/"+newName);
        Encoder encoder=new Encoder();
        try {
//            获取视频的信息
            MultimediaInfo m = encoder.getInfo(source);
            long ls=m.getDuration();
//            获取多少s
            long second = ls / 1000;
            System.out.println(second);
//          小时
            int h= (int) (second/3600);
            String h1;
            if(h%10==h){
                h1=0+""+h;
            }else{
                h1=h+"";
            }
//          分钟
            int min=(int) (second%3600/60);
            String min1;
            if(min%10==min){
                min1=0+""+min;
            }else{
                min1=min+"";
            }
//           秒
            int sec= (int) (second%3600%60);
            String sec1;
            if(sec%10==sec){
                sec1=0+""+sec;
            }else{
                sec1=sec+"";
            }
//            拼接时间
            String s2 = h1 + ":" + min1 + ":" + sec1;
            record.setDuration(s2);
        } catch (EncoderException e) {
            e.printStackTrace();
        }

        /*int id=Integer.parseInt(uuid);
        System.out.println(id);
        record.setId(id);*/
        cd.insert(record);
    }
}
