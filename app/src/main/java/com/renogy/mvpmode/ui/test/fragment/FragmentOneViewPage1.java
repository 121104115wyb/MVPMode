package com.renogy.mvpmode.ui.test.fragment;

import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.base.fragment.ViewPage1LazyLoadFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.FragmentOneBinding;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：
 *
 * 生命周期。onAttach（Activity）->onAttach（Context）->onCreate->onActivityCreated->onResume->onpause->onstop
 * ->ondestoryView->onDestroy->onDetach
 *
 *
 *使用 ：setUserVisibleHint 方法来实现懒加载
 *
 */
public class FragmentOneViewPage1 extends ViewPage1LazyLoadFragment<BasePresenter, FragmentOneBinding> {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentOneBinding getViewBinding() {
        return FragmentOneBinding.inflate(getLayoutInflater(),bindView.getRoot(),true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected void onViewCreate() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentOne onViewCreate");
    }

    @Override
    protected void fetchData() {
        LogUtils.dTag("ViewPageFragmentActivity","FragmentOne initData");
    }

}
