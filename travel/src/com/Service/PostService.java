package com.Service;

import com.Dao.Dao;
import com.entity.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostService extends Dao {
    //根据id查找post
    public Post selectPost(int qNum) {
        if (con == null)
            getCon();
        // 创建sql命令

        String sql = "select * from yPost where id = ?";
        Post post = null;
        try {
            // 创建sql命令对象
            ps = con.prepareStatement(sql);


            ps.setInt(1, qNum);
            // 执行sql命令
            rs = ps.executeQuery();
            if (rs.next()) {
                post = new  Post(rs.getInt("id"), rs.getString("authur"), rs.getString("title"),
                        rs.getString("content"));
            }
            return post;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
     //通过用户查找问题
    public List<Post> selectPost(String authur) {

        if (con == null)
            getCon();
        // 创建sql命令
        //String sql="select * from question_infor WHERE USERNUM=5";
        String sql = "select * from yPost where authur=?";
        List<Post> list = new ArrayList<Post>();
        int i = 0;
        Post post=null;
        try {

            // 创建sql命令对象
            ps = con.prepareStatement(sql);
            ps.setString(1,authur);
            // 执行sql命令
            rs = ps.executeQuery();
            while (rs.next()) {
               post = new  Post(rs.getInt("id"), rs.getString("authur"), rs.getString("title"),
                        rs.getString("content"));
                list.add(post);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 返回结果

        return list;
    }
      //此处select用来给之后的lucene建索引
      public List<Post> selectPost() {

        if (con == null)
            getCon();
        // 创建sql命令
        //String sql="select * from question_infor WHERE USERNUM=5";
        String sql = "select * from yPost";
        List<Post> list = new ArrayList<Post>();
        int i = 0;
          Post post=null;
        try {

            // 创建sql命令对象
            ps = con.prepareStatement(sql);

            // 执行sql命令
            rs = ps.executeQuery();
            while (rs.next()) {
                post = new  Post(rs.getInt("id"), rs.getString("authur"), rs.getString("title"),
                        rs.getString("content"));
                list.add(post);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 返回结果

        return list;
    }

   //创建问题
    public int insertPost(Post newPost) {
        if (con == null)
            getCon();

        String sql = "insert into yPost(title,authur,content) values(?,?,?)";
        int i = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, newPost.getpTitle());
            ps.setString(2, newPost.getpAuthur());
            ps.setString(3, newPost.getContent());

            i = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return i;
    }


}
