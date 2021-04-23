package com.renogy.mvpmode.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.renogy.mvpmode.utils.LanguageUtils;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

/**
 * @author Create by 17474 on 2021/4/23.
 * Email： lishuwentimor1994@163.com
 * Describe：切换语言，这里处理一些最基础的事务
 */
public abstract class AbstractBaseActivity extends RxAppCompatActivity {

    //日志输出标志
    protected final String TAG = "MVP" + this.getClass().getSimpleName();

    //适配多语言版本
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtils.initLanguage(newBase));
    }


    protected static void startActivity1(final Class<? extends Activity> clz) {
        ActivityUtils.startActivity(clz, null);
    }

    protected static void startActivity1(@NonNull final Class<? extends Activity> clz,
                                 @Nullable final Bundle options) {
        ActivityUtils.startActivity(clz, options);
    }


    //绑定布局id
    protected abstract int getLayoutId();

    //视图创建
    protected abstract void onViewCreate();

    //初始化数据
    protected abstract void initData();
}
