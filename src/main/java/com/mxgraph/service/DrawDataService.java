package com.mxgraph.service;

import com.mxgraph.base.KbsBaseService;
import com.mxgraph.bean.DrawData;

import java.util.List;

public interface DrawDataService extends KbsBaseService<DrawData,String> {

    /**
     * 获取拥有者笔记本
     * @param ownerId
     * @return
     */
    List<DrawData> findByOwnerId(String ownerId);
    /**
     * 获取拥有者笔记本
     * @param uuid
     * @return
     */
    DrawData findByUuid(String uuid);
}
