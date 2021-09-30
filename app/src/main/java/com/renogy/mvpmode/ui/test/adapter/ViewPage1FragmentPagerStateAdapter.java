package com.renogy.mvpmode.ui.test.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author Create by 17474 on 2021/9/15.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class ViewPage1FragmentPagerStateAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public ViewPage1FragmentPagerStateAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

}
