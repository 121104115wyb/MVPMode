package com.renogy.mvpmode.base.presenter;

import android.content.Context;

import com.renogy.mvpmode.base.contract.BusinessImpl;

import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.components.RxActivity;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.trello.rxlifecycle4.components.support.RxFragmentActivity;

import java.lang.ref.SoftReference;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：
 */
public class RxPresenter<V extends BusinessImpl> implements BasePresenter<V> {

    private CompositeDisposable mCompositeDisposable;
    protected V mView;
    private SoftReference<Context> softReference;
    private RxFragment rxFragment;

    public <T> LifecycleTransformer<T> getLifecycleTransformer() {
        if (softReference == null) return null;
        if (rxFragment != null) {
            return rxFragment.bindUntilEvent(FragmentEvent.DESTROY);
        }
        Context context = softReference.get();
        if (context instanceof RxActivity) {
            return ((RxActivity) context).bindUntilEvent(ActivityEvent.DESTROY);
        } else if (context instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY);
        } else if (context instanceof RxFragmentActivity) {
            return ((RxFragmentActivity) context).bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            return null;
        }
    }

    public RxPresenter(Context context) {
        this.softReference = new SoftReference<>(context);
    }

    public RxPresenter(RxFragment context) {
        if (context != null) {
            this.softReference = new SoftReference<>(context.getActivity());
            this.rxFragment = context;
        }
    }

    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
//        unSubscribe();
        if (rxFragment != null) {
            rxFragment = null;
        }
        if (softReference != null) {
            softReference.clear();
        }
        if (mView != null) {
            mView = null;
        }
    }
}
