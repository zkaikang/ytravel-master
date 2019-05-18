package com.controller;

import com.Service.UserService;
import com.entity.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by worith on 2019/5/11.
 */

@WebServlet("/register")
public class register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //int userNum  = Integer.parseInt(request.getParameter("userNum"));
        doPost(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);}
        JSONObject json=JSONObject.fromObject(sb.toString());
        String userAccount =json.getString("userAccount");
        String userPassword=json.getString("userPassword");

        //String userAccount =request.getParameter("userAccount");

        //String userPassword  =request.getParameter("userPassword");
        //userPassword = new String(userPassword.getBytes("iso-8859-1"),"utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user1 = new User(userAccount,userPassword);
        UserService con = new UserService();
        System.out.print(userAccount+userPassword);
        int i = con.insertUser(user1);
        if(i!=0) {
            // request.setAttribute("message", "registerS");
           /* JSONObject json = JSONObject.fromObject(user1);
            System.out.println(json.toString());
            try {
                response.getWriter().println(json);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close(); // 关闭这个流，不然会发生错误的
            }*/
            String message="registerS";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close(); // 关闭这个流，不然会发生错误的
            }
//            request.setAttribute("message", "registerS");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            String message="registerf";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close(); // 关闭这个流，不然会发生错误的
            }

        }
    }

}
