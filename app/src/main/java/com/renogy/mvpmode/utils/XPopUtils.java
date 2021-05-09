package com.renogy.mvpmode.utils;

import com.lxj.xpopup.XPopup;

import com.renogy.mvpmode.MVPApp;
import com.renogy.mvpmode.ui.dialog.NoticeDialog;

/**
 * @author Create by 17474 on 2021/5/7.
 * Email： lishuwentimor1994@163.com
 * Describe：弹窗工具类
 */
public class XPopUtils {

    public static void showNotice(NoticeDialog.OnClickListener onClickListener) {
        new XPopup.Builder(MVPApp.getInstance()).asCustom(new NoticeDialog.NoticeDialogBuilder().textContent("您确定要退出应用吗?").textTitle("提 示").textCancel("取 消").textConfirm("确 认").setOnClickListener(onClickListener).build()).show();
    }

    public static void showLocationNotice(NoticeDialog.OnClickListener onClickListener) {
        new XPopup.Builder(MVPApp.getInstance()).asCustom(new NoticeDialog.NoticeDialogBuilder().textContent("应用需要请求定位权限,为了您的方便使用,请您点击确认跳转到设置页面,选择同意定位权限!").textTitle("定位权限提示").textCancel("取 消").textConfirm("确 认").setOnClickListener(onClickListener).build()).show();
    }

}
