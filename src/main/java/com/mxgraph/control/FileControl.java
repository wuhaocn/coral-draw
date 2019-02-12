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

        String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<mxfile modified=\"2019-02-12T07:07:12.236Z\" host=\"127.0.0.1\" agent=\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36\" etag=\"Hxi9cdVr-Ee_9CzLO883\" version=\"10.0.39\" type=\"browser\">\n" +
                "  <diagram id=\"FO2v3a15tLzoUDuactzV\" name=\"Page-1\">zZZNc6owFIZ/DUtngIjYpYC1tTr9cKZXV50UIqQNBEMo0F/feAkKYqcuHHRF8p6TD57zJhMF2GE+YTAO5tRDRNFVL1eAo+i61geq+GyVolRMDZSCz7Ank/bCAn8jKcpxfoo9lDQSOaWE47gpujSKkMsbGmSMZs20NSXNVWPoo5awcCFpq/+wx4NSHermXr9D2A+qlbXBTRkJYZUs/yQJoEezmgTGCrAZpbxshbmNyBZexaUcd/tLdLcxhiJ+ygBjRfXifukx+I7h0l0O43nak7N8QZLKH5ab5UVFQMwiYIuOlQWYo0UM3W0kE/UWWsBDInqaaMIkLiuwxjkSi1rtHVbLIcZRXpPkjieIhoizQqTIKKjoFZWhZD+rFUNKQa0OlQZl+f3dzHtCoiEhHQcGXcdLljxntjFZvVjTF/sBXD0w7RDY8MLA9L+BiZMRb5sxoy5Kkr/BvUP302c0jbzHlBMcIamfg5/R5Ge08e0Q1/kNzsDPJ9YmJTOWptnrjD4YheOOrt5wffPAcGp3hntC69k6T5JglhM8tkbBZj49Bdg1GU5Xm/x2TurCcUcBnnBiL+q4wxuuQ8MNTccf97+nn4NVz07UCBXOxymGuwAlQzvwldkdJu3WeWb3z5iYm9eR8THvvRk3XVxkLTpHGP5uK9AE1j9yEM8ETHT3777/sdrrGYx/AA==</diagram>\n" +
                "</mxfile>\n";
        LOGGER.info("get File:{}", body);
        response.getOutputStream().write(body.getBytes());
        response.setHeader("title", System.currentTimeMillis() + ".xml");
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
