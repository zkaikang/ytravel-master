package com.controller;

/**
 * Created by worith on 2019/5/11.
 */
import com.Service.UserService;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class changeUserInfo
 */
@WebServlet("/update")
public class update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //设置编码格式，前端后台统一是utf-8
        // request.setCharacterEncoding("utf-8");
        //response.setContentType("text/json;charset=utf-8");

        String userAccount = request.getParameter("userAccount");
        // userAccount = new String(userAccount.getBytes("iso-8859-1"),"utf-8");
        String userPassword = request.getParameter("userPassword");
        //userPassword = new String(userPassword.getBytes("iso-8859-1"),"utf-8");
        String signature = request.getParameter("signature");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String sign = request.getParameter("sign");
        String userImge = request.getParameter("userImage");
        HttpSession session = request.getSession();
        User theUser = (User) session.getAttribute("user");
        theUser.setuAccount(userAccount);
        theUser.setSignature(signature);
        theUser.setSex(sex);
        theUser.setBirth(birth);
        theUser.setSignature(sign);
        theUser.setUserImg(userImge);
        UserService userS = new UserService();
        int i = userS.updateUser(theUser);
        if (i != 0) {

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
            String message = "updateS";
            try {
                response.getWriter().println(message);
                // 将json数据传给客户端
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getWriter().close();
            }

            //request.getRequestDispatcher("userPage.jsp").forward(request, response);
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
