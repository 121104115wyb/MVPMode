package com.renogy.mvpmode.ui.test;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityViewPageFragmentBinding;
import com.renogy.mvpmode.ui.test.adapter.ViewPage1FragmentPagerAdapter;
import com.renogy.mvpmode.ui.test.fragment.FragmentFourViewPage1;
import com.renogy.mvpmode.ui.test.fragment.FragmentOneViewPage1;
import com.renogy.mvpmode.ui.test.fragment.FragmentThreeViewPage1;
import com.renogy.mvpmode.ui.test.fragment.FragmentTwoViewPage1;

import java.util.ArrayList;
import java.util.List;


/**
 * 使用
 */

public class ViewPageFragmentActivity extends BaseActivity<BasePresenter, ActivityViewPageFragmentBinding> {
    private final List<Fragment> FRAGMENTS = new ArrayList<>();

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityViewPageFragmentBinding getViewBinding() {
        return ActivityViewPageFragmentBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onViewCreate() {
        FRAGMENTS.add(new FragmentOneViewPage1());
        FRAGMENTS.add(new FragmentTwoViewPage1());
        FRAGMENTS.add(new FragmentThreeViewPage1());
        FRAGMENTS.add(new FragmentFourViewPage1());
        //behavior 不同我们使用的懒加载模式也不同
        //ViewPage1FragmentPagerAdapter fragmentPagerAdapter = new ViewPage1FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);

        ViewPage1FragmentPagerAdapter fragmentPagerAdapter = new ViewPage1FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentPagerAdapter.setFragments(FRAGMENTS);
        viewBinding.viewPage1.setAdapter(fragmentPagerAdapter);
        viewBinding.viewPage1.setOffscreenPageLimit(4);




    }

    @Override
    protected void initData() {
        viewBinding.viewPage1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewBinding.viewPage1BottomNav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewBinding.viewPage1BottomNav.setOnNavigationItemSelectedListener(item -> {
            int menuId = item.getItemId();
            if (menuId == R.id.meinv) {
                viewBinding.viewPage1.setCurrentItem(0);
            } else if (menuId == R.id.badBoy) {
                viewBinding.viewPage1.setCurrentItem(2);
            } else if (menuId == R.id.add) {
                viewBinding.viewPage1.setCurrentItem(1);
            } else if (menuId == R.id.music) {
                viewBinding.viewPage1.setCurrentItem(3);
            }
            return false;
        });

    }
}