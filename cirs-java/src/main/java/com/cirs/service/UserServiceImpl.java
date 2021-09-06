package com.cirs.service;

import com.cirs.dao.UserMapper;
import com.cirs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")//这里的userService命名规范依据于你在UserController类中声明的service对象的变量名保持一致
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(int uid, String password) {
        User user = new User();
        user.setUId(uid);
        user.setPassword(password);
        User rs = userMapper.queryUserByUidAndPassword(user);
        return rs;
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public List<User> queryStuUser() {
        return userMapper.queryStuUser();
    }

    @Override
    public List<User> queryUsers(int uid,String username,int ustatus,int role) {
        User user = new User();
        user.setUId(uid);
        user.setUsername(username);
        user.setUStatus(ustatus);
        user.setRole(role);
        return userMapper.queryUsers(user);
    }

    @Override
    public User queryUserByUid(int uid) {
        return userMapper.queryUserByUid(uid);
    }

    @Override
    public int register(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserBaseInfo(User user) {
        return userMapper.updateUserBaseInfoByUid(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfoByUid(user);
    }

    @Override
    public int updatePasswordByUid(User user) {
        return userMapper.updateUserPasswordByUid(user);
    }

    @Override
    public int resetPassword(int uid) {
        return userMapper.initUserPasswordByUid(uid);
    }

    @Override
    public int setUserPermissions(int uid, String position) {
        User user = new User();
        user.setUId(uid);
        user.setPosition(position);
        return userMapper.updateUserPositionByUid(user);
    }

    @Override
    public int updateUserStatus(int uid, int ustatus) {
        User user = new User();
        user.setUId(uid);
        user.setUStatus(ustatus);
        return userMapper.updateUserStatusByUid(user);
    }

    @Override
    public int deleUserByUid(int uid) {
        return userMapper.deleUserByUid(uid);
    }

}
