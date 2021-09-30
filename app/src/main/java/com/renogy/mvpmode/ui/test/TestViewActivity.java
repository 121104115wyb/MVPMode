package com.renogy.mvpmode.ui.test;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Create by 17474 on 2021/6/30.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class TestViewActivity extends BaseActivity<BasePresenter, ActivityTestBinding> {
    private List<String> testList = new ArrayList<>();

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityTestBinding getViewBinding() {
        return ActivityTestBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onViewCreate() {
        testList.add("强强强强");
        testList.add("wwww");
        testList.add("eeee");
        testList.add("rrrr");
        viewBinding.battery.setOnClickListener(v -> {
            startCommand();
        });
    }


    //开始发送命令
    private void startCommand() {
        Observable<String> data = Observable.fromIterable(testList);
        Observable<Long> time = Observable.interval(0, 2, TimeUnit.SECONDS, Schedulers.single());


        Observable.interval(0, 10, TimeUnit.SECONDS, Schedulers.single()).subscribe(aLong -> {
            Observable.zip(data, time, new BiFunction<String, Long, Object>() {

                @Override
                public Object apply(String s, Long aLong1) throws Throwable {

                    Log.d("Test", "apply s:" + s + "aLong1:" + aLong1.intValue() + "times:" + TimeUtils.getNowString(TimeUtils.getSafeDateFormat("yyyy-MM-dd HH:mm:ss.SSS")));

                    if (aLong1.intValue() == testList.size() - 1) {
                        //延时两秒去更新数据
                    }

                    return s;
                }
            }).subscribe(o -> {
                String testBean = o.toString();
                Observable.intervalRange(0, 4, 0, 500, TimeUnit.MILLISECONDS, Schedulers.single()).subscribe(aLong1 -> {
                    LogUtils.dTag("Test", "发送testBean:" + testBean + "---次数：" + aLong1.intValue());
                });
            });
        });


//        Observable.interval(0, 10, TimeUnit.SECONDS, Schedulers.single()).subscribe(aLong -> {
//                    Observable.zip(data, time, (BiFunction<String, Long, Object>) (s, aLong1) -> {
//                        Log.d("Test", "s:" + s + "along:" + aLong1.intValue() + "\ntimes:" + TimeUtils.getNowString(TimeUtils.getSafeDateFormat("yyyy-MM-dd HH:mm:ss.SSS")));
//                        return s;
//                    }).subscribe(o ->
//
//                                    Observable.interval(0, 500, TimeUnit.MILLISECONDS)
//
//
//                            Log.d("Test", "o:" + o.toString() + "\ntimes:" + TimeUtils.getNowString(TimeUtils.getSafeDateFormat("yyyy-MM-dd HH:mm:ss.SSS"))));
//
//
//                }
//
//        );


    }

    @Override
    protected void initData() {

    }
}
