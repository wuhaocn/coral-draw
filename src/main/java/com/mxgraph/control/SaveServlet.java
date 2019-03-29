/**
 * Copyright (c) 2011-2012, JGraph Ltd
 */
package com.mxgraph.control;

import com.mxgraph.bean.DrawData;
import com.mxgraph.service.DrawDataService;
import com.mxgraph.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveServlet.
 * <p>
 * The SaveDialog in Dialogs.js implements the user interface. Editor.saveFile
 * in Editor.js implements the request to the server. Note that this request
 * is carried out in a separate iframe in order to allow for the response to
 * be handled by the browser. (This is required in order to bring up a native
 * Save dialog and save the file to the local filestyem.) Finally, the code in
 * this servlet echoes the XML and sends it back to the client with the
 * required headers (see Content-Disposition in RFC 2183).
 */
@RestController
@RequestMapping("/save")
public class SaveServlet {


    @Autowired
    private DrawDataService dataService;

    /**
     * @see SaveServlet#save(HttpServletRequest request, HttpServletResponse response)
     */
    @PostMapping()
    @ResponseBody
    public void save(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        if (request.getContentLength() < Constants.MAX_REQUEST_SIZE) {
            String filename = request.getParameter("filename");
            String xml = request.getParameter("xml");

            if (filename == null) {
                filename = "export";
            }

            if (xml != null && xml.length() > 0) {
                String format = request.getParameter("format");

                if (format == null) {
                    format = "xml";
                }

                if (!filename.toLowerCase().endsWith("." + format)) {
                    filename += "." + format;
                }

                // Decoding is optional (no plain text values allowed)
                if (xml != null && xml.startsWith("%3C")) {
                    xml = URLDecoder.decode(xml, "UTF-8");
                }
                saveToStorage(filename, xml);
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + filename
                                + "\"; filename*=UTF-8''" + filename);
                response.setStatus(HttpServletResponse.SC_OK);

                OutputStream out = response.getOutputStream();
                out.write(xml.getBytes("UTF-8"));
                out.flush();
                out.close();
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE);
        }
    }


    private void saveToStorage(String fileName, String xml){
        DrawData drawData = new DrawData();
        drawData.setBody(xml);
        drawData.setName(fileName);
        drawData.setOwnerId("kong");
        drawData.setId(UUID.randomUUID().toString());
        dataService.save(drawData);
    }
}
