package com.mxgraph.control;

import com.mxgraph.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 */
@RestController
@RequestMapping("/file")
public class FileControl {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * http://127.0.0.1:8081/index.html?offline=1&local=1&flid=1&spin=1
     *
     * @param flid
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/get/{flid}")
    @ResponseBody
    public void get(@PathVariable String flid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = "<mxfile modified=\"2019-01-24T12:07:23.169Z\" host=\"127.0.0.1\" agent=\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36\" etag=\"fZa_9_pMrHquUjE9i1OP\" version=\"10.0.39\" type=\"device\"><diagram id=\"FO2v3a15tLzoUDuactzV\" name=\"Page-1\">jZJNb8MgDIZ/TY6TmtCu7bVZu+3QUw7Ljiy4AYkERMiS7NePDJMPVZV2QfaDMfC+jkha9a+Gan5VDGSUbFgfkZcoSY7bvVtHMHiw3R09KI1gHsUzyMQPINwgbQWDZlVolZJW6DUsVF1DYVeMGqO6ddlNyfWtmpZwB7KCynv6IZjlnh6S/czfQJQ83Bw/4/8qGorxJw2nTHULRM4RSY1S1kdVn4IctQu6+HOXB7vTwwzU9j8Hdp8qGd5zZuiXoHmRH/S1fcIu31S2+GF8rB2CAq6LE9slp44LC5mmxbjTObsd47aSLotdSBvtHbiJHtylJ+wNxkL/8NHxJIUbIVAVWDO4EjxAgno4PvEW825hBiK+8CEwivaXU+dZIRegSCGdzfjbW0w0Of8C</diagram></mxfile>";
        LOGGER.info("get File:{}", body);
        response.getOutputStream().write(body.getBytes());
        response.setStatus(200);
    }


    @PostMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = HttpUtil.getBodyString(request);
        System.out.println(body);
        LOGGER.info("save File:{}", body);
        response.setStatus(200);
    }
}
