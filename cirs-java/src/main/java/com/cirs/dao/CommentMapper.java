package com.cirs.dao;

import com.cirs.entity.Comment;
import com.cirs.entity.Info;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //通过信息编号查询相关有评论
    List<Comment> findCommentByInfoId(int infoId);
    //添加评论
    int insertComment(Comment comment);
    //撤回评论
    int deleComment(int cid);
}
