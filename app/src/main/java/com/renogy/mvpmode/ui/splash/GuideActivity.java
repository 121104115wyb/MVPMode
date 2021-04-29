package com.renogy.mvpmode.ui.splash;

import android.widget.ImageView;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.common.AppHelper;
import com.renogy.mvpmode.databinding.ActivityGuideBinding;
import com.renogy.mvpmode.ui.login.LoginActivity;
import com.renogy.mvpmode.ui.main.MainActivity;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * @author Create by 17474 on 2021/4/25.
 * Email： lishuwentimor1994@163.com
 * Describe：用户指导页面，这里可以初始化一些app全局的数据
 */
public class GuideActivity extends BaseActivity<BasePresenter, ActivityGuideBinding> {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityGuideBinding getViewBinding() {
        return ActivityGuideBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onViewCreate() {
        if (!AppHelper.getInstance().loginState()) {
            startActivity1(LoginActivity.class);
            finish();
        } else {
            BGALocalImageSize localImageSize = new BGALocalImageSize(1200, 1920, 320, 640);
            viewBinding.guideBanner.setData(localImageSize,
                    ImageView.ScaleType.FIT_XY,
                    R.drawable.gao1,
                    R.drawable.gao2,
                    R.drawable.gao3,
                    R.drawable.gao4);

            viewBinding.guideBanner.setEnterSkipViewIdAndDelegate(R.id.skipBanner, 0, new BGABanner.GuideDelegate() {
                @Override
                public void onClickEnterOrSkip() {
                    startActivity1(MainActivity.class);
                    finish();
                }
            });
        }
    }

    @Override
    protected void initData() {

    }
}
