package com.renogy.mvpmode.utils;

import com.renogy.mvpmode.base.response.BaseResponse;
import com.renogy.mvpmode.data.exception.RxException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Create by 17474 on 2021/4/22.
 * Email： lishuwentimor1994@163.com
 * Describe：定义一些常用的观察者
 */
public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return flowAbleTransformer -> flowAbleTransformer
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxIoSchedulerHelper() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSingleSchedulerHelper() {
        return upstream -> upstream.subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return upstream -> upstream.flatMap((Function<BaseResponse<T>, ObservableSource<T>>) tBaseResponse ->
                BaseResponse.CODE_OK.equals(tBaseResponse.getCode())
                        && tBaseResponse.getData() != null
                        ? createData(tBaseResponse.getData())
                        : Observable.error(new RxException(tBaseResponse)));
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResultWithIoScheduler() {
        return upstream -> upstream.flatMap((Function<BaseResponse<T>, ObservableSource<T>>) tBaseResponse ->
                BaseResponse.CODE_OK.equals(tBaseResponse.getCode())
                        && tBaseResponse.getData() != null
                        ? createData(tBaseResponse.getData())
                        : Observable.error(new RxException(tBaseResponse))).compose(rxIoSchedulerHelper());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResultWithSingleScheduler() {
        return upstream -> upstream.flatMap((Function<BaseResponse<T>, ObservableSource<T>>) tBaseResponse ->
                BaseResponse.CODE_OK.equals(tBaseResponse.getCode())
                        && tBaseResponse.getData() != null
                        ? createData(tBaseResponse.getData())
                        : Observable.error(new RxException(tBaseResponse))).compose(rxSingleSchedulerHelper());
    }

    /**
     * 退出登录返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
//    public static <T> ObservableTransformer<BaseResponse<T>, T> handleLogoutResult() {
//        return httpResponseObservable ->
//                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
//                    if (BaseResponse.CODE_OK.equals(baseResponse.getCode())
//                            && CommonUtils.isNetworkConnected()) {
//                        //创建一个非空数据源，避免onNext()传入null
//                        return createData(CommonUtils.cast(new LoginData()));
//                    } else {
//                        return Observable.error(new OtherException());
//                    }
//                });
//    }

    /**
     * 收藏返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
//    public static <T> ObservableTransformer<BaseResponse<T>, T> handleCollectResult() {
//        return httpResponseObservable ->
//                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
//                    if (BaseResponse.CODE_OK.equals(baseResponse.getCode())
//                            && CommonUtils.isNetworkConnected()) {
//                        //创建一个非空数据源，避免onNext()传入null
//                        return createData(CommonUtils.cast(new FeedArticleListData()));
//                    } else {
//                        return Observable.error(new OtherException());
//                    }
//                });
//    }

    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

}
