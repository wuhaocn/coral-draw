package com.mxgraph.server.utils;

import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * apache官方http工具类
 *
 * Created by wuhao on 15-8-5.
 */
public class HttpUtil {
    /**
     * 获取request body体
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getBody(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int contentLength = request.getContentLength();
        if (contentLength > 0) {
            int sum = 0;
            byte[] buffer = new byte[contentLength];
            while (sum < contentLength) {
                int count = request.getInputStream().read(buffer);
                if (count > 0) {
                    outputStream.write(buffer, 0, count);
                    sum += count;
                } else if (count < 0) {
                    outputStream.close();
                    throw new IOException(String.format("getRequestBody read return %s. contentLength:%s sum:%s ",
                            count, contentLength, sum));
                }
            }
        }
        outputStream.flush();
        byte[] outputBuffer = outputStream.toByteArray();
        outputStream.close();
        return outputBuffer;
    }
    /**
     * 获取request body String
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getBodyString(HttpServletRequest request) throws IOException {
        byte[] bodyByte = getBody(request);
        return new String(bodyByte);
    }
    /**
     * 获取request body体
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequest(HttpServletRequest request, String body){
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(String.format("%s %s %s\n", request.getMethod(), request.getRequestURI(), request.getProtocol()));
            for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements(); ) {
                String headName = e.nextElement();
                String headValue = request.getHeader(headName);
                sb.append(String.format("%s: %s\n", headName, headValue));
            }
            if (body != null && body.length() > 0) {
                sb.append("\n");
                sb.append(body);
            }
        } catch (Exception e){
            sb.append(e.getMessage());
        }

        return sb.toString();
    }


    public static String getResponse(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(temp)) > 0) {
            arrayOutputStream.write(temp, 0, length);
        }
        return new String(arrayOutputStream.toByteArray());
    }


}
