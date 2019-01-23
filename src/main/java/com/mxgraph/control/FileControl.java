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
 * Created by Administrator on 2019/1/16.
 */
@RestController
@RequestMapping("/file")
public class FileControl {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get")
    @ResponseBody
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = "<mxfile modified=\"2019-01-23T15:32:39.986Z\" host=\"127.0.0.1\" agent=\"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:64.0) Gecko/20100101 Firefox/64.0\" etag=\"EmoZrw3h0lwMw6FsZoUw\" version=\"10.0.39\" type=\"device\"><diagram id=\"SAONUdvtZ16R1FJdLK8N\" name=\"Page-1\">rZRdT4MwFIZ/DZcmfLnNS8em3sybmZh4s1R6RpsVSkoR8Nd7sO0AcYlLvCHtc/rF8xa8KMnbR0VKtpMUhBf6tPWijReGy9UCnz3oDIh834BMcWpQMIA9/wQL3bCaU6gmA7WUQvNyClNZFJDqCSNKyWY67CjFdNeSZDAD+5SIOX3lVDNDV+Fy4E/AM+Z2DhZ3ppITN9i+ScUIlc0IRVsvSpSU2rTyNgHRu3NezLyHC9XzwRQU+i8TFiLedc9cHWr/ncJbcApvDzd2lQ8iavvC9rC6cwZwFZSNnXXDuIZ9SdK+0mDcyJjOBfYCbJKqNAkceQu46dquDUpDe/HQwVkFXiGQOWjV4RA3YWnt2esTOJvNKAyL2CgHx4iNPzuvPBjChpV0hbBwJuwFWj2Tpns4sVNpJU+QSCEVkkIWvdEjF+IHIoJnBXZTNATI170/jhfy3hZyTqm4FIeSdUF7+Rv/fwKI4x8BxPMA4l8CCK8PALvD1/BdG/1Sou0X</diagram></mxfile>";
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
