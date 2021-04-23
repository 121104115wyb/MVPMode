package com.renogy.mvpmode.common;

/**
 * @author Create by 17474 on 2021/4/22.
 * Email： lishuwentimor1994@163.com
 * Describe：自定义你的错误信息
 */
public enum ExceptionEnum {

    /**
     * httpException Response 为空
     */
    RESPONSE_NULL(10000, "Http error"),

    /**
     * String 格式化成 baseResponse 出错
     */
    RESPONSE_ERROR(10001, "http response error"),

    /**
     * 数据格式错误
     */
    FORMAT_ERROR(10002, "Data format error"),

    /**
     * 网络连接超时
     */
    CONNECT_TIMEOUT(10003, "Network connection timed out"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(10004, "unknown error"),

    /**
     * 服务器地址或者端口错误 IP address of a host
     */
    SERVER_ERROR(10005, "Server is not responding"),

    /**
     * 服务器不理解请求的语法。
     */
    HTTP_400_ERROR(10006, "Request error"),

    /**
     * 验证用户身份，服务未授权
     */
    HTTP_401_ERROR(10007, "Service is not authorized"),

    /**
     * 服务器拒绝访问
     */
    HTTP_403_ERROR(10008, "The server rejected the request"),

    /**
     * 未找到请求的资源
     */
    HTTP_404_ERROR(10009, "The server could not find the request"),
    /**
     * 服务器内部错误
     */
    HTTP_500_ERROR(10010, "Server internal error"),
    /**
     * 错误网关
     */
    HTTP_502_ERROR(10011, "Gateway error"),
    /**
     * 服务暂时不可用
     */
    HTTP_503_ERROR(10012, "Service is not available"),
    /**
     * 网关超时
     */
    HTTP_504_ERROR(10013, "Gateway timeout"),
    /**
     * HTTP 版本不受支持
     */
    HTTP_505_ERROR(10014, "Unsupported HTTP version"),
    /**
     * HTTP 版本不受支持
     */
    UNKNOWN_SERVER_ERROR(10015, "Unknown service error"),
    /**
     * HTTP 版本不受支持
     */
    BUSINESS_ERROR(0, "");


    private int code;

    private String errorMsg;

    ExceptionEnum(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAllMsg() {
//        if (exceptionEnum != null) {
//            return "code ：" + exceptionEnum.code + "\n" + exceptionEnum.errorMsg;
//        } else {
//            return "code ：99999" + "\n" + "unknown error";
//        }
        return "code ：" + this.code + "\n" + this.errorMsg;
    }


}
