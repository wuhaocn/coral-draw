package com.mxgraph.dao;


import com.mxgraph.base.DrawBaseDao;
import com.mxgraph.bean.DrawUser;

import java.util.List;

/**
 * @author coral
 */
// 自定义接口 不会创建接口的实例 必须加此注解
public interface DrawUserDao extends DrawBaseDao<DrawUser,String> {
    /**
     * 采用Uid获取
     * @param uid
     * @return
     */
    DrawUser findByUid(String uid);
    /**
     * 采用User用户名获取
     * @param user
     * @return
     */
    DrawUser findByUser(String user);
}
