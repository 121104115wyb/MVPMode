package com.renogy.mvpmode.base.fragment;

import androidx.viewbinding.ViewBinding;

import com.renogy.mvpmode.base.presenter.BasePresenter;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：
 * 其中FragmentPagerAdapter 参数 behavior 为 FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
 * 根据FragmentPagerAdapter.setMaxLifecycle() 我们实现懒加载的方式如下
 */
public abstract class ViewPageLazyLoadFragment<T extends BasePresenter, VB extends ViewBinding> extends BaseFragment<T, VB> {

    protected boolean mIsLoadData;

    @Override
    public void onResume() {
        super.onResume();
        if (!mIsLoadData) {
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
