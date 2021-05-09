package com.renogy.mvpmode.utils;

import android.app.Activity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.renogy.mvpmode.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Set;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

/**
 * @author Create by 17474 on 2021/3/2.
 * Email： lishuwentimor1994@163.com
 * Describe：请求权限
 */
public class ApplyPermissionUtils {

    public final static int REQUEST_IMG = 0x99;
    public final static int REQUEST_VIDEO = REQUEST_IMG + 1;

    /**
     * 请求媒介
     *
     * @param context     上下文
     * @param mimeTypes   媒介类型
     * @param maxSelect   最大可选
     * @param requestCode 请求码
     * @param capture     是否使用拍照功能
     */
    private static void requestMedium(Activity context, Set<MimeType> mimeTypes, boolean capture, int maxSelect, int requestCode) {
        Matisse.from(context).choose(mimeTypes, false)
                .showSingleMediaType(true)
                .capture(capture)
                .captureStrategy(new CaptureStrategy(true, context.getString(R.string.text_provider_authorities)))
                .theme(R.style.Matisse_mvpMode)
//    R.style.Matisse_Zhihu
                .countable(true)
                .maxSelectable(maxSelect)
                .restrictOrientation(SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.8f)
                .imageEngine(new GlideEngine()).originalEnable(false).maxOriginalSize(5)
                .autoHideToolbarOnSingleTap(true)
                .forResult(requestCode);
    }

    public static void requestImage(Activity activity,int maxSelect){
        requestMedium(activity, MimeType.ofImage(), false, maxSelect, REQUEST_IMG);
    }


//    public static void showImg(Activity context, int maxSelect) {
//        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE).callback(new PermissionUtils.SimpleCallback() {
//            @Override
//            public void onGranted() {
//                requestMedium(context, MimeType.ofImage(), true, maxSelect, REQUEST_IMG);
//            }
//
//            @Override
//            public void onDenied() {
////                ToastUtils.showShort(context.getResources().getString(R.string.text_apply_permission));
//            }
//        }).request();
//    }
//
//
//    public static void showVideo(Activity context) {
//        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE, PermissionConstants.MICROPHONE).callback(new PermissionUtils.SimpleCallback() {
//            @Override
//            public void onGranted() {
//                requestMedium(context, MimeType.ofVideo(), false, 1, REQUEST_VIDEO);
//            }
//
//            @Override
//            public void onDenied() {
////                ToastUtils.showShort(context.getResources().getString(R.string.text_apply_permission));
//            }
//        }).request();
//    }

    /**
     * 录制视频
     *
     * @param callback 回调
     */
    public static void recordVideo(PermissionUtils.SimpleCallback callback) {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE, PermissionConstants.MICROPHONE).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                if (callback != null) {
                    callback.onGranted();
                }
            }

            @Override
            public void onDenied() {
//                ToastUtils.showShort(R.string.text_apply_permission);
                if (callback != null) {
                    callback.onDenied();
                }
            }
        }).request();
    }

}
