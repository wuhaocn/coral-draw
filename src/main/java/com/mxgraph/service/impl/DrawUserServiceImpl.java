package com.mxgraph.service.impl;

import com.mxgraph.bean.DrawUser;
import com.mxgraph.dao.DrawUserDao;
import com.mxgraph.service.DrawUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户实现
 * @author coral
 */
@Slf4j
@Service
@Transactional
public class DrawUserServiceImpl implements DrawUserService {

    @Autowired
    private DrawUserDao drawDataDao;

    @Override
    public DrawUserDao getRepository() {
        return drawDataDao;
    }



    @Override
    public DrawUser findByUid(String uid) {
        return drawDataDao.findByUid(uid);
    }

    @Override
    public DrawUser findByUser(String user) {
        return drawDataDao.findByUser(user);
    }

    @Override
    public DrawUser checkByUserPass(String user, String pass) {
        DrawUser drawUser = drawDataDao.findByUser(user);
        if (drawUser != null){
            if(!pass.equals(drawUser.getPass())){
                drawUser = null;
            }
        }
        return drawUser;
    }

    @Override
    public DrawUser checkByUserToken(String user, String token) {
        DrawUser drawUser = drawDataDao.findByUser(user);
        if (drawUser != null){
            if(!token.equals(drawUser.getToken())){
                drawUser = null;
            }
        }
        return null;
    }
}