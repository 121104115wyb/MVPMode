package com.renogy.mvpmode.widget.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NotificationUtils;
import com.renogy.mvpmode.MVPApp;
import com.renogy.mvpmode.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.blankj.utilcode.util.NotificationUtils.IMPORTANCE_DEFAULT;
import static com.blankj.utilcode.util.NotificationUtils.IMPORTANCE_HIGH;
import static com.blankj.utilcode.util.NotificationUtils.IMPORTANCE_LOW;
import static com.blankj.utilcode.util.NotificationUtils.IMPORTANCE_MIN;

/**
 * @author Create by 17474 on 2021/5/8.
 * Email： lishuwentimor1994@163.com
 * Describe：定义全局的通知栏
 * 分组定义自己的默认通道，分别对应不同的优先级
 * 需要在使用前初始化，我们这里初始化在Application中
 * 1.初始化：
 * NotificationHelper.getInstance().init();
 * 2.使用：定义符合自己需求的消息
 */
public class NotificationHelper {

    private static final String TAG = "NotificationHelper";

    //广告推送
    private final static String ADVERTISING_CHANNEL = "advertising_channel";
    //系统通知
    private final static String SYSTEM_CHANNEL = "system_channel";
    //重要消息通知
    private final static String NOTIFICATION_CHANNEL = "notification_channel";
    //订阅消息通知
    private final static String SUBSCRIBE_CHANNEL = "subscribe_channel";

    private final static List<NotificationChannel> notificationChannels = new ArrayList<>();
    //是否显示顶部的toast
    private boolean showNoticeToast = false;
    private int DEFAULT_NOTIFICATION_ID = 1;


    private static final NotificationUtils.ChannelConfig ADVERTISING_CHANNEL_CONFIG = new NotificationUtils.ChannelConfig(
            ADVERTISING_CHANNEL, "广告推送", IMPORTANCE_LOW
    ).setShowBadge(true);

    private static final NotificationUtils.ChannelConfig SYSTEM_CHANNEL_CONFIG = new NotificationUtils.ChannelConfig(
            SYSTEM_CHANNEL, "系统推送", IMPORTANCE_DEFAULT
    ).setShowBadge(true);

    private static final NotificationUtils.ChannelConfig NOTIFICATION_CHANNEL_CONFIG = new NotificationUtils.ChannelConfig(
            NOTIFICATION_CHANNEL, "关于我的消息", IMPORTANCE_HIGH
    ).setShowBadge(true).setLightColor(Color.RED).setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

    private static final NotificationUtils.ChannelConfig SUBSCRIBE_CHANNEL_CONFIG = new NotificationUtils.ChannelConfig(
            SUBSCRIBE_CHANNEL, "订阅消息", IMPORTANCE_MIN
    ).setShowBadge(true);

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = NOTIFICATION_CHANNEL_CONFIG.getNotificationChannel();
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannels.add(notificationChannel);
        }
        notificationChannels.add(SYSTEM_CHANNEL_CONFIG.getNotificationChannel());
        notificationChannels.add(SUBSCRIBE_CHANNEL_CONFIG.getNotificationChannel());
        notificationChannels.add(ADVERTISING_CHANNEL_CONFIG.getNotificationChannel());
    }

    private final static class LazyHolder {
        private final static NotificationHelper INSTANCE = new NotificationHelper();
    }

    public static NotificationHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    //初始话所有的消息通道，仅需一次即可；
    public void init() {
        NotificationManager notificationManager = (NotificationManager) MVPApp.getInstance().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannels(notificationChannels);
        }
    }

    /**
     * 有通知栏消息时是否显示，自定义的顶部toast消息
     * 默认不显示；
     * 当通知消息的通道已经有显示横幅的权限后，isShow设置未true不起作用
     * 我们可以单独使用 NoticeToast 来展示我们需要展示的信息
     * 详情请看 NoticeToast 使用方式
     *
     * @param isShow 默认不显示
     */
    public void setShowNoticeToast(boolean isShow) {
        this.showNoticeToast = isShow;
    }

    public boolean isShowNoticeToast() {
        return showNoticeToast;
    }

    public void sendNotification(String title, String content) {
        DEFAULT_NOTIFICATION_ID++;
        NotificationUtils.notify(DEFAULT_NOTIFICATION_ID, NOTIFICATION_CHANNEL_CONFIG, builder -> builder.setAutoCancel(true)
                .setSmallIcon(R.drawable.icon_home)
                .setContentText(content)
                .setContentTitle(title)
                .setTicker("新消息")
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setPriority(Notification.PRIORITY_HIGH)
                .build());
        if (showNoticeToast) {
            new NoticeToast.NoticeToastBuilder()
                    .setContent(content)
                    .setTitle(title)
                    .setNotificationId(DEFAULT_NOTIFICATION_ID)
                    .build().show();
        }
    }

    public void sendSystem(String title, String content) {
        DEFAULT_NOTIFICATION_ID++;
        NotificationUtils.notify(DEFAULT_NOTIFICATION_ID, SYSTEM_CHANNEL_CONFIG, builder -> builder.setAutoCancel(true).setSmallIcon(R.drawable.icon_home).setContentText(content).setContentTitle(title).build());
    }

    /**
     * 校验当前通知渠道是否关闭
     *
     * @param channelId 渠道的id
     * @return 关闭 返回 true 否则 false
     */
    private boolean checkNotificationClose(String channelId) {
        if (TextUtils.isEmpty(channelId)) {
            LogUtils.eTag(TAG, "channelId 不能为空");
            return false;
        }
        NotificationManager manager = (NotificationManager) MVPApp.getInstance().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            if (channel == null) {
                LogUtils.eTag(TAG, "请检查您的channelId");
                return false;
            }
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                LogUtils.eTag(TAG, "通知已经关闭");
                return true;
            }
        } else {
            LogUtils.eTag(TAG, "当前版本不支持");
        }
        return false;
    }

    /**
     * 跳转到对应的通知渠道的设置页面
     *
     * @param channelId 通知的渠道的id
     * @param context   上下文
     */
    public void startOpenNotification(String channelId, Context context) {
        if (TextUtils.isEmpty(channelId) || context == null) return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
            context.startActivity(intent);
        }
    }

}
