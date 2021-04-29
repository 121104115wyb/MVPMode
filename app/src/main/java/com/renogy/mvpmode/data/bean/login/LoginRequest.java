package com.renogy.mvpmode.data.bean.login;

import com.google.gson.annotations.SerializedName;
import com.renogy.mvpmode.base.request.BaseRequest;

import java.io.Serializable;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：登录的请求
 */
public class LoginRequest extends BaseRequest implements Serializable {

    @SerializedName("userEmail")
    private String userName;
    @SerializedName("userPassword")
    private String password;
    @SerializedName("appVersion")
    private String appVersion;
    @SerializedName("channel")
    private String channel;


    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public static LoginRequest of(String userName, String password,String appVersion, String channel) {
        return new LoginRequest(userName, password,appVersion,channel);
    }

    public LoginRequest(String userName, String password, String appVersion, String channel) {
        this.userName = userName;
        this.password = password;
        this.appVersion = appVersion;
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
