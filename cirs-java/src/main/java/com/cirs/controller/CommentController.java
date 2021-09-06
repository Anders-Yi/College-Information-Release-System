package com.cirs.controller;

import com.cirs.entity.Comment;
import com.cirs.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="评论controller",tags={"评论操作接口"})
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("获取评论")
    @CrossOrigin
    @PostMapping("/findcommentbyinfoid")
    public List<Comment> findCommentByInfoId(int infoId){
        return commentService.findCommentByInfoId(infoId);
    }

    @ApiOperation("发表评论")
    @CrossOrigin
    @PostMapping("/pulishcomment")
    public String pulishcomment(Comment comment){
        System.out.println("===========>评论内容:"+comment.getContent());
        int row = commentService.addComment(comment);
        if(row == 1){
            return "pulish comment success";
        }
        return "pulish comment failure";
    }

    @ApiOperation("撤回评论")
    @CrossOrigin
    @PostMapping("/delecomment")
    public int deleComment(int cid){
        int row = commentService.deleComment(cid);
        return row;
    }
}
