package com.renogy.mvpmode.ui.personal;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.adapter.BaseViewBindingAdapter;
import com.renogy.mvpmode.data.bean.personal.PersonalMainBean;
import com.renogy.mvpmode.databinding.AdapterPersonalItemViewBinding;

import org.jetbrains.annotations.NotNull;

/**
 * @author Create by 17474 on 2021/5/6.
 * Email： lishuwentimor1994@163.com
 * Describe：个人中心适配器
 */
public class PersonalAdapter extends BaseViewBindingAdapter<PersonalMainBean,PersonalViewHolder> {

    public PersonalAdapter() {
        super(R.layout.adapter_personal_item_view);
    }

    @Override
    protected PersonalViewHolder getViewBinding(int viewType, LayoutInflater from, ViewGroup parent) {
        return new PersonalViewHolder(AdapterPersonalItemViewBinding.inflate(from,parent,false));
    }

    @Override
    protected void convert(@NotNull PersonalViewHolder personalViewHolder, PersonalMainBean personalMainBean) {
        personalViewHolder.getViewBind().content.setText(getContext().getResources().getString(personalMainBean.getContentResId()));
        personalViewHolder.getViewBind().iconStart.setImageResource(personalMainBean.getStartImgRes());
        personalViewHolder.getViewBind().iconEnd.setImageResource(personalMainBean.getEndImgRes());
    }
}
