package com.baizhi.service;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/10/28.
 */
public interface UserService  {
    public Map selectAll(int page, int rows);
    public List<User> selectAll1();
    void insert(List<User> list);

    //    通过密码 查询是否登录成功
    Map login(String phone, String password,String code);
//    用户注册
    Map register(String phone,String password);
//    用户修改
    boolean updateByPrimaryKey(User record);
//   通过 查询用户
    User selectByPrimaryKey(Integer id);
    //    查询全部
    public List<User> selectAll2(int id);
//验证手机号是否存在
    boolean selectByPhone(String phoneNum);

}
