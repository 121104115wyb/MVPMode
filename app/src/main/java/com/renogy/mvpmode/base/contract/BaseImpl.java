package com.renogy.mvpmode.base.contract;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：基类
 */
public interface BaseImpl {

    void stateLoading();

    void stateFinish();

    void showToast(String msg);

    void showSnackBar(String msg);


}
