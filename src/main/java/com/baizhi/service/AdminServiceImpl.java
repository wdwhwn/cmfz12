package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wdwhwn on 2018/10/23.
 */
@Service
@Transactional
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminDao ad;
    @Autowired
    private BannerDao bd;
    @Autowired
     private MenuService ms;
    int a=895469;
    Admin a1=new Admin(2,"12344","513");
    @Override
    public boolean login(Admin admin) {
        System.out.println(123234);
        Admin admin1 = ad.selectOne(admin);
        System.out.println("123123");
        boolean f=false;
        if(admin1!=null){
            f=true;
        }
        return f;
    }


}
