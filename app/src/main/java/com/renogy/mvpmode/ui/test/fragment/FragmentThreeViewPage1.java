package com.renogy.mvpmode.ui.test.fragment;

import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.base.fragment.ViewPage1LazyLoadFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentThreeBinding;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class FragmentThreeViewPage1 extends ViewPage1LazyLoadFragment<BasePresenter, FragmentThreeBinding> {


    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentThreeBinding getViewBinding() {
        return FragmentThreeBinding.inflate(getLayoutInflater(),bindView.getRoot(),true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected void onViewCreate() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentThree create");
    }

    @Override
    protected void fetchData() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentThree initData");
    }

}
