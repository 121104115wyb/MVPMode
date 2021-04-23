package com.renogy.mvpmode.data.bean.login;

import com.google.gson.annotations.SerializedName;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：登录成功后返回的token
 */
public class LoginData {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
