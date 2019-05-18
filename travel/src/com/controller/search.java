package com.controller;

import com.Service.PostService;
import com.Service.Search;
import com.entity.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by worith on 2019/5/11.
 */
//import com.entity.Page;


@WebServlet("/search")
public class search  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        request.setCharacterEncoding("utf-8");
        String sear_content;
        HttpSession session = request.getSession();

        sear_content = request.getParameter("search");


      /*  int pageNum = 0;
        if (request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum")) -1;*/
        PostService ps = new PostService();
        List<Post> list = ps.selectPost();
        Search searchIndex=new Search();
        for (Post p:list){
           searchIndex.createIndex(p);
       };


       List<Post> test=searchIndex.searchWithHighLighter(sear_content);
//        List<Page> pageList=new ArrayList<Page>();
        int totalsize=test.size();
        //int allPages= (int)Math.ceil(totalsize/6.0);
//        for(int i=0;i<allPage;i++){
//            int first=i*6;
//            Page b=searchIndex.searchWithHighLighter(sear_content,first,6);
//            pageList.add(b);
//        }

//        List<question> ques=a.getLists();

        request.setAttribute("list", test);
        //request.setAttribute("q", sear_content);
        session.setAttribute("search", sear_content);
        //request.setAttribute("allPages", allPages);
       // request.getRequestDispatcher("search.jsp?allPages="+allPages+"&pageNum="+(pageNum+1)).forward(request, response);

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}