package com.renogy.mvpmode.data.exception;

import com.renogy.mvpmode.base.response.BaseResponse;

/**
 * @author Create by 17474 on 2021/4/22.
 * Email： lishuwentimor1994@163.com
 * Describe：定义自己的 Exception
 */
public class RxException extends Exception {

    private BaseResponse response;

    public RxException(BaseResponse response) {
        this.response = response;
    }

    public BaseResponse getResponse() {
        return response;
    }
}
