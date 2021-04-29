package com.renogy.mvpmode.ui.main.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.renogy.mvpmode.base.adapter.BaseViewBindingHolder;
import com.renogy.mvpmode.databinding.AdapterHomePostItemViewBinding;

import org.jetbrains.annotations.NotNull;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe：主页面的加载帖子列表的holder
 */
public class HomeAdapterHolder extends BaseViewBindingHolder<AdapterHomePostItemViewBinding> {

    public HomeAdapterHolder(AdapterHomePostItemViewBinding viewBind) {
        super(viewBind);
    }

    public TextView nickName() {
        return getViewBind().nickName;
    }

    public TextView topicText() {
        return getViewBind().topicText;
    }

    public TextView postContent() {
        return getViewBind().postContent;
    }

    public TextView likeCounts() {
        return getViewBind().likeCounts;
    }

    public TextView replyCounts() {
        return getViewBind().replyCounts;
    }

    public TextView viewCounts() {
        return getViewBind().viewCounts;
    }

    public ImageView headImg() {
        return getViewBind().headImg;
    }

    public ImageView postImg1() {
        return getViewBind().image1;
    }

    public ImageView postImg2() {
        return getViewBind().image2;
    }

    public ImageView postImg3() {
        return getViewBind().image3;
    }
}
