package com.renogy.mvpmode.base.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：响应的基类
 */
public class BaseResponse<T> {

    public final static String CODE_OK = "A000000";

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private T data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("fail")
    private Boolean fail;
    @SerializedName("success")
    private Boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getFail() {
        return fail;
    }

    public void setFail(Boolean fail) {
        this.fail = fail;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
