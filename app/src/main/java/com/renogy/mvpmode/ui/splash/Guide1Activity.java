package com.renogy.mvpmode.ui.splash;

import com.blankj.utilcode.util.BarUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.common.AppHelper;
import com.renogy.mvpmode.databinding.ActivityGuide1Binding;
import com.renogy.mvpmode.ui.login.LoginActivity;
import com.renogy.mvpmode.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DefaultObserver;

public class Guide1Activity extends BaseActivity<BasePresenter, ActivityGuide1Binding> {


    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityGuide1Binding getViewBinding() {
        return ActivityGuide1Binding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide1;
    }

    @Override
    protected void onViewCreate() {
        BarUtils.setStatusBarVisibility(this,false);
        BarUtils.setNavBarVisibility(this,false);
        if (!AppHelper.getInstance().loginState()) {
            startActivity1(LoginActivity.class);
            finish();
        } else {
            skipBtn();
            viewBinding.skipBtn.setOnClickListener(v -> {
                startActivity1(MainActivity.class);
                finish();
            });
        }
    }

    //直接跳转按钮
    private void skipBtn() {
        Observable.intervalRange(0L, 6L, 0, 1L, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        long result = 5 - aLong;
                        String timerText = "跳过广告：" + result;
                        viewBinding.timerBtn.setText(timerText);
                        if (result == 0) {
                            startActivity1(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initData() {

    }
}