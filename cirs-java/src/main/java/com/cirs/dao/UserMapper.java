package com.cirs.dao;


import com.cirs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //查询所有用户
    List<User> queryAllUser();
    //查询所有学生用户
    List<User> queryStuUser();
    //通过用户编号、用户名、用户状态和角色中任意个条件查询用户
    List<User> queryUsers(User user);
    //通过用户编号查询用户
    User queryUserByUid(int uid);

    //登录
    User queryUserByUidAndPassword(User user);
    //注册
    int addUser(User user);
    //修改用户基本信息
    int updateUserBaseInfoByUid(User user);
    //修改用户信息
    int updateUserInfoByUid(User user);
    //修改密码
    int updateUserPasswordByUid(User user);
    //重置密码
    int initUserPasswordByUid(int uid);
    //管理权限
    int updateUserPositionByUid(User user);
    //修改用户状态
    int updateUserStatusByUid(User user);
    //删除用户
    int deleUserByUid(int uid);

}
