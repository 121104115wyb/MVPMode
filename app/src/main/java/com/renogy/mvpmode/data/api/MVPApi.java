package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.data.bean.login.LoginData;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.data.bean.main.TopicResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：所有的api接口，在这里声明
 */
public interface MVPApi {

    //下面接口增删改查，都做下简单示例

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @return 登陆数据
     */
    @POST("api/v1/user/login")
    Observable<BaseResponse<LoginData>> login(@Body LoginRequest loginRequest);


    /**
     * 测试获取帖子列表
     * 仅限用于测试，如发现用于商业用途，八个雅璐
     */
    @GET("api/v1/community/get_post_list/{pageNum}/{size}")
    Observable<BaseResponse<TopicResponse>> loadPostList(@Path("pageNum") String pageNum, @Path("size") String size, @QueryMap Map<String, String> map);



}
