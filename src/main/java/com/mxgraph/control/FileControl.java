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
        String body = "<mxfile modified=\"2019-01-31T06:10:13.689Z\" host=\"127.0.0.1\" agent=\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36\" etag=\"VcN0mstEjZjZBO5BYMyy\" version=\"10.0.39\" type=\"browser\"><diagram id=\"FO2v3a15tLzoUDuactzV\" name=\"Page-1\">zZZbT4MwFIB/DY8mA2Sbj8LmvGzRuES3x9qe0cZCsRQBf72dlA1kxiVbpi+k/U6v3zkELDeIiolECZ0JAtxyeqSw3JHlOIOLnn6uQVkBd+hVIJSMVMjegjn7AAPNvDBjBNLWQCUEVyxpQyziGLBqMSSlyNvDVoK3d01QCB0wx4h36TMjilZ06Ay2/BpYSOud7f5FFYlQPdjcJKWIiLyB3LHlBlIIVbWiIgC+dld7qeZd/RDdHExCrPaZ4C2FU94siEQvDC3wYpjMsjOzyjvimbmwOawqawN6FS1bd/ycMgXzBOF1JNfp1oyqiOuerZsoTaoMrFgBelO/e8J6O5AKigYyJ56AiEDJUg8xUbe2Z8rHPjf9vJEMg2gjDzVDJv3hZuWtId0wknYLQ3hE0oUqZOBNlo/+7WNw5/57YfZ3YcM/Fub8Lky/Gcm6mUiBIU1/F/eC8GsoRRaT+0xxFoPhx/Dntf15XX0bxU1//SP4C7n/lvGpzLL8aSruvHKEL/99wZ0PvhVc73QF9wCr6apIUzotOBv7l/RtdruPsCMXXEfWDqU/+nN6bX+bSjpFxe0UuMcbe2jFHWjsVAWnu9vv81es8ZPjjj8B</diagram></mxfile>\n";
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
