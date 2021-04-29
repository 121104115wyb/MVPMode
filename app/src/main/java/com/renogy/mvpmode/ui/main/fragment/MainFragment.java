package com.renogy.mvpmode.ui.main.fragment;

import android.util.Log;

import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.AppUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.fragment.BaseFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.common.AppHelper;
import com.renogy.mvpmode.databinding.FragmentMainBinding;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：业务主页
 */
public class MainFragment extends BaseFragment<BasePresenter, FragmentMainBinding> {
    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentMainBinding getViewBinding() {
        return FragmentMainBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onViewCreate() {
        _viewBing.testText.setOnClickListener(v -> {
            AppHelper.getInstance().setLoginState(false);
            AppUtils.exitApp();
        });
        Log.d(TAG, "onViewCreate: ---MainFragment----");
    }

    @Override
    protected void initData() {

    }
}
