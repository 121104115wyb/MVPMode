package com.renogy.mvpmode.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.contract.BaseImpl;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityBaseBinding;
import com.renogy.mvpmode.utils.RxUtils;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DefaultObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


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
        initParams(getIntent().getExtras());
        bindView = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(bindView.getRoot());
        viewBinding = getViewBinding();
        onViewCreate();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable.just(NetworkUtils.isConnected()).compose(RxUtils.rxSingleSchedulerHelper())
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        if (!aBoolean) {
                            showToast(getString(R.string.network_no_connect));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
        SnackbarUtils.with(bindView.snackView).setMessage("哈哈哈哈哈哈").show();
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
