package com.mxgraph.server.biz.service.impl;

import com.mxgraph.server.biz.bean.DrawData;
import com.mxgraph.server.biz.dao.DrawDataDao;
import com.mxgraph.server.biz.service.DrawDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门负责人接口实现
 * @author coral
 */
@Slf4j
@Service
@Transactional
public class DrawDataServiceImpl implements DrawDataService {

    @Autowired
    private DrawDataDao drawDataDao;

    @Override
    public DrawDataDao getRepository() {
        return drawDataDao;
    }


    @Override
    public List<DrawData> findByOwnerId(String ownerId) {
        return drawDataDao.findByOwnerId(ownerId);
    }

    @Override
    public DrawData findByUuid(String uuid) {
        return drawDataDao.findByUuid(uuid);
    }
}