package com.mxgraph.online;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/16.
 */
@RestController
@RequestMapping("/")
public class IndexControl {
    @GetMapping
    @ResponseBody
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(301);
        response.setHeader("Location", "/index.html?offline=1");
    }
}
