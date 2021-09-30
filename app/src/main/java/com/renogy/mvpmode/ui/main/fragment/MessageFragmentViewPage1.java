package com.renogy.mvpmode.ui.main.fragment;

import android.util.Log;

import com.renogy.mvpmode.base.fragment.CommonLazyLoadFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentMessageBinding;
import com.renogy.mvpmode.ui.test.ViewPageFragmentActivity;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：消息页面
 */
public class MessageFragmentViewPage1 extends CommonLazyLoadFragment<BasePresenter,FragmentMessageBinding> {


    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentMessageBinding getViewBinding() {
        return FragmentMessageBinding.inflate(getLayoutInflater(),bindView.getRoot(),true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected void onViewCreate() {
        Log.d(TAG, "onViewCreate: ----MessageFragment----");
        _viewBing.startToViewPagerActivity.setOnClickListener(v->{
            startActivity1(ViewPageFragmentActivity.class);
        });
    }

    @Override
    protected void fetchData() {
        Log.d(TAG, "MessageFragment: fetchData");



    }
}
