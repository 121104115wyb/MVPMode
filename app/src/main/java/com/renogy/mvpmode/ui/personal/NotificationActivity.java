package com.renogy.mvpmode.ui.personal;

import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityNotificationBinding;
import com.renogy.mvpmode.widget.notification.NotificationHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DefaultObserver;

/**
 * @author Create by 17474 on 2021/5/8.
 * Email： lishuwentimor1994@163.com
 * Describe：使用rxjava测试通知栏的消息
 */
public class NotificationActivity extends BaseActivity<BasePresenter, ActivityNotificationBinding> {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityNotificationBinding getViewBinding() {
        return ActivityNotificationBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void onViewCreate() {

    }

    @Override
    protected void initData() {
        viewBinding.delayTimingSend.setOnClickListener(v -> {
            count1 = 0;
            delaySendMsg();


        });
        viewBinding.timingSend.setOnClickListener(v -> {
            count = 0;
            timingSendMsg();


        });
    }

    private int count = 0;
    private int count1 = 0;

    private void timingSendMsg() {
        Observable.timer(5L, TimeUnit.SECONDS).subscribe(new DefaultObserver<Long>() {
            @Override
            public void onNext(@NonNull Long aLong) {
                count++;
                LogUtils.eTag("send", "timingSendMsg每间隔3s发送一次通知，永远不停止 第 " + count + "次");
//                NotificationUtils.notify(count, new Utils.Consumer<NotificationCompat.Builder>() {
//                    @Override
//                    public void accept(NotificationCompat.Builder builder) {
//                        builder.setTicker("测试ticker").setContentTitle("ContentTitle").setContentText("ContentText")
//                                .setOngoing(true).setSmallIcon(R.drawable.icon_home).build();
//                    }
//                });

                NotificationHelper.getInstance().sendNotification("测试通知消息","我是德玛西亚之力");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void delaySendMsg() {
//        LogUtils.eTag("send", "延时3s发送第一次通知");
//        Observable.interval(3L, 3, TimeUnit.SECONDS).subscribe(new DefaultObserver<Long>() {
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                count1++;
//                LogUtils.eTag("send", "delaySendMsg每间隔3s发送一次通知，永远不停止 第 " + count1 + " 次");
//
//
////                NotificationUtils.areNotificationsEnabled()
//                NotificationUtils.notify(count1, new Utils.Consumer<NotificationCompat.Builder>() {
//                    @Override
//                    public void accept(NotificationCompat.Builder builder) {
//                        builder.setTicker("测试ticker").setContentTitle("ContentTitle").setContentText("ContentText,哈哈哈哈" + count1)
//                                .setOngoing(false).setAutoCancel(true).setSmallIcon(R.drawable.icon_home).build();
//                    }
//                });
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable.timer(5L, TimeUnit.SECONDS).subscribe(new DefaultObserver<Long>() {
            @Override
            public void onNext(@NonNull Long aLong) {
                count++;
                LogUtils.eTag("send", "timingSendMsg每间隔3s发送一次通知，永远不停止 第 " + count + "次");
//                NotificationUtils.notify(count, new Utils.Consumer<NotificationCompat.Builder>() {
//                    @Override
//                    public void accept(NotificationCompat.Builder builder) {
//                        builder.setTicker("测试ticker").setContentTitle("ContentTitle").setContentText("ContentText")
//                                .setOngoing(true).setSmallIcon(R.drawable.icon_home).build();
//                    }
//                });

                NotificationHelper.getInstance().sendSystem("测试系统消息","我是德玛西亚之力他爷爷");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
