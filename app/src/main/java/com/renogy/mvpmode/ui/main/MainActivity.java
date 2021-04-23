package com.renogy.mvpmode.ui.main;


import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.data.bean.main.SearchData;
import com.renogy.mvpmode.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> {

    private final static String MAIN_BUNDLE_KEY = "main_key";
    private SearchData searchData;

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreate() {

    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        if (bundle != null) {
            searchData = GsonUtils.fromJson(bundle.getString(MAIN_BUNDLE_KEY), SearchData.class);
        }
    }

    @Override
    protected void initData() {
        if (searchData != null) {
            viewBinding.showData.setText(searchData.toString());
        } else {
            viewBinding.showData.setText("something wrong with bundle");
        }
    }


    public static void startMainActivity(String s) {
        if (TextUtils.isEmpty(s)) {
            startActivity1(MainActivity.class);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(MAIN_BUNDLE_KEY, s);
            startActivity1(MainActivity.class, bundle);
        }
    }
}