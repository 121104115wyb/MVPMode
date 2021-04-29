package com.renogy.mvpmode.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.renogy.mvpmode.R;

/**
 * @author Create by 17474 on 2021/4/29.
 * Email： lishuwentimor1994@163.com
 * Describe：glide 图片加载
 * <p>
 * 图片的url，我们不要采用本地路径加服务器地址的方式拼接，直接采用全部路径
 */
public class ImageLoadUtils {

    /*****************glide 默认使用缓存机制，但是加载Gif 图片时会造成oom，所以要关闭缓存机制*********/
    public static RequestOptions getDefaultOptions() {
        return getOptions(R.drawable.ic_image_holder, R.drawable.ic_image_error);
    }

    public static RequestOptions getOptions(@DrawableRes int holderRes, @DrawableRes int errorRes) {
        return new RequestOptions().centerCrop().error(errorRes)
                .placeholder(holderRes);
    }

    //加载原图
    public static void load(Context activity, String url, ImageView iv) {
        if (objectNull(activity, url, iv)) return;
        Glide.with(activity)
                .load(url)
                .apply(getDefaultOptions())
                .into(iv);
    }

    //加载圆形的图片
    public static void loadCircle(Context activity, String url, ImageView iv) {
        Glide.with(activity)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).apply(getDefaultOptions()))
                .into(iv);
    }


    //加载圆角图片
    public static void loadCorner(Context activity, String url, ImageView iv, int size) {
        if (objectNull(activity, url, iv)) return;
        Glide.with(activity)
                .load(url)
                .skipMemoryCache(true)
                //圆角半径
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(SizeUtils.dp2px(size))))
                .into(iv);
    }

    //判断图片路径是否为空和是否符合规则
    private static boolean objectNull(Context activity, String path, ImageView imageView) {
        return activity == null || TextUtils.isEmpty(path) || imageView == null;
    }


}
