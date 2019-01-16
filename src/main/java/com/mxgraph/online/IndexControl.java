package com.mxgraph.online;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/1/16.
 */
@RestController
@RequestMapping("/")
public class IndexControl {
    @GetMapping
    public String index(){
        return "index";
    }
}
