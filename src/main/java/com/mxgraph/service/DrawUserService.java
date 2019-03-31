package com.mxgraph.service;

import com.mxgraph.base.DrawBaseService;
import com.mxgraph.bean.DrawUser;

public interface DrawUserService extends DrawBaseService<DrawUser,String> {

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

    /**
     * 采用User用户名获取
     * @param user
     * @return
     */
    DrawUser checkByUserPass(String user, String pass);

    /**
     * 采用User用户名获取
     * @param user
     * @return
     */
    DrawUser checkByUserToken(String user, String pass);
}
