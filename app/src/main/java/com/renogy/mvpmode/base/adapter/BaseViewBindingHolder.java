package com.renogy.mvpmode.base.adapter;

import android.widget.TextView;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * @author Create by 17474 on 2021/4/29.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class BaseViewBindingHolder <VB extends ViewBinding> extends BaseViewHolder {
    public VB viewBind;

    public BaseViewBindingHolder(VB viewBind) {
        super(viewBind.getRoot());
        this.viewBind = viewBind;
    }

    public VB getViewBind() {
        return viewBind;
    }
}