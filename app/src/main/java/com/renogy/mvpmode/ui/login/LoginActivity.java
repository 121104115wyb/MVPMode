package com.renogy.mvpmode.ui.login;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.renogy.mvpmode.ui.main.MainActivity;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.data.bean.main.SearchData;
import com.renogy.mvpmode.databinding.ActivityLoginBinding;
import com.renogy.mvpmode.http.presenter.LoginPresenter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DefaultObserver;

/**
 * @author Create by 17474 on 2021/4/23.
 * Email： lishuwentimor1994@163.com
 * Describe：登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter, ActivityLoginBinding> implements LoginPresenter.LoginPresenterView {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected LoginPresenter getMPresenter() {
        return LoginPresenter.of(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreate() {
        viewBinding.userName.setText("jkl@126.com");
        viewBinding.passWord.setText("qwer1234");
        viewBinding.btnLogin.setOnClickListener(v -> {
            login();
        });
    }

    private void login() {
        String userName = viewBinding.userName.getText().toString();
//        if (TextUtils.isEmpty(userName)) {
//            showToast("请输入用户名");
//            return;
//        }
        String password = viewBinding.passWord.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            showToast("请输入密码");
//            return;
//        }
        String appVersion = AppUtils.getAppVersionName();
        LoginRequest request = LoginRequest.of(userName, password, appVersion, "3");
        Observable.just(request)
                .map(loginRequest ->
                        !TextUtils.isEmpty(loginRequest.getPassword())
                                && !TextUtils.isEmpty(loginRequest.getUserName()) ? "" : "用户名或者密码不能为空")
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        if (TextUtils.isEmpty(s)) {
                            mPresenter.login(request);
                        } else {
                            showToast(s);
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

    @Override
    public void loginSuccess() {
        MainActivity.startMainActivity(GsonUtils.toJson(SearchData.of("德玛西亚之力", 520)));
    }
}
