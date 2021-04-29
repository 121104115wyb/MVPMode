package com.renogy.mvpmode.common;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：app 全局静态常量
 */
public class AppConstants {

    public final static String APP_DEFAULT_LOG = "MVPLog";

    //测试环境服务器地址
//    public final static String APP_BASE_DEV_URL = "http://192.168.20.168:15000";
    public final static String APP_BASE_DEV_URL = "http://39.103.13.231/dchome_app/";
//    public final static String APP_BASE_DEV_URL = "http://192.168.18.28:20002";

    //正式环境服务器地址
    public final static String APP_BASE_RELEASE_URL = "http://39.103.13.231/dchome_app/";



    //一页默认的长度
    public final static int LIST_DEFAULT_COUNT = 15;

    /******************************************************************************************
     ****************************       全局常量       *****************************************
     ******************************************************************************************/
    //保存token的key
    public final static String APP_TOKEN = "MVP_TOKEN";
    //保存用户用户名的key
    public final static String APP_USERNAME = "APP_USERNAME";
    //保存用户密码的key
    public final static String APP_PASSWORD = "APP_PASSWORD";

    //保存用户是否登录的状态，如果登录过，就展示banner，如果没有就展示登录页面
    public final static String HAS_LOGIN = "HAS_LOGIN";


}
