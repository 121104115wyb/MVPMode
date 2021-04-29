package com.renogy.mvpmode.http.presenter;

import com.renogy.mvpmode.base.contract.BusinessImpl;
import com.renogy.mvpmode.base.presenter.RxPresenter;
import com.renogy.mvpmode.data.api.ApiCase;
import com.renogy.mvpmode.data.bean.main.BannerBean;
import com.renogy.mvpmode.data.bean.main.TopicResponse;
import com.renogy.mvpmode.data.server.LocalData;
import com.renogy.mvpmode.http.observe.RxObserver;
import com.trello.rxlifecycle4.components.support.RxFragment;

import java.util.List;
import java.util.Map;

/**
 * @author Create by 17474 on 2021/4/25.
 * Email： lishuwentimor1994@163.com
 * Describe：主页面
 */
public class HomePresenter extends RxPresenter<HomePresenter.MainPresenterView> {

    public HomePresenter(RxFragment context) {
        super(context);
    }

    public static HomePresenter of(RxFragment context) {
        return new HomePresenter(context);
    }

    //获取banner
    public void loadBanner() {
        ApiCase.getInstance().loadBanner().compose(getLifecycleTransformer()).subscribe(new RxObserver<BannerBean>(mView) {
            @Override
            public void onRxSuccess(BannerBean response) {
                mView.loadBannerSuccess(LocalData.loadBanner());
            }

            @Override
            public void onRxError( ) {

            }
        });
    }

    /**
     * 记载帖子列表
     *
     * @param pageNum 第几页
     * @param map     搜索条件
     */
    public void loadPost(int pageNum, Map<String, String> map) {
        ApiCase.getInstance().loadPostList(String.valueOf(pageNum), map).compose(getLifecycleTransformer()).subscribe(new RxObserver<TopicResponse>(mView) {
            @Override
            public void onRxSuccess(TopicResponse response) {
                if (response.getContentList() != null) {
                    mView.loadPostSuccess(pageNum, response.getContentList());
                } else {
                    mView.onLoadPostFailed(pageNum);
                }
                mView.showSnackBar("加载完成");
            }

            @Override
            public void onRxError() {
                mView.onLoadPostFailed(pageNum);
            }
        });
    }


    public interface MainPresenterView extends BusinessImpl {
        /**
         * 加载banner 成功
         *
         * @param banner banner 数据
         */
        void loadBannerSuccess(BannerBean banner);

        /**
         * 加载帖子成功
         *
         * @param pageNum     第几页
         * @param contentList 帖子列表
         */
        void loadPostSuccess(int pageNum, List<TopicResponse.ContentListBean> contentList);

        /**
         * 加载帖子失败
         *
         * @param pageNum 第几页
         */
        void onLoadPostFailed(int pageNum);
    }
}
