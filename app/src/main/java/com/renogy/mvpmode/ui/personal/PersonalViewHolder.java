package com.renogy.mvpmode.ui.personal;

import android.widget.ImageView;
import android.widget.TextView;

import com.renogy.mvpmode.base.adapter.BaseViewBindingAdapter;
import com.renogy.mvpmode.base.adapter.BaseViewBindingHolder;
import com.renogy.mvpmode.databinding.AdapterPersonalItemViewBinding;

/**
 * @author Create by 17474 on 2021/5/6.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class PersonalViewHolder extends BaseViewBindingHolder<AdapterPersonalItemViewBinding> {

    public PersonalViewHolder(AdapterPersonalItemViewBinding viewBind) {
        super(viewBind);
    }

    private ImageView getStartImg() {
        return getViewBind().iconStart;
    }

    private TextView getContent() {
        return getViewBind().content;
    }

    private ImageView getEndImg() {
        return getViewBind().iconEnd;
    }
}
