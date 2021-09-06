package com.cirs.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int cId;            //评论编号
    private String content;    //评论内容
    private int uId;            //评论的用户编号
    private String username;    //用户名
    private int infoId;         //信息编号
}
