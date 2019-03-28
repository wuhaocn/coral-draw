package com.mxgraph.service;


import com.mxgraph.base.KbsBaseDao;
import com.mxgraph.bean.DrawData;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author coral
 */
// 自定义接口 不会创建接口的实例 必须加此注解
@NoRepositoryBean
public interface DrawDataDao extends KbsBaseDao<DrawData,String> {
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
