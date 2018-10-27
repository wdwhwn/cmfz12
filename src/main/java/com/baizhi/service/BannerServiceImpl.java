package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/23.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
@Autowired
private BannerDao bd;
    @Override
    public Map selectAllByPage(int page, int rows) {
        int start=(page-1)*rows;
        List<Banner> list= bd.selectAllByPage(start, rows);
        int count=bd.count();
        Map map=new HashMap();
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public void insert(Banner record) {
            bd.insert(record);
    }

    @Override
    public void updateByPrimaryKey(int id, int status) {
        bd.updateByPrimaryKey(id,status);
    }

    @Override
    public void deleteByPrimaryKey(int[] ids) {
        try {
            bd.deleteByPrimaryKey(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
