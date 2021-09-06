package com.cirs.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uId;            //用户编号
    private String username;    //用户姓名
    private String password;    //用户密码
    private int sex;            //性别：0，男；1，女
    private String birthday;    //出生日期
    private int departId;       //部门编号
    private int clazzId;        //学院班级号
    private int role;           //角色：0，管理员；1，教职工；2，学生
    private String position;    //职位
    private int uStatus;        //用户状态：0，未登录；1，登录
}
