package com.mxgraph.control;

import com.alibaba.fastjson.JSONObject;
import com.mxgraph.bean.DrawData;
import com.mxgraph.bean.DrawUser;
import com.mxgraph.config.CoralConfig;
import com.mxgraph.service.DrawDataService;
import com.mxgraph.service.DrawUserService;
import com.mxgraph.utils.HttpUtil;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 *
 *
 */
@RestController
@RequestMapping("/user")
public class UserControl {

    @Autowired
    private DrawUserService drawUserService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * http://127.0.0.1:8081/index.html?offline=1&local=1&flid=1&spin=1
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/register")
    public void get(DrawUser drawUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        if (drawUser == null){
            response.setHeader("Location", "/admin/index/register.html");
        }
        DrawUser drawUserSelect = drawUserService.findByUser(drawUser.getUser());
        if (drawUserSelect != null){
            response.setHeader("Location", "/admin/index/register.html");
        } else {
            drawUser.setName(drawUser.getUser());
            drawUserService.save(drawUser);
            response.setHeader("Location", "/admin/index/login.html");

        }
    }


    @PostMapping("/check")
    public void save(DrawUser drawUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        if (drawUser == null){
            response.setHeader("Location", "/admin/index/login.html");
            return;
        }
        //为空生成id
        //获取内容
        drawUser  = drawUserService.checkByUserPass(drawUser.getUser(), drawUser.getPass());
        if (drawUser == null){
            response.setHeader("Location", "/admin/index/login.html");
            return;
        }
        request.getSession().setAttribute("user", drawUser);
        response.setHeader("Location", "/admin/index/index.html");
    }

}
