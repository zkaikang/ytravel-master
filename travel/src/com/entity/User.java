package com.entity;

/**
 * Created by worith on 2019/5/10.
 */
public class User
{
    private int uNum;
    private String uAccount;
    private String uPassword;
    private String birth;
    private String sex;
    private String userImg;
    private String signature;

    public void setuNum(int uNum){this.uNum=uNum;}
    public void setuAccount(String uAccount){this.uAccount=uAccount;}
    public void setuPassword(String uPassword){this.uPassword=uPassword;}
    public void setSex(String sex){this.sex=sex;}
    public void setSignature(String signature){this.signature=signature;}
    public void setUserImg(String userImg){this.userImg=userImg;}
    public void setBirth(String birth){this.birth=birth;}


    public int getuNum(){return uNum;}
    public String getuAccount(){return uAccount;}
    public String getuPassword(){return uPassword;}
    public String getBirth(){return birth;}
    public String getSex(){return sex;}
    public String getUserImg(){return userImg;}
    public String getSignature(){return signature;}

    public User(){};
    public User(int uNum,String uAccount,String uPassword,String birth,String sex,String userImg,String signature){


        this.uNum=uNum;
        this.uAccount=uAccount;
        this.uPassword=uPassword;
        this.birth=birth;
        this.sex=sex;
        this.userImg=userImg;
        this.signature=signature;

    }
    public User(String uAccount,String uPassword){
        this.uAccount=uAccount;
        this.uPassword=uPassword;
    }




}
