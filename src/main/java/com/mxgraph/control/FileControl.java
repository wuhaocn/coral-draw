package com.mxgraph.control;

import com.alibaba.fastjson.JSONObject;
import com.mxgraph.bean.DrawData;
import com.mxgraph.bean.PageData;
import com.mxgraph.config.CoralConfig;
import com.mxgraph.service.DrawDataService;
import com.mxgraph.utils.HttpUtil;
import com.mxgraph.utils.SessionUtils;
import com.mysql.jdbc.StringUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 *
 */
@RestController
@RequestMapping("/file")
public class FileControl {

    @Autowired
    private DrawDataService dataService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * http://127.0.0.1:8081/index.html?offline=1&local=1&flid=1&spin=1
     *
     * @param uuid
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/get/{uuid}")
    @ResponseBody
    public void get(@PathVariable String uuid, HttpServletRequest request, HttpServletResponse response) throws IOException {

        DrawData drawData = null;
        //为空生成id
        if (StringUtils.isNullOrEmpty(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        //获取内容
        drawData = dataService.findByUuid(uuid);
        if (drawData == null){
            drawData = new DrawData();
            drawData.setUuid(uuid);
            drawData.setOwnerId(uuid);
            drawData.setName(CoralConfig.DNAME);
            drawData.setBody(CoralConfig.DXML.getBytes());

        }
        LOGGER.info("get File:{}-{}", JSONObject.toJSONString(drawData));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", drawData.getName());
        jsonObject.put("data", new String(drawData.getBody()));
        response.getOutputStream().write(jsonObject.toJSONString().getBytes());
        response.setStatus(200);
    }


    @PostMapping("/save")
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String drawJsonStr = HttpUtil.getBodyString(request);
            JSONObject drawJson = JSONObject.parseObject(drawJsonStr);
            LOGGER.info("save File:{}", drawJson);
            String id = drawJson.getString("id");
            String ownerId = drawJson.getString("ownerId");
            String title = drawJson.getString("title");
            String data = drawJson.getString("data");
            DrawData drawData = dataService.findByUuid(id);
            if (drawData == null){
                drawData = new DrawData();
            }
            drawData.setUuid(id);
            drawData.setOwnerId(ownerId);
            drawData.setName(title);
            drawData.setBody(data.getBytes());
            if(StringUtils.isNullOrEmpty(ownerId)  || StringUtils.isNullOrEmpty(data) ||
                    StringUtils.isNullOrEmpty(title) || !ownerId.equals(drawData.getOwnerId())){
                response.setStatus(403);
                return;
            }
            dataService.save(drawData);
            LOGGER.info("save File:{}", JSONObject.toJSONString(drawData));
            response.setStatus(200);
        } catch (Exception e){
            response.setStatus(400);
            LOGGER.error("save error:{}", e.getMessage());
        }

    }



    @GetMapping("/list")
    @ResponseBody
    public PageData get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DrawData> dataList = dataService.findByOwnerId(SessionUtils.getCurUid(request));
        if (dataList == null){
            dataList = new ArrayList<>();
        }
        PageData pageData = new PageData(dataList.size(), 0, dataList);
        return pageData;
    }

    @RequestMapping(value = "/del/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject delAllByIds(@PathVariable String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id: ids) {
            DrawData drawData = dataService.findByUuid(id);
            if (drawData != null){
                dataService.delete(drawData);
            }
        }
        jsonObject.put("code", "200");
        return jsonObject;
    }
}
