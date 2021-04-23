package com.renogy.mvpmode.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.renogy.mvpmode.base.contract.BaseImpl;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityBaseBinding;


/**
 * @author Create by 17474 on 2021/4/23.
 * Email： lishuwentimor1994@163.com
 * Describe：基类Activity封装
 */
public abstract class BaseActivity<T extends BasePresenter, VB extends ViewBinding> extends AbstractBaseActivity implements BaseImpl {

    protected T mPresenter;
    protected ActivityBaseBinding bindView = null;
    protected VB viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setNavBarVisibility(this, false);
        BarUtils.setStatusBarVisibility(this, false);
        mPresenter = getMPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initParams(savedInstanceState);
        bindView = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(bindView.getRoot());
        viewBinding = getViewBinding();
        onViewCreate();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
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
    }

    @Override
    public void showSnackBar(String msg) {
//        SnackbarUtils.with(this).show()
    }

    protected void initParams(Bundle bundle) {

    }

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    protected abstract VB getViewBinding();

    protected abstract T getMPresenter();
}
