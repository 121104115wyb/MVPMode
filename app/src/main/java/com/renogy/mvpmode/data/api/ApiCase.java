package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.data.bean.login.LoginData;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.data.bean.main.BannerBean;
import com.renogy.mvpmode.data.bean.main.TopicResponse;
import com.renogy.mvpmode.data.server.LocalData;
import com.renogy.mvpmode.utils.RxUtils;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

import static com.renogy.mvpmode.common.AppConstants.LIST_DEFAULT_COUNT;

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


    public Observable<LoginData> login(LoginRequest loginRequest) {
        return API_IMPL.login(loginRequest)
                .compose(RxUtils.handleResultWithIoScheduler());
    }

    //获取帖子 列表
    public Observable<TopicResponse> loadPostList(String pageNum, Map<String, String> map) {
        return API_IMPL.loadPostList(pageNum, String.valueOf(LIST_DEFAULT_COUNT), map)
                .compose(RxUtils.handleResultWithIoScheduler());
    }

    public Observable<BannerBean> loadBanner() {
        return Observable.just(LocalData.loadBanner());
    }

}
