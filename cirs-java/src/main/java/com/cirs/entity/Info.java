package com.cirs.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private int infoId;         //信息编号
    private String infoTitle;   //信息标题
    private String infoContent; //信息内容
    private String releaseTime; //发布时间
    private int uId;            //用户编号
    private String username;    //用户名
    private int infoStatus;     //审核状态：0，初始化；1，通过；2，未通过
}
