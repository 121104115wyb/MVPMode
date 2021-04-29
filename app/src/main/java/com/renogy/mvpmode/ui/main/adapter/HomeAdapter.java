package com.renogy.mvpmode.ui.main.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.adapter.BaseViewBindingAdapter;
import com.renogy.mvpmode.data.bean.main.TopicResponse;
import com.renogy.mvpmode.databinding.AdapterHomePostItemViewBinding;
import com.renogy.mvpmode.ui.main.holder.HomeAdapterHolder;
import com.renogy.mvpmode.utils.ImageLoadUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe：主页的适配器
 */
public class HomeAdapter extends BaseViewBindingAdapter<TopicResponse.ContentListBean, HomeAdapterHolder> {

    public HomeAdapter() {
        super(R.layout.adapter_home_post_item_view);
    }

    //添加子View点击事件
    public void addChildClickListener() {
        addChildClickViewIds(R.id.headImg, R.id.image1, R.id.image2);
    }

    @Override
    protected HomeAdapterHolder getViewBinding(int viewType, LayoutInflater from, ViewGroup parent) {
        return new HomeAdapterHolder(AdapterHomePostItemViewBinding.inflate(from, parent, false));
    }

    @Override
    protected void convert(@NotNull HomeAdapterHolder holder, TopicResponse.ContentListBean contentListBean) {
        holder.nickName().setText(contentListBean.getUserNickName());
        holder.topicText().setText(contentListBean.getTopicName());
        holder.postContent().setText(contentListBean.getPostTitle());
        holder.likeCounts().setText(contentListBean.getPostPageVirtualLikes());
        holder.replyCounts().setText(contentListBean.getPostPageVirtualReplys());
        holder.viewCounts().setText(contentListBean.getPostPageVirtualViews());

        ImageLoadUtils.load(getContext(), contentListBean.getUserImage(), holder.headImg());
        if (TextUtils.isEmpty(contentListBean.getPostImg1())) {
            holder.postImg1().setVisibility(View.GONE);
        } else {
            ImageLoadUtils.load(getContext(), contentListBean.getPostImg1(), holder.postImg1());
            holder.postImg1().setVisibility(View.VISIBLE);
        }
        if (holder.postImg1().getVisibility() == View.GONE) {
            holder.postImg2().setVisibility(View.GONE);
            holder.postImg3().setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(contentListBean.getPostImg2())) {
                holder.postImg2().setVisibility(View.INVISIBLE);
            } else {
                ImageLoadUtils.load(getContext(), contentListBean.getPostImg2(), holder.postImg2());
            }
            if (TextUtils.isEmpty(contentListBean.getPostImg3())) {
                holder.postImg3().setVisibility(View.INVISIBLE);
            } else {
                ImageLoadUtils.load(getContext(), contentListBean.getPostImg3(), holder.postImg3());
            }
        }
    }

}
