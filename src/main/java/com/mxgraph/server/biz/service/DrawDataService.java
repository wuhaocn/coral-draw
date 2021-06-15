package com.mxgraph.server.biz.service;

import com.mxgraph.server.biz.base.DrawBaseService;
import com.mxgraph.server.biz.bean.DrawData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DrawDataService extends DrawBaseService<DrawData,String> {

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

    /**
     * findByConfition
     *
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    public Page<DrawData> findByConfition(Integer type, String key, SearchVo searchVo, Pageable pageable);
}
