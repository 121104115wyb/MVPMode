package com.renogy.mvpmode.http.observe;

/**
 * @author Create by 17474 on 2021/4/23.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
interface ObserverImpl<T> {

    void onRxSuccess(T response);

    void onRxError(Throwable throwable);

}
