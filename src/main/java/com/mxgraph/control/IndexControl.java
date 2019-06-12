package com.mxgraph.control;

import com.mxgraph.config.CoralConfig;
import com.mxgraph.utils.SessionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2019/1/16.
 */
@RestController
@RequestMapping("/")
public class IndexControl {
    @GetMapping("")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        response.setHeader("Location", "/admin/index/index.html");
    }

    @GetMapping("draw")
    @ResponseBody
    public void draw(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = UUID.randomUUID().toString();
        response.setStatus(302);
        StringBuilder sb = new StringBuilder();
        sb.append("/index.html?offline=1&ownerId=");
        sb.append(SessionUtils.getCurUid(request));
        sb.append("&uuid=");
        sb.append(uuid);
        response.setHeader("Location", sb.toString());
    }

    @GetMapping("admin")
    @ResponseBody
    public void admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(302);
        if (request.getSession().getAttribute(CoralConfig.SESSION_KEY) == null){
            response.setHeader("Location", "/admin/index/login.html");
        }
        response.setHeader("Location", "/admin/index/index.html");
    }
}
