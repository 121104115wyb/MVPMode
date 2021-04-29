package com.renogy.mvpmode.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.renogy.mvpmode.base.contract.BaseImpl;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentBaseBinding;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：fragment 基类
 */
public abstract class BaseFragment<T extends BasePresenter, VB extends ViewBinding> extends AbstractBaseFragment implements BaseImpl {
    protected T mPresenter;
    protected VB _viewBing;
    protected FragmentBaseBinding bindView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getMPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = FragmentBaseBinding.inflate(inflater, container, false);
        _viewBing = getViewBinding();
        onViewCreate();
        initData();
        return bindView.getRoot();
    }


    @Override
    public void stateLoading() {

    }

    @Override
    public void stateFinish() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showLong(msg);
        SnackbarUtils.with(bindView.snackView).setMessage(msg).show(true);
    }

    @Override
    public void showSnackBar(String msg) {
        SnackbarUtils.with(bindView.snackView).setMessage(msg).show(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bindView != null && bindView.getRoot().getParent() != null) {
            ((ViewGroup) bindView.getRoot().getParent()).removeView(bindView.getRoot());
        }
    }

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    protected abstract VB getViewBinding();

    protected abstract T getMPresenter();
}
