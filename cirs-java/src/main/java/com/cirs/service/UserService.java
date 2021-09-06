package com.cirs.service;

import com.cirs.entity.User;

import java.util.List;

public interface UserService {
    /****** select start ******/
    //登录
    User login(int uid, String password);
    //查询所有用户
    List<User> queryAllUser();
    //通过所有学生用户
    List<User> queryStuUser();
    //通过用户编号、用户名、用户状态和角色查询用户
    List<User> queryUsers(int uid,String username,int ustatus,int role);
//    int uid,String username,int ustatus,int role
//    Integer uid,String username,Integer ustatus,Integer role
    //通过用户编号查询
    User queryUserByUid(int uid);

    /****** select end ******/

    /****** insert start ******/
    //注册
    int register(User user);
    /****** insert end ******/

    /****** update start ******/
    //修改用户基本信息
    int updateUserBaseInfo(User user);
    //修改用户信息
    int updateUserInfo(User user);
    //修改密码
    int updatePasswordByUid(User user);
    //重置密码
    int resetPassword(int uid);
    //设置用户权限
    int setUserPermissions(int uid,String position);
    //修改用户状态
    int updateUserStatus(int uid,int ustatus);
    /****** update end ******/

    /****** insert start ******/
    //删除用户
    int deleUserByUid(int uid);
    /****** insert end ******/
}
