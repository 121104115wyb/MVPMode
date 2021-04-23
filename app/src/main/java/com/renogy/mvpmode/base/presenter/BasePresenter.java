package com.renogy.mvpmode.base.presenter;

import com.renogy.mvpmode.base.contract.BaseImpl;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public interface BasePresenter<V extends BaseImpl> {

    void attachView(V view);

    void detachView();

}
