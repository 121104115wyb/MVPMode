package com.renogy.mvpmode.ui.post.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.adapter.BaseViewBindingAdapter;
import com.renogy.mvpmode.data.bean.main.PostImgBean;
import com.renogy.mvpmode.databinding.AdapterPostImgItemViewBinding;
import com.renogy.mvpmode.ui.post.holder.PostImgHolder;
import com.renogy.mvpmode.utils.ImageLoadUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Create by 17474 on 2021/5/7.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class PostImgAdapter extends BaseViewBindingAdapter<PostImgBean, PostImgHolder> {

    public final static int MAX_IMG_COUNTS = 9;

    public PostImgAdapter() {
        super(R.layout.adapter_post_img_item_view);
    }

    @Override
    protected PostImgHolder getViewBinding(int viewType, LayoutInflater from, ViewGroup parent) {
        return new PostImgHolder(AdapterPostImgItemViewBinding.inflate(from, parent, false));
    }

    @Override
    protected void convert(@NotNull PostImgHolder postImgHolder, PostImgBean s) {
        if (s.isAdd()) {
            postImgHolder.postImg().setImageResource(R.drawable.ic_add);
        } else {
            ImageLoadUtils.load(getContext(), s.getPath(), postImgHolder.postImg());
        }
    }

    public void setDefault() {
        if (getData().size() == 0) {
            addData(new PostImgBean("", true));
        }
    }

    public void addImages(List<String> list) {
        if (list == null || list.size() <= 0) return;
        int size = getData().size();
        if (size < 1 || size >= MAX_IMG_COUNTS) return;
        PostImgBean addBean = getItem(size - 1);
        if (!addBean.isAdd()) return;
        removeAt(size - 1);
        for (String s : list) {
            addData(new PostImgBean(s, false));
        }
        if (getData().size() < MAX_IMG_COUNTS) {
            addData(addBean);
        }
    }

    public int getImgCounts() {
        int size = getData().size();
        if (size < 1) return 0;
        if (size < MAX_IMG_COUNTS) {
            return size - 1;
        } else {
            return getItem(MAX_IMG_COUNTS - 1).isAdd() ? MAX_IMG_COUNTS - 1 : MAX_IMG_COUNTS;
        }
    }

    public List<Object> getImages() {
        if (getData().size() <= 0) return null;
        List<Object> list = new ArrayList<>();
        for (PostImgBean imgBean : getData()) {
            if (!imgBean.isAdd()){
                list.add(imgBean.getPath());
            }
        }
        return list;
    }
}
