package com.mxgraph.server.utils;

import com.mxgraph.server.biz.bean.DrawUser;
import com.mxgraph.server.config.CoralConfig;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    public static DrawUser getCurUser(HttpServletRequest servletRequest){
        Object object = servletRequest.getSession().getAttribute(CoralConfig.SESSION_KEY);

        if (object == null){
            DrawUser drawUser = new DrawUser();
            drawUser.setUser(CoralConfig.OWNERID);
            drawUser.setUid(CoralConfig.OWNERID);
            return drawUser;
        }
        return (DrawUser) object;
    }

    public static String getCurUid(HttpServletRequest servletRequest){
        Object object = servletRequest.getSession().getAttribute(CoralConfig.SESSION_KEY);
        DrawUser drawUser = null;
        if (object == null){
            drawUser = new DrawUser();
            drawUser.setUser(CoralConfig.OWNERID);
            drawUser.setUid(CoralConfig.OWNERID);
        } else {
            drawUser = (DrawUser) object;
        }
        return drawUser.getUid();
    }
}
