package com.renogy.mvpmode.ui.main.fragment;

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NotificationUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.fragment.BaseFragment;
import com.renogy.mvpmode.common.AppConstants;
import com.renogy.mvpmode.common.AppRequestHelper;
import com.renogy.mvpmode.data.bean.main.BannerBean;
import com.renogy.mvpmode.data.bean.main.TopicResponse;
import com.renogy.mvpmode.databinding.FragmentHomeBinding;
import com.renogy.mvpmode.http.presenter.HomePresenter;
import com.renogy.mvpmode.ui.main.adapter.HomeAdapter;
import com.renogy.mvpmode.utils.ClickSkipUtils;
import com.renogy.mvpmode.utils.ImageLoadUtils;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：主页
 */
public class HomeFragment extends BaseFragment<HomePresenter, FragmentHomeBinding> implements HomePresenter.MainPresenterView {

    private int pageNum = 1;

    private HomeAdapter homeAdapter;

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentHomeBinding getViewBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected HomePresenter getMPresenter() {
        return HomePresenter.of(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onViewCreate() {

        boolean areNotificationsEnabled = NotificationUtils.areNotificationsEnabled();
        LogUtils.eTag("test", "通知 是否允许：" + areNotificationsEnabled);

//        BarUtils.setStatusBarColor(getActivity(),getResources().getColor(R.color.black_33));
        Log.d(TAG, "onViewCreate: ---HomeFragment----");
        homeAdapter = new HomeAdapter();
        homeAdapter.setAnimationEnable(true);
        homeAdapter.addChildClickListener();
        _viewBing.homeRev.setLayoutManager(new LinearLayoutManager(getContext()));
        _viewBing.homeRev.setAdapter(homeAdapter);
        //设置加载方式
        _viewBing.homeBanner.setAdapter((BGABanner.Adapter<ImageView, BannerBean.Banner>) (banner, itemView, model, position) -> {
            ImageLoadUtils.load(getActivity(), model.getUrl(), itemView);
        });

        //设置点击事件
        _viewBing.homeBanner.setDelegate((BGABanner.Delegate<ImageView, BannerBean.Banner>) (banner, itemView, model, position) -> {
            if (position > 1) {
                ClickSkipUtils.openInnerBrowser(model.getSkipUrl());
            } else {
                ClickSkipUtils.openBrowser(getContext(), model.getSkipUrl());
            }
        });

        //下拉刷新
        _viewBing.homeSmRef.setOnRefreshListener(v -> loadPost());
        //上拉加载
        _viewBing.homeSmRef.setOnLoadMoreListener(v -> loadMorePost());
        _viewBing.homeFragmentTitleBar.setLeftClick(view -> getActivity().finish());
        _viewBing.homeFragmentTitleBar.setRightClick(v -> showToast("点击了添加按钮"));
        mPresenter.loadBanner();
    }


    public void loadPost() {
        pageNum = 1;
        mPresenter.loadPost(pageNum, AppRequestHelper.loadRecommendPost(""));
    }

    //我们取最后一条数据的更新时间
    public void loadMorePost() {
        pageNum++;
        String theEndPostUpdateDatetime = "";
        if (homeAdapter != null && homeAdapter.getData().size() > 0) {
            int lastItem = homeAdapter.getData().size() - 1;
            theEndPostUpdateDatetime = homeAdapter.getData().get(lastItem).getPostUpdateDatetime();
        }
        mPresenter.loadPost(pageNum, AppRequestHelper.loadRecommendPost(theEndPostUpdateDatetime));
    }

    @Override
    protected void initData() {
        homeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (R.id.headImg == id) {
                showSnackBar("点击了头像");
            } else if (R.id.image1 == id) {
                showSnackBar("点击了图片1");
            } else if (R.id.image2 == id) {
                showSnackBar("点击了图片2");
            } else {

            }
        });
    }

    /**
     * 加载banner 成功
     *
     * @param banner banner 数据
     */
    @Override
    public void loadBannerSuccess(BannerBean banner) {
        if (banner == null || banner.getBannerList() == null) return;
        _viewBing.homeBanner.setAutoPlayAble(banner.getBannerList().size() > 1);
        _viewBing.homeBanner.setData(banner.getBannerList(), banner.getTipList());
    }

    /**
     * 加载帖子成功
     *
     * @param pageNum     第几页
     * @param contentList 帖子列表
     */
    @Override
    public void loadPostSuccess(int pageNum, List<TopicResponse.ContentListBean> contentList) {
        int dataCount = contentList.size();
        if (pageNum > 1) {
            homeAdapter.addData(contentList);
            if (dataCount < AppConstants.LIST_DEFAULT_COUNT) {
                _viewBing.homeSmRef.finishLoadMoreWithNoMoreData();
            } else {
                _viewBing.homeSmRef.finishLoadMore();
            }
        } else {
            homeAdapter.setList(contentList);
            if (dataCount < AppConstants.LIST_DEFAULT_COUNT) {
                _viewBing.homeSmRef.finishRefreshWithNoMoreData();
            } else {
                _viewBing.homeSmRef.finishRefresh();
            }
        }
    }

    /**
     * 加载帖子失败
     *
     * @param pageNum 第几页
     */
    @Override
    public void onLoadPostFailed(int pageNum) {
        if (pageNum > 1) {
            _viewBing.homeSmRef.finishLoadMoreWithNoMoreData();
        } else {
            _viewBing.homeSmRef.finishRefresh();
        }
    }
}
