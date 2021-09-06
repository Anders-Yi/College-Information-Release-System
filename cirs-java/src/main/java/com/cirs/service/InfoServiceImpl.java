package com.cirs.service;

import com.cirs.dao.InfoMapper;
import com.cirs.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("infoService")
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public List<Info> getAllInfo() {
        return infoMapper.getAllInfo();
    }

    @Override
    public List<Info> findInfoByConditions(Integer infoId,String title, String username,Integer infoStatus) {
        return infoMapper.queryInfoByConditions(infoId,title,username,infoStatus);
    }

    @Override
    public int addInfo(Info info) {
        return infoMapper.insertInfo(info);
    }

    @Override
    public int updateInfo(Info news) {
        return infoMapper.updateInfoByNidAndUid(news);
    }

    @Override
    public int updateInfoStatus(int infoId, int infoStatus) {
        Info info = new Info();
        info.setInfoId(infoId);
        info.setInfoStatus(infoStatus);
        return infoMapper.updateInfoStatusByInfoId(info);
    }

    @Override
    public int deleteInfo(int nid) {
        return infoMapper.deleInfo(nid);
    }
}
