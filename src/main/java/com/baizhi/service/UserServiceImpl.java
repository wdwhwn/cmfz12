package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/28.
 */
@Service
@Transactional
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserDao ud;

    @Override
    public Map selectAll(int page, int rows) {
        int start=(page-1)*rows;
        List<User> users = ud.selectAll(start,rows);
        int count=ud.count();
        Map map=new HashMap();
        map.put("rows",users);
        map.put("total",count);
        return map;

    }
}
