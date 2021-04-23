package com.renogy.mvpmode.data.api;

import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.data.bean.login.LoginData;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：所有的api接口，在这里声明
 */
public interface MVPApi {


    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username, @Field("password") String password);


    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @return 登陆数据
     */
    @POST("api/v1/user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> login(@FieldMap Map<String,String> map);


}
