package com.renogy.mvpmode.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.renogy.mvpmode.base.presenter.BasePresenter;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：懒加载
 * <p>
 * 使用setUserVisibleHint实现懒加载
 * 其中FragmentPagerAdapter 参数 behavior 为 FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT 兼容setUserVisibleHint模式
 * 我们可以设置FragmentPagerAdapter.setOffscreenPageLimit()的最大值，实现懒加载
 */
public abstract class ViewPage1LazyLoadFragment<T extends BasePresenter, VB extends ViewBinding> extends BaseFragment<T, VB> {

    protected boolean mIsVisibleToUser;
    protected boolean mIsViewCreated;
    protected boolean mIsLoadData;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mIsLoadData) {
            fetchData();
            mIsLoadData = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisibleToUser = isVisibleToUser;
        if (mIsVisibleToUser && mIsViewCreated && !mIsLoadData) {
            fetchData();
            mIsLoadData = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mIsVisibleToUser) {
            fetchData();
            mIsLoadData = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsViewCreated = false;
        mIsLoadData = false;
        mIsVisibleToUser = false;
    }
}
