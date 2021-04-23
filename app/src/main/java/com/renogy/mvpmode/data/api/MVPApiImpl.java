package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.data.bean.login.LoginData;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：实现接口
 */
public class MVPApiImpl implements MVPApi {

    private MVPApi getApi() {
        return ServiceHelper.getInstance().getMVPApiService();
    }

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return getApi().getLoginData(username, password);
    }

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param map
     * @return 登陆数据
     */
    @Override
    public Observable<BaseResponse<LoginData>> login(Map<String, String> map) {
        return getApi().login(map);
    }
}
