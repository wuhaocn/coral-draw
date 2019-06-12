/**
 * $Id: ErrorServlet.java,v 1.6 2014/02/21 12:01:30 gaudenz Exp $
 * Copyright (c) 2014, JGraph Ltd
 */
package com.mxgraph.online;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class OpenServlet
 */
@RequestMapping("/app")
public class AppShortcutServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppShortcutServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @GetMapping
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Location", "index.html?offline=1");
        response.setStatus(HttpServletResponse.SC_FOUND);
    }

}
