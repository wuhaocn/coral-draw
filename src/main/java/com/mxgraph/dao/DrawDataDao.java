package com.mxgraph.dao;


import com.mxgraph.base.DrawBaseDao;
import com.mxgraph.bean.DrawData;

import java.util.List;

/**
 * @author coral
 */
// 自定义接口 不会创建接口的实例 必须加此注解
public interface DrawDataDao extends DrawBaseDao<DrawData,String> {
    /**
     * 采用Uid获取
     * @param ownerId
     * @return
     */
    List<DrawData> findByOwnerId(String ownerId);
    /**
     * 采用用户名获取
     * @param uuid
     * @return
     */
    DrawData findByUuid(String uuid);
}
