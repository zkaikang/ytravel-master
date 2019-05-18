package com.Service;

import com.Dao.Dao;
import com.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Dao {
    //用户登录
    public int login(User theUser){
        if(con==null)
            getCon();
        //创建sql命令
        String sql="select * from yUser where account=?";
        String truepassword=null;
        int i = 0;
        int uNum = 0;
        try {
            //获取sql命令对象
            ps=con.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, theUser.getuAccount());
            //执行sql命令
            rs=ps.executeQuery();//帐号是不可重复的
            while(rs.next()){
                truepassword=rs.getString("password");
                uNum = rs.getInt("id");
                if (theUser.getuPassword().equals(truepassword)){
                    theUser.setuNum(uNum);
                    theUser.setUserImg(rs.getString("userimage"));
                    theUser.setBirth(rs.getString("birth"));
                    theUser.setSex(rs.getString("sex"));
                    theUser.setSignature(rs.getString("sign"));
                    i=1;
                }
            }

        } catch (SQLException e) {
            System.out.println("命令失败！");
            e.printStackTrace();
        }

        //返回结果
        return i;
    }


/*    public User selectOneUser(int uNum) {
        if (con == null)
            getCon();
        // 创建sql命令

        String sql = "select * from user_infor where usernum = ?";
        user theUser = null;
        try {
            // 创建sql命令对象
            ps = con.prepareStatement(sql);


            ps.setInt(1, uNum);
            // 执行sql命令
            rs = ps.executeQuery();
            if (rs.next()) {
                theUser = new user(rs.getInt("usernum"),rs.getString("USERACCOUNT"),rs.getString("USERPASSWORD"),
                        rs.getString("nickname"),rs.getTimestamp("birth"),rs.getInt("sex"),
                        rs.getString("signature"),rs.getString("focus_users"),rs.getString("COLLECTION_QUESTION"),
                        rs.getString("USERIMAGE"),rs.getString("BROWSING_HISTORY"),rs.getInt("integral"));
            }
            return theUser;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }*/

    //未修改
    public List<User> selectUser(){

        if(con==null)
            getCon();
        //创建sql命令
        String sql="select * from yUser";
        List<User> list = new ArrayList<User>();
        int i= 0;
        try {

            //创建sql命令对象
            ps=con.prepareStatement(sql);

            //执行sql命令
            rs=ps.executeQuery();
            while(rs.next()){
                User newUser = new User(rs.getString("account"),
                        rs.getString("password"));
                list.add(newUser);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //返回结果


        return list;
    }


    //注册
    public int insertUser(User newUser) {
        if(con==null)
            getCon();
        //创建sql命令
        String sql="insert into yUser(account, password) values(?,?)";

        int i= 0;
        try {
            //创建sql命令对象
            ps=con.prepareStatement(sql);

            //给占位符赋值
            //ps.setInt(1, newUser.getuNum());
            ps.setString(1,newUser.getuAccount());
            ps.setString(2,newUser.getuPassword());
            //执行sql命令

            i=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //返回结果
        return i;
    }

    //修改用户信息        待修改
    public int updateUser(User theUser) {
        if(con==null)
            getCon();
        //创建sql命令
        String sql="update yUser set account=?,password=?,sex=?,birth=? ,sign=?,userimage=? where id=?";
        int i = 0;
        try {
            //获取sql命令对象
            ps=con.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, theUser.getuAccount());
            ps.setString(2, theUser.getuPassword());

            ps.setString(3, theUser.getSex());
            ps.setString(4,theUser.getBirth());
            ps.setString(5, theUser.getSignature());
            ps.setString(6, theUser.getUserImg());
            ps.setInt(7,theUser.getuNum());
            //执行sql命令
            i=ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //返回结果
        return i;
    }

}
