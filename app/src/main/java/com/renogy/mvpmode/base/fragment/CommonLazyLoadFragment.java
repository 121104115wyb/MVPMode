package com.renogy.mvpmode.base.fragment;

import androidx.viewbinding.ViewBinding;

import com.renogy.mvpmode.base.presenter.BasePresenter;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：使用add+show+hide方式实现懒加载
 */
public abstract class CommonLazyLoadFragment <T extends BasePresenter, VB extends ViewBinding> extends BaseFragment<T,VB> {

    protected boolean mIsLoadData;

    @Override
    public void onResume() {
        super.onResume();
        judgeLazyInit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        judgeLazyInit();
    }

    //校验懒加载的方法
    private void judgeLazyInit(){
        if (!mIsLoadData && !isHidden()){
            fetchData();
            mIsLoadData = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsLoadData = false;
    }
}
