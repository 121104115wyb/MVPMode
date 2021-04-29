package com.renogy.mvpmode.http.presenter;

import android.content.Context;

import com.renogy.mvpmode.base.contract.BusinessImpl;
import com.renogy.mvpmode.base.presenter.RxPresenter;
import com.renogy.mvpmode.common.AppHelper;
import com.renogy.mvpmode.data.api.ApiCase;
import com.renogy.mvpmode.data.bean.login.LoginData;
import com.renogy.mvpmode.data.bean.login.LoginRequest;
import com.renogy.mvpmode.http.observe.RxObserver;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：登录接口
 */
public class LoginPresenter extends RxPresenter<LoginPresenter.LoginPresenterView> {

    public LoginPresenter(Context context) {
        super(context);
    }


    public static LoginPresenter of(Context context) {
        return new LoginPresenter(context);
    }

    public void login(LoginRequest request) {
        //使用CompositeDisposable 管理Observable生命周期
//        addSubscribe(ApiCase.getInstance().login(request).subscribeWith(new RxObserver<LoginData>(mView) {
//            @Override
//            public void onRxSuccess(LoginData response) {
//
//            }
//
//            @Override
//            public void onRxError(Throwable throwable) {
//
//            }
//        }));
        //使用RxLife 管理Observable生命周期
        ApiCase.getInstance().login(request).compose(getLifecycleTransformer()).subscribe(new RxObserver<LoginData>(mView) {
            @Override
            public void onRxSuccess(LoginData response) {
                AppHelper.getInstance().setToken(response.getToken());
                AppHelper.getInstance().setUserName(request.getUserName());
                AppHelper.getInstance().setPassword(request.getPassword());
                AppHelper.getInstance().setLoginState(true);
                mView.loginSuccess();
            }

            @Override
            public void onRxError() {

            }
        });

    }


    public interface LoginPresenterView extends BusinessImpl {

        void loginSuccess();

    }
}
