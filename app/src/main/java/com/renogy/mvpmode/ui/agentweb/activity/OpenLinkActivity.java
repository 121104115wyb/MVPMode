package com.renogy.mvpmode.ui.agentweb.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityOpenLinkBinding;
import com.renogy.mvpmode.ui.agentweb.base.BaseAgentWebActivity;
import com.renogy.mvpmode.ui.agentweb.webinterface.OpenLinkInterface;

/**
 * @author Create by 17474 on 2021/5/8.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class OpenLinkActivity extends BaseAgentWebActivity<BasePresenter, ActivityOpenLinkBinding> {
    private static final String URL_KEY = "url_key";
    private Bundle mBundle;

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) getViewBinding().container;
    }

    @Override
    protected ActivityOpenLinkBinding getViewBinding() {
        return ActivityOpenLinkBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_link;
    }

    @Override
    protected void initData() {
        if (mAgentWeb != null) {
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new OpenLinkInterface(mAgentWeb, this));
        }
    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        this.mBundle = bundle;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return linkUrl();
    }

    private String linkUrl() {
        return mBundle == null ? "" : mBundle.getString(URL_KEY);
    }

    public static void startLinkActivity(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL_KEY, url);
        startActivity1(OpenLinkActivity.class, bundle);
    }
}
