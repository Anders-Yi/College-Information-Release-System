package com.cirs.service;

import com.cirs.entity.Comment;

import java.util.List;

public interface CommentService {
    /****** insert start ******/
    // 通过信息编号查询相关有评论
    List<Comment> findCommentByInfoId(int infoId);
    /****** insert end ******/

    /****** insert start ******/
    //添加评论
    int addComment(Comment comment);
    /****** insert end ******/

    /****** delete start ******/
    //撤回或删除评论
    int deleComment(int cid);
    /****** delete end ******/
}
