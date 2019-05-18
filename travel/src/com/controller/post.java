package com.controller;

/**
 * Created by worith on 2019/5/11.
 */
import com.Service.PostService;
import com.entity.Post;
import com.entity.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Servlet implementation class newQue
 */
@WebServlet("/newPost")
public class post extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public post() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub



        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);}
        JSONObject json=JSONObject.fromObject(sb.toString());

        String pTitle = json.getString("title");
        String pInfo=json.getString("content");
        String userName=json.getString("userAccount");
       // pTitle = new String(pTitle.getBytes("iso-8859-1"),"utf-8");
        //String pInfo = request.getParameter("content");
        //pInfo = new String(pInfo.getBytes("iso-8859-1"),"utf-8");

        //HttpSession session = request.getSession();
        //User theUser = (User)session.getAttribute("user");
        Post newPost = new Post(pTitle,userName,pInfo);
       // Post newPost = new Post(pTitle,theUser.getuAccount(),pInfo);

        PostService ps = new PostService();
        int i = ps.insertPost(newPost);

        if(i!=0) {
            String message="insertS";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close();
            }

        }
        else{
            String message="insertW";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close();
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
