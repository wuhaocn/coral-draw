package com.mxgraph.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 2P on 18-11-20.
 */
@WebFilter(urlPatterns={"/*"})
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("this is a corsFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("Origin");
        String allowHeaders = request.getHeader("Access-Control-Request-Headers");
        if (request.getMethod().equals("OPTIONS")) {
            if (origin != null) {
                response.addHeader("Access-Control-Allow-Origin", origin);
            } else {
                response.addHeader("Access-Control-Allow-Origin", "*");
            }
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Method", "DELETE, GET, POST, PATCH, PUT");
            if (allowHeaders != null) {
                response.addHeader("Access-Control-Allow-Headers", allowHeaders);
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        } else {
            if (origin != null) {
                response.addHeader("Access-Control-Allow-Origin", origin);
            } else {
                response.addHeader("Access-Control-Allow-Origin", "*");
            }
            response.addHeader("Access-Control-Allow-Credentials", "true");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}