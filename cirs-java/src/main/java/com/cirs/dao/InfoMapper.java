package com.cirs.dao;

import com.cirs.entity.Info;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InfoMapper {
    //查询所有校园信息
    List<Info> getAllInfo();
    //通过条件模糊查询校园信息
    List<Info> queryInfoByConditions(Integer infoId,String title,String username,Integer infoStatus);
    //发布校园信息
    int insertInfo(Info info);
    //修改校园信息
    int updateInfoByNidAndUid(Info info);
    //修改审核信息状态
    int updateInfoStatusByInfoId(Info info);
    //删除校园信息
    int deleInfo(int nid);
}
