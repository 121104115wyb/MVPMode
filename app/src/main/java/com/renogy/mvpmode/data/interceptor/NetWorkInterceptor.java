package com.renogy.mvpmode.data.interceptor;


import android.text.TextUtils;

import com.renogy.mvpmode.common.AppHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器，统一添加公共的字段
 *
 * @author wyb
 */
public class NetWorkInterceptor implements Interceptor {
    //你的Url中token的key
    private final static String TOKEN_KEY = "x-token";
    private static Map<String, String> requestMap = new HashMap<>();

    /**
     * 添加您的公共参数
     */
    public NetWorkInterceptor(String key, String o) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(o)) return;
        requestMap.put(key, o);
    }

    /**
     * 添加您的公共参数
     */
    public NetWorkInterceptor() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String token = AppHelper.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            requestMap.put(TOKEN_KEY, token);
        }

        Request request = chain.request();
        Request.Builder updateRequest = request.newBuilder();
        if (requestMap != null) {
            for (Map.Entry<String, String> entry : requestMap.entrySet()) {
                updateRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return chain.proceed(updateRequest.build());
    }
}