package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.data.bean.login.LoginData;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Create by 17474 on 2021/4/22.
 * Email： lishuwentimor1994@163.com
 * Describe：在这里先处理一遍网络请求
 */
public class ApiCase {

    private final static MVPApiImpl API_IMPL = new MVPApiImpl();

    private static class LazyHolder {
        private final static ApiCase INSTANCE = new ApiCase();
    }

    public static ApiCase getInstance() {
        return LazyHolder.INSTANCE;
    }


    public <T> Observable<LoginData> login(LoginRequest loginRequest) {
        Map<String, String> map = new HashMap<>();
        map.put("userEmail", loginRequest.getUserName());
        map.put("userPassword", loginRequest.getPassword());
        map.put("appVersion", loginRequest.getAppVersion());
        map.put("channel", loginRequest.getChannel());
        return API_IMPL.login(map)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult());
    }

}
