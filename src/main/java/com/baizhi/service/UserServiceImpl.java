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

    @Override
    public List<User> selectAll1() {
        List<User> users = ud.selectAll1();
        return users;
    }

    @Override
    public void insert(List<User> list) {
            ud.insert1(list);
    }

    @Override
    public Map login(String phone, String password,String code) {
        Map map=new HashMap();
        User user=null;
        if(password !=null){
         user=ud.selectOne(phone,password);

        }
        if(code !=null){
             user = ud.selectOne1(phone, code);
        }

        if(user !=null) {
            map.put("password",user.getPassword());
            map.put("farmington",user.getDharmaName());
            map.put("uid",user.getId());
            map.put("nickname",user.getName());
            map.put("gender",user.getSex());
            map.put("photo",user.getPhonenum());
            map.put("location",user.getLocation());
            map.put("guruId",user.getGuruId());
            map.put("province",user.getProvince());
            map.put("city",user.getCity());
            map.put("description",user.getSign());
            map.put("phone",user.getPhonenum());
            return map;
        }
        map.put("error","-200");
        map.put("errmsg","密码错误");
        return map;
    }

    @Override
    public Map register(String phone, String password) {
       Map map=new HashMap();
        ud.insert2(phone,password);
        int i = ud.selectId(phone, password);
        map.put("password",password);
        map.put("uid",i);
        map.put("phone",phone);


        return map;
    }

    @Override
    public boolean updateByPrimaryKey(User record) {
        Map map=new HashMap();
        try {
            ud.updateByPrimaryKey(record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        User user = ud.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> selectAll2(int id) {
        List<User> users = ud.selectAll2(id);
        return users;
    }

    @Override
    public boolean selectByPhone(String phoneNum) {
        User user = ud.selectByPhone(phoneNum);
        if(user == null){
            return false;
        }
        return true;
    }


}
