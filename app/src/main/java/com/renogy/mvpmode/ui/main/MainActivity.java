package com.renogy.mvpmode.ui.main;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.blankj.utilcode.util.GsonUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.data.bean.main.SearchData;
import com.renogy.mvpmode.databinding.ActivityMainBinding;
/**
 * rxjava 中 Observable 关键字符的意义
 * Observable.concat() 串行有序的输出多个数据源，返回值只能是基本数据类型
 * Observable.concatMap() 串行有序的输出数据源，不改变原来的数据源的位置，对复杂数据进行修改，拼接，过滤
 * Observable.flatMap() 并行无序输出数据源，对复杂数据进行修改，拼接，过滤，类似concatMap(),但是无序
 * Observable.just() 对多个数据进行依次输出，Observable.create() 的简化，不需要手写发射数据
 * Observable.create() 最基本的初始化被观察者
 * Observable.merge() 并行执行数据，或者耗时操作，不保证有序性,数据量小于4个
 * Observable.mergeArray() 并行执行数据，或者耗时操作，不保证有序性，数量大于4个
 * Observable.zip() 对多个数据源进行打包处理（两个数据进行拼接处理），但是最终的数据条数，取决于，最少的数据源的条数
 * Observable.filter()  对数据源进行过滤操作，只能接收符合条件的数据
 * Observable.map() 将数据转换成其他数据后在发射给observer（观察者）基本数据类型
 * .subscribeOn(Schedulers.io()) 订阅在io模式的线程调度下
 * .observeOn(AndroidSchedulers.mainThread()) 回调在Android主线程
 * subscribeOn以第一次调用为准。
 * observeOn以调用subscribe前的最后一次调用为准，每个subscribe单独计算。(就近原则)
 * 例如： observable 执行两次订阅
 * observable.subscribeOn(A).subscribeOn(B).observeOn(c) ;
 * observable.subscribeOn(C).subscribeOn(D).observeOn(e) ;
 * <p>
 * subscribeOn 的线程调度永远为A，除非observable对象改变
 * observeOn 的线程调度，第一次为 c ,第二次为 e ,可以改变
 */
public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> {

    private final static String MAIN_BUNDLE_KEY = "main_key";
    private SearchData searchData;

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreate() {

    }

    @Override
    protected void initParams(Bundle bundle) {
        super.initParams(bundle);
        if (bundle != null) {
            searchData = GsonUtils.fromJson(bundle.getString(MAIN_BUNDLE_KEY), SearchData.class);
        }
    }

    @Override
    protected void initData() {
//        if (searchData != null) {
//            String result = searchData.getName() + "\n" + searchData.getId();
//            viewBinding.showData.setText(result);
//        } else {
//            viewBinding.showData.setText("something wrong with bundle");
//        }

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(viewBinding.navView, navController);
    }


    public static void startMainActivity(String s) {
        if (TextUtils.isEmpty(s)) {
            startActivity1(MainActivity.class);
        } else {
            //intent 传值 不能太大,但是具体多少我们不知道，本人经过测试，大概在500kB时，就会出现闪退现象
            //所以这个范围还是挺大的，所以不用太纠结，Intent 传值不够的问题了，除非你没事，用intent传值图片
            Bundle bundle = new Bundle();
            bundle.putString(MAIN_BUNDLE_KEY, s);
            startActivity1(MainActivity.class, bundle);
        }
    }
}