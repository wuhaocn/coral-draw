package com.mxgraph.control;

import com.mxgraph.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/16.
 */
@RestController
@RequestMapping("/file")
public class FileControl {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get")
    @ResponseBody
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = "111";
        LOGGER.info("get File", body);
        response.getOutputStream().write(body.getBytes());
        response.setStatus(200);
    }


    @GetMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = HttpUtil.getBodyString(request);
        LOGGER.info("save File", body);
        response.setStatus(200);
    }
}
