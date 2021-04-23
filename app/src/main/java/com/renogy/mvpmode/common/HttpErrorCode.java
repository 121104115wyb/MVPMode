package com.renogy.mvpmode.common;

/**
 * @author Create by 17474 on 2021/4/23.
 * Email： lishuwentimor1994@163.com
 * Describe：网络错误响应码
 */
public class HttpErrorCode {

    //请求错误
    public final static int HTTP_400_ERROR = 400;
    //用户身份需要验证，服务未授权
    public final static int HTTP_401_ERROR = 401;
    //服务器拒绝访问
    public final static int HTTP_403_ERROR = 403;
    //未找到请求资源
    public final static int HTTP_404_ERROR = 404;
    //服务器内部错误
    public final static int HTTP_500_ERROR = 500;
    //服务器网关错误
    public final static int HTTP_502_ERROR = 502;
    //服务器暂时不可用，升级维护中
    public final static int HTTP_503_ERROR = 503;
    //服务器网关超时
    public final static int HTTP_504_ERROR = 504;
    //http版本不支持
    public final static int HTTP_505_ERROR = 505;
}
