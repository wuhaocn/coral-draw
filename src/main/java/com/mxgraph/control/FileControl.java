package com.mxgraph.control;

import com.alibaba.fastjson.JSONObject;
import com.feinno.superpojo.util.StringUtils;
import com.feinno.util.DateUtil;
import com.mxgraph.utils.HttpUtil;
import com.urcs.data.service.common.content.ContentBean;
import com.urcs.data.service.common.content.ContentService;
import org.helium.framework.annotations.ServiceSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 *
 *
 */
@RestController
@RequestMapping("/file")
public class FileControl {

    @ServiceSetter
    private ContentService contentService;

    private String type = "drawview";

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

        ContentBean contentBean = new ContentBean();
        //为空生成id
        if (StringUtils.isNullOrEmpty(flid)){
            flid = UUID.randomUUID().toString();
        }
        //默认名称
        String title = flid + ".xml";
        contentBean.setType(type);
        contentBean.setCns(type);
        contentBean.setName(title);
        contentBean.setId(flid);

        //获取内容
        contentBean = contentService.get(contentBean);
        String xmlBody = "";
        String xmlTitle = "";
        if (contentBean == null){
            xmlBody = DXML;
            xmlTitle = title;
            contentService.save(contentBean);
        } else {
            xmlBody = contentBean.getBody();
            xmlTitle = contentBean.getName();
        }
        LOGGER.info("get File:{}-{}", xmlTitle, xmlBody);
        response.getOutputStream().write(xmlBody.getBytes());
        response.setHeader("title", title);
        response.setStatus(200);
    }


    @PostMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String body = HttpUtil.getBodyString(request);
        String xmlTitle = request.getHeader("title");
        String xmlId = request.getHeader("flid");

        ContentBean contentBean = new ContentBean();
        contentBean.setType(type);
        contentBean.setCns(type);
        contentBean.setBody(body);
        contentBean.setName(xmlTitle);
        contentBean.setId(xmlId);
        contentBean.setModifyTime(DateUtil.getDefaultYearMonthDayTime());

        contentService.save(contentBean);
        LOGGER.info("save File:{}-{}-{}", xmlTitle, body);
        response.setStatus(200);
    }

    private static final String DXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<mxfile modified=\"2019-02-12T08:55:28.159Z\" host=\"127.0.0.1\" agent=\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36\" etag=\"BhcgNWRefkg5PAe-CUDU\" version=\"10.0.39\" type=\"browser\">\n" +
            "  <diagram id=\"FO2v3a15tLzoUDuactzV\" name=\"Page-1\">dZHBEoIgEIafhrtCk3U2y0snD50Z2YAZdB2k0Xr6dMCMsU4s3/4/y+4SljfjxfJOXVGAITQRI2EnQmm6Y8l0zOTpSZYyD6TVIohWUOkXBBh88qEF9JHQIRqnuxjW2LZQu4hxa3GIZXc0cdWOS9iAquZmS29aOOXpgWYrL0FLtVRO90efafgiDp30igscvhArCMstovNRM+Zg5uEtc/G+85/s52MWWvfDMAXr29Ml2hAr3g==</diagram>\n" +
            "</mxfile>\n";
}
