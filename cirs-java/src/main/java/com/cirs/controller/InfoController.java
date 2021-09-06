package com.cirs.controller;

import com.cirs.entity.Info;
import com.cirs.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value="信息controller",tags={"信息操作接口"})
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @ApiOperation("获取所有信息")
    @CrossOrigin
    @PostMapping("/findallinfo")
    public List<Info> sendinfo(){
        List<Info> infolist = new ArrayList<Info>();
        infolist = infoService.getAllInfo();
        return infolist;
    }

    @ApiOperation("通过条件模糊查询信息")
    @CrossOrigin
    @PostMapping("/findinfobyconditions")
    public List<Info> findInfoByConditions(Integer infoId,String title,String username,Integer infoStatus){
//        System.out.println("InfoController--findInfoByConditions====>title="+title+",username="+username+",infoStatus="+infoStatus);
        List<Info> infolist = new ArrayList<Info>();
        infolist = infoService.findInfoByConditions(infoId,title,username,infoStatus);
        return infolist;
    }

    @ApiOperation("发送信息")
    @CrossOrigin
    @PostMapping("/sendinfo")
    public int sendinfo(Info info){
        return infoService.addInfo(info);
    }

    @ApiOperation("审核信息")
    @CrossOrigin
    @PostMapping("/aduitinfo")
    public int aduitinfo(int infoId,int infoStatus){
        return infoService.updateInfoStatus(infoId,infoStatus);
    }

    @ApiOperation("修改信息")
    @CrossOrigin
    @PostMapping("/updateinfo")
    public int updateinfo(Info info){
        return infoService.updateInfo(info);
    }

    @ApiOperation("删除信息")
    @CrossOrigin
    @PostMapping("/deleinfobyinfoid")
    public int deleinfo(int infoId){
        return infoService.deleteInfo(infoId);
    }
}
