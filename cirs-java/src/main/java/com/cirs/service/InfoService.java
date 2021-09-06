package com.cirs.service;

import com.cirs.entity.Info;

import java.util.List;

public interface InfoService {
    /****** select start ******/
    // 查询所有信息
    List<Info> getAllInfo();
    // 通过条件模糊查询信息
    List<Info> findInfoByConditions(Integer infoId,String title,String username,Integer infoStatus);
    /****** select end ******/

    /****** update start ******/
    // 修改信息
    int updateInfo(Info info);
    // 修改信息审核状态
    int updateInfoStatus(int infoId,int infoStatus);
    /****** update end ******/

    /****** insert start ******/
    // 添加信息
    int addInfo(Info info);
    /****** insert end ******/

    /****** delete start ******/
    // 通过信息编号删除信息
    int deleteInfo(int InfoId);
    /****** delete end ******/
}
