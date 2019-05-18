package com.entity;

/**
 * Created by worith on 2019/5/10.
 */
public class Post
{
    private int pNum;
    private String pTitle;
    private String pAuthur;
    private String content;

    public void setpNum(int pNum){this.pNum=pNum;}
    public void setpTitle(String pTitle){this.pTitle=pTitle;}
    public void setpAuthur(String pAuthur){this.pAuthur=pAuthur;}
    public void setContent(String content){this.content=content;}


    public int getpNum(){return pNum;}
    public String getpTitle(){return pTitle;}
    public String getpAuthur(){return pAuthur;}
    public String getContent(){return content;}

    public Post(){};
    public Post(int pNum,String pTitle,String pAuthur,String content){


        this.pNum=pNum;
        this.pTitle=pTitle;
        this.pAuthur=pAuthur;
        this.content=content;


    }
    public Post(String pTitle,String pAuthur,String content){



        this.pTitle=pTitle;
        this.pAuthur=pAuthur;
        this.content=content;


    }
}
