package com.mxgraph.service;

import com.mxgraph.base.DrawBaseService;
import com.mxgraph.bean.DrawData;
import com.mxgraph.bean.SearchVo;
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

    Page<DrawData> findByConfition(String ownerId, String key, SearchVo searchVo, Pageable pageable);
    /**
     * 获取拥有者笔记本
     * @param uuid
     * @return
     */
    DrawData findByUuid(String uuid);
}
