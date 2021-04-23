package com.renogy.mvpmode.data.bean.user;

import org.litepal.crud.LitePalSupport;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：模拟记录用户的登录信息
 */
public class UserData extends LitePalSupport {
    //用户唯一标识
    private String userInnerId;
    //用户名
    private String userName;
    //密码
    private String password;
    //登录时间
    private long loginTimestamp;
    //版本号
    private int versionCode;
    //版本名称
    private String versionName;
    //设备类型
    private String deviceModel;
    //手机厂商
    private String manufacturer;


    public String getUserInnerId() {
        return userInnerId;
    }

    public void setUserInnerId(String userInnerId) {
        this.userInnerId = userInnerId;
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

    public long getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(long loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
