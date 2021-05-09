package com.renogy.mvpmode.widget.notification;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.renogy.mvpmode.MVPApp;
import com.renogy.mvpmode.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义Toast显示
 * 类似 notification 的横幅显示通知，
 */
public class NoticeToast {

    private NoticeToastBuilder mBuilder;
    private Toast mToast;

    public NoticeToast(NoticeToastBuilder builder) {
        this.mBuilder = builder;
        init();
    }

    //初始化
    @SuppressLint("ShowToast")
    private void init() {
        try {
            if (mBuilder == null) return;
            View mView = LayoutInflater.from(MVPApp.getInstance()).inflate(mBuilder.customView, null);
            if (mView == null) return;
            ((TextView) mView.findViewById(R.id.toastTitle)).setText(mBuilder.title);
            ((TextView) mView.findViewById(R.id.toastContent)).setText(mBuilder.content);
            ((ImageView) mView.findViewById(R.id.toastImg)).setImageResource(mBuilder.imgResId);

            mToast = Toast.makeText(mView.getContext(), "", mBuilder.duration);
            final Field mTNField = mToast.getClass().getDeclaredField("mTN");
            mTNField.setAccessible(true);
            final Object mTnObj = mTNField.get(mToast);
            if (mTnObj == null) return;
            Field paramField = mTnObj.getClass().getDeclaredField("mParams");
            paramField.setAccessible(true);
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) paramField.get(mTnObj);
            if (params == null) return;
            params.windowAnimations = mBuilder.windowAnimations;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

            mView.setOnClickListener(v -> {
                hideToast(mTnObj, mBuilder.notificationId);
                if (mBuilder.onToastViewClickListener != null) {
                    mBuilder.onToastViewClickListener.onToastClick(v);
                }
            });
            mToast.setView(mView);
            mToast.setGravity(Gravity.TOP, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            if (mBuilder != null) {
                mBuilder = null;
            }
            if (mToast != null) {
                mToast = null;
            }
        }
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }


    public static class NoticeToastBuilder {

        //点击事件
        private OnToastViewClickListener onToastViewClickListener;
        //通知栏的id,如果id<0，则不取消通知栏,类似自定义的toast
        private int notificationId;
        //显示的view,支持自定义，但是需要保持id一致
        private int customView = R.layout.custom_toast_view;
        //默认的窗口的动画
        private int windowAnimations = R.style.Notification_Toast;
        //显示时间
        private int duration = Toast.LENGTH_SHORT;

        private String title = "";

        private String content = "";

        private int imgResId = R.mipmap.ic_launcher;

        public NoticeToast build() {
            return new NoticeToast(this);
        }

        public NoticeToastBuilder setOnToastViewClickListener(OnToastViewClickListener onToastViewClickListener) {
            this.onToastViewClickListener = onToastViewClickListener;
            return this;
        }

        public NoticeToastBuilder setNotificationId(int notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public NoticeToastBuilder setCustomView(int customView) {
            this.customView = customView;
            return this;
        }

        public NoticeToastBuilder setWindowAnimations(int windowAnimations) {
            this.windowAnimations = windowAnimations;
            return this;
        }

        public NoticeToastBuilder setDuration(int duration) {
            this.duration = duration == Toast.LENGTH_LONG || duration == Toast.LENGTH_SHORT ? duration : Toast.LENGTH_SHORT;
            return this;
        }

        public NoticeToastBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public NoticeToastBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public NoticeToastBuilder setImgResId(int imgResId) {
            this.imgResId = imgResId;
            return this;
        }
    }


    private void hideToast(Object mTnObj, int notificationId) {
        try {
            if (notificationId > 0) {
                NotificationManager manager = (NotificationManager) MVPApp.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notificationId);
            }
            Method handleHide = mTnObj.getClass()
                    .getDeclaredMethod("handleHide", (Class<?>[]) null);
            handleHide.setAccessible(true);
            handleHide.invoke(mTnObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    interface OnToastViewClickListener {
        void onToastClick(View view);
    }

}
