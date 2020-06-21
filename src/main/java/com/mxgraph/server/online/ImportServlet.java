package com.mxgraph.server.online;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@RequestMapping("/import")
public class ImportServlet{

    @PostMapping
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRemoteHost().endsWith("atlassian.net")) {

        } else {
            System.out.println(req);
        }
    }


}
