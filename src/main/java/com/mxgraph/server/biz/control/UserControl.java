package com.mxgraph.server.biz.control;

import com.mxgraph.server.biz.bean.DrawResult;
import com.mxgraph.server.biz.bean.DrawUser;
import com.mxgraph.server.biz.service.DrawUserService;
import com.mxgraph.server.config.CoralConfig;
import com.mxgraph.server.utils.UidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

            drawUser.setUid(UidUtils.getUid(drawUser.getUser()));
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
        request.getSession().setAttribute(CoralConfig.SESSION_KEY, drawUser);
        response.setHeader("Location", "/admin/index/index.html");
    }

    @GetMapping("/unregister")
    public void unregister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        request.getSession().removeAttribute(CoralConfig.SESSION_KEY);
        response.setHeader("Location", "/admin/index/login.html");
    }


    @GetMapping("/get")
    @ResponseBody
    public DrawResult save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object drawUser = request.getSession().getAttribute(CoralConfig.SESSION_KEY);
        DrawResult<DrawUser> drawResult = null;
        if (drawUser == null){
            drawResult = new DrawResult(404, "Not Found");
        } else {
            drawResult = new DrawResult(0, drawUser);
        }
        return drawResult;
    }

}
