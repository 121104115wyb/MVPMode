package com.renogy.mvpmode.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.ui.agentweb.activity.OpenLinkActivity;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe: bannner 点击，跳转内部的webview，手机浏览器
 */
public class ClickSkipUtils {

    /**
     * 打开app外部的浏览器
     *
     * @param context 上下文
     * @param url     浏览器地址
     */
    @SuppressLint("QueryPermissionsNeeded")
    public static void openBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            //ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.text_select_browser)));
        } else {
            // GlobalMethod.showToast(context, "链接错误或无浏览器");
            ToastUtils.showShort(context.getResources().getString(R.string.text_no_browser));
        }
    }


    /**
     * 打开内部的浏览器
     */
    public static void openInnerBrowser(String url) {
        if (TextUtils.isEmpty(url)) {
            ToastUtils.showLong("url is empty");
        } else {
            OpenLinkActivity.startLinkActivity(url);
        }
    }

}
