package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);






//    分页查询全部
    List<User> selectAll(@Param("start") int start,@Param("rows") int rows);
//    统计数量
    int count();
//    查询全部
    List<User> selectAll1();
//    批量添加
    void insert1(List<User> list);
//    echarts  人数统计
//    一周内
    int count1();
//      二周内
    int  count2();
//    三周内
    int  count3();
//    接口的方法
    int selectById(int uid);

//    通过密码 查询是否登录成功
    User selectOne(@Param("phone") String phone,@Param("password") String password);
//    通过加密方式 查询是否登录成功
    User selectOne1(@Param("phone") String phone,@Param("salt") String salt);
//        注册
    void insert2(@Param("phone")String phone,@Param("password")String password);
//    查询用户id
    int selectId(@Param("phone")String phone,@Param("password")String password);
//    修改
    void updateByPrimaryKey(User record);
//    查询用户
User selectByPrimaryKey(Integer id);
//    查询  5个金刚道友
    List<User> selectAll2(int id);
//    验证手机号存在
    User selectByPhone(String phoneNum);
}
