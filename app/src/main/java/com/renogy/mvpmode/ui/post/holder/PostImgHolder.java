package com.renogy.mvpmode.ui.post.holder;

import android.widget.ImageView;

import com.renogy.mvpmode.base.adapter.BaseViewBindingHolder;
import com.renogy.mvpmode.databinding.AdapterPostImgItemViewBinding;

/**
 * @author Create by 17474 on 2021/5/7.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class PostImgHolder extends BaseViewBindingHolder<AdapterPostImgItemViewBinding> {

    public PostImgHolder(AdapterPostImgItemViewBinding viewBind) {
        super(viewBind);
    }


    public ImageView postImg() {
        return getViewBind().postItemImg;
    }

}
