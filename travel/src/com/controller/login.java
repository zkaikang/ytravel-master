package com.controller;

import com.Service.UserService;
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
 * Created by worith on 2019/5/11.
 */

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
        String userAccount =json.getString("userAccount");
        String userPassword=json.getString("userPassword");
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        //String userAccount =request.getParameter("userAccount");
       // userAccount = new String(userAccount.getBytes("iso-8859-1"),"utf-8");
        //String userPassword  =request.getParameter("userPassword");
        //userPassword = new String(userPassword.getBytes("iso-8859-1"),"utf-8");
        User user1 = new User(userAccount,userPassword);
        UserService con = new UserService();
        int i = con.login(user1);
//		respnse.setHeader("Cache-Control", "no-cache");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("expires", -1);
        HttpSession session = request.getSession();
        if(i != 0) {

            // request.setAttribute("message", "loginS");
           /* JSONObject json = JSONObject.fromObject(user1);
            System.out.println(json.toString());
            try {
                response.getWriter().println(json);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close();
            }*/
            String message="loginS";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close();
            }


           // request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message", "loginW");

            //response.setCharacterEncoding("GBK");
          //  request.getRequestDispatcher("index.jsp").forward(request, response);
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
