package com.cirs.service;

import com.cirs.dao.CommentMapper;
import com.cirs.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentByInfoId(int infoId) {
        return commentMapper.findCommentByInfoId(infoId);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int deleComment(int cid) {
        return commentMapper.deleComment(cid);
    }
}
