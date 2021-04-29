package com.renogy.mvpmode.ui.main.fragment;

import android.util.Log;

import androidx.viewbinding.ViewBinding;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.fragment.BaseFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentMusicBinding;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：消息页面
 */
public class MessageFragment extends BaseFragment {


    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentMusicBinding getViewBinding() {
        return FragmentMusicBinding.inflate(getLayoutInflater(),bindView.getRoot(),true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void onViewCreate() {
        Log.d(TAG, "onViewCreate: ----MessageFragment----");
    }

    @Override
    protected void initData() {

    }
}
