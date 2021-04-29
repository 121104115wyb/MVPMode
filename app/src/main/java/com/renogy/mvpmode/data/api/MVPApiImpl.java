package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.data.bean.login.LoginData;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.data.bean.main.BannerBean;
import com.renogy.mvpmode.data.bean.main.TopicResponse;

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

//    /**
//     * 登陆
//     * http://www.wanandroid.com/user/login
//     *
//     * @param username user name
//     * @param password password
//     * @return 登陆数据
//     */
//    @Override
//    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
//        return getApi().getLoginData(username, password);
//    }

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param loginRequest 登录请求
     * @return 登陆数据
     */
    @Override
    public Observable<BaseResponse<LoginData>> login(LoginRequest loginRequest) {
        return getApi().login(loginRequest);
    }

    /**
     * 测试获取帖子列表
     * 仅限用于测试，如发现用于商业用途，八个雅璐
     *
     * @param pageNum 第几页
     * @param size    一页的数据
     * @param map     搜索条件
     */
    @Override
    public Observable<BaseResponse<TopicResponse>> loadPostList(String pageNum, String size, Map<String, String> map) {
        return getApi().loadPostList(pageNum, size, map);
    }

    @Override
    public Observable<BaseResponse<BannerBean>> loadBanner() {
        return getApi().loadBanner();
    }
}
