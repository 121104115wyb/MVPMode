package com.renogy.mvpmode.ui.test.fragment;

import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.base.fragment.ViewPage1LazyLoadFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentFourBinding;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class FragmentFourViewPage1 extends ViewPage1LazyLoadFragment<BasePresenter, FragmentFourBinding> {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentFourBinding getViewBinding() {
        return FragmentFourBinding.inflate(getLayoutInflater(),bindView.getRoot(),true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected void onViewCreate() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentFour create");
    }

    @Override
    protected void fetchData() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentFour initData");
    }
}
