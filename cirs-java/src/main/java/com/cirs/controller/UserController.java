package com.cirs.controller;

import com.cirs.entity.User;
import com.cirs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value="用户controller",tags={"用户操作接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录操作")
    @CrossOrigin
    @PostMapping("/login")
    public User login(int uid, String password){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userService.login(uid, password);
        if(user != null){
            //用户存在，导航栏某部分换成登录用户名
            //Java后端向Vue前端发出相对应的响应
            int row = userService.updateUserStatus(user.getUId(),1);
            if(row == 1){
                user.setUStatus(1);
                return user;
            }
            return null;
        }else{
            //用户不存在，返回主页
            //Java后端向Vue前端发出相对应的响应
            return null;
        }
    }

    @ApiOperation("查询所有用户操作")
    @CrossOrigin
    @PostMapping("/queryalluser")
    public List<User> getUserlist(){
        List<User> userlist = new ArrayList<User>();
        userlist = userService.queryAllUser();
        return userlist;
    }

    @ApiOperation("查询所有学生用户操作")
    @CrossOrigin
    @PostMapping("/queryallstu")
    public List<User> getStulist(){
        List<User> userlist = new ArrayList<User>();
        userlist = userService.queryStuUser();
        return userlist;
    }

    @ApiOperation("通过用户编号查询用户")
    @CrossOrigin
    @PostMapping("/queryuserbyuid")
    public User getUsers(int uid){
        User user = userService.queryUserByUid(uid);
        return user;
    }

    @ApiOperation("通过用户编号、用户名、用户状态和角色查询用户")
    @CrossOrigin
    @PostMapping("/queryusers")
    public List<User> getUserslis(Integer uid,String username,Integer ustatus,Integer role){
        List<User> userlist = new ArrayList<User>();
        userlist = userService.queryUsers(uid,username,ustatus,role);
        return userlist;
    }

    @ApiOperation("学生注册操作")
    @CrossOrigin
    @PostMapping("/sturegister")
    public String sturegister(User user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setRole(2);
        user.setUStatus(0);

        int row = userService.register(user);
        if(row == 1){
            return "sturegister success";
        }else {
            return "sturegister failure";
        }
    }

    @ApiOperation("用户注册操作")
    @CrossOrigin
    @PostMapping("/register")
    public String register(User user){
        System.out.println("uId=" + user.getUId());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int row = userService.register(user);
        if(row == 1){
            return "register success";
        }else {
            return "register failure";
        }
    }

    @ApiOperation("修改用户基础信息")
    @CrossOrigin
    @PostMapping("/modifyuserbaseinfo")
    public String modifyUserBaseInfo(User user){
        int row = userService.updateUserBaseInfo(user);
        if(row == 1){
            return "modify userbaseinfo success";
        }else {
            return "modify userbaseinfo failure";
        }
    }

    @ApiOperation("修改用户信息")
    @CrossOrigin
    @PostMapping("/modifyuserinfo")
    public String modifyUserInfo(User user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int row = userService.updateUserInfo(user);
        if(row == 1){
            return "modify userinfo success";
        }else {
            return "modify userinfo failure";
        }
    }

    @ApiOperation("修改用户密码")
    @CrossOrigin
    @PostMapping("/modifyuserpassword")
    public String modifyUserPassword(int uid,String password){
        User user = new User();
        user.setUId(uid);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        int row = userService.updatePasswordByUid(user);
        if(row == 1){
            return "modify password success";
        }else{
            return "modify password failure";
        }
    }

    @ApiOperation("重置用户密码")
    @CrossOrigin
    @PostMapping("/resetuserpassword")
    public String resetUserPassword(int uid){
        int row = userService.resetPassword(uid);
        if(row == 1){
            return "reset userpassword success";
        }else {
            return "reset userpassword failure";
        }
    }

    @ApiOperation("用户权限管理")
    @CrossOrigin
    @PostMapping("/setuserrightsmanagement")
    public String setUserRightsManagement(int uid,String position){
        int row = userService.setUserPermissions(uid,position);
        if(row == 1){
            return "set userrights success";
        }else {
            return "set userrights failure";
        }
    }

    @ApiOperation("退出登录操作")
    @CrossOrigin
    @PostMapping("/logout")
    public String logout(int uid){
        int ustatus = 0;
        int row = userService.updateUserStatus(uid,ustatus);
        if(row == 1){
            return "logout success";
        }
        return "logout fail";
    }

    @ApiOperation("注销用户")
    @CrossOrigin
    @PostMapping("/deleuser")
    public String cancellateUser(int uid){
        int row = userService.deleUserByUid(uid);
        if(row == 1){
            return "cancellate user success";
        }else {
            return "cancellate user failure";
        }
    }
}
