package com.renogy.mvpmode.base.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Create by 17474 on 2021/4/29.
 * Email： lishuwentimor1994@163.com
 * Describe：使用ViewBinding 封装
 */
public abstract class BaseViewBindingAdapter<T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> {

    public BaseViewBindingAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseViewBindingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @NotNull
    @Override
    protected VH onCreateDefViewHolder(@NotNull ViewGroup parent, int viewType) {
        return  getViewBinding(viewType,LayoutInflater.from(getContext()),parent);
    }



    protected abstract VH getViewBinding(int viewType, LayoutInflater from, ViewGroup parent);



}
