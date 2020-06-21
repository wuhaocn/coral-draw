/**
 * Copyright (c) 2006-2017, JGraph Ltd
 * Copyright (c) 2006-2017, Gaudenz Alder
 */
package com.mxgraph.server.online;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation ProxyServlet
 */
@SuppressWarnings("serial")
@RequestMapping("/github")
public class GitHubServlet{

    /**
     * Path component under war/ to locate iconfinder_key file.
     */
    public static final String DEV_CLIENT_SECRET_FILE_PATH = "/WEB-INF/github_dev_client_secret";


    /**
     * Path component under war/ to locate iconfinder_key file.
     */
    public static final String CLIENT_SECRET_FILE_PATH = "/WEB-INF/github_client_secret";

    /**
     *
     */
    private static String DEV_CLIENT_SECRET = null;

    /**
     *
     */
    private static String CLIENT_SECRET = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GitHubServlet() {
        super();
    }

    /**
     * Loads the key.
     */
    protected void updateKeys(HttpServletRequest request) {
        if (DEV_CLIENT_SECRET == null) {
            try {
                DEV_CLIENT_SECRET = Utils
                        .readInputStream(request.getServletContext()
                                .getResourceAsStream(DEV_CLIENT_SECRET_FILE_PATH))
                        .replaceAll("\n", "");
            } catch (IOException e) {
                throw new RuntimeException("Dev client secret path invalid.");
            }
        }

        if (CLIENT_SECRET == null) {
            try {
                CLIENT_SECRET = Utils
                        .readInputStream(request.getServletContext()
                                .getResourceAsStream(CLIENT_SECRET_FILE_PATH))
                        .replaceAll("\n", "");
            } catch (IOException e) {
                throw new RuntimeException("Client secret path invalid.");
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String client = request.getParameter("client_id");
        String code = request.getParameter("code");
        updateKeys(request);

        if (client != null && code != null) {
            String secret = client.equals("23bc97120b9035515661") ? DEV_CLIENT_SECRET : CLIENT_SECRET;

            String url = "https://github.com/login/oauth/access_token";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "draw.io");

            String urlParameters = "client_id=" + client + "&client_secret="
                    + secret + "&code=" + code;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer res = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine);
            }
            in.close();

            response.setStatus(con.getResponseCode());

            OutputStream out = response.getOutputStream();

            // Creates XML for stencils
            PrintWriter writer = new PrintWriter(out);

            // Writes JavaScript and adds function call with
            // stylesheet and stencils as arguments
            writer.println(res.toString());

            writer.flush();
            writer.close();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
