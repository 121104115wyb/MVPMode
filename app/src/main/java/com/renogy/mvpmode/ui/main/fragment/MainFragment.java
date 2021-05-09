package com.renogy.mvpmode.ui.main.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.AppUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.fragment.BaseFragment;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.common.AppHelper;
import com.renogy.mvpmode.data.server.LocalData;
import com.renogy.mvpmode.databinding.FragmentMainBinding;
import com.renogy.mvpmode.ui.dialog.NoticeDialog;
import com.renogy.mvpmode.ui.personal.NotificationActivity;
import com.renogy.mvpmode.ui.personal.PersonalAdapter;
import com.renogy.mvpmode.ui.personal.PhoneInfoActivity;
import com.renogy.mvpmode.ui.post.NewPostActivity;
import com.renogy.mvpmode.utils.XPopUtils;

/**
 * @author Create by 17474 on 2021/4/27.
 * Email： lishuwentimor1994@163.com
 * Describe：业务主页
 */
public class MainFragment extends BaseFragment<BasePresenter, FragmentMainBinding> {


    private PersonalAdapter personalAdapter;

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected FragmentMainBinding getViewBinding() {
        return FragmentMainBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onViewCreate() {
        personalAdapter = new PersonalAdapter();
        personalAdapter.setList(LocalData.getMainBeanList());
        _viewBing.personalMainRev.setLayoutManager(new LinearLayoutManager(getActivity()));
        _viewBing.personalMainRev.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        _viewBing.personalMainRev.setAdapter(personalAdapter);

        personalAdapter.setOnItemClickListener((adapter, view, position) -> {
            String content = getResources().getString(personalAdapter.getItem(position).getContentResId());
            showSnackBar(content);
            if (position == 0) {
                startActivity1(PhoneInfoActivity.class);
            } else if (position == 1) {
                startActivity1(NewPostActivity.class);
            } else if (position == 2) {
                startActivity1(NotificationActivity.class);

            } else if (position == 6) {
                XPopUtils.showNotice(mOnClickListener);
            }
        });

        Log.d(TAG, "onViewCreate: ---MainFragment----");
    }

    @Override
    protected void initData() {

    }

    public NoticeDialog.OnClickListener mOnClickListener = new NoticeDialog.OnClickListener() {
        @Override
        public void onConfirmClick(View view) {
            showToast("点击了确认按钮");
            AppHelper.getInstance().setLoginState(false);
            AppUtils.exitApp();
        }

        @Override
        public void onCancelClick(View view) {
            showToast("点击了取消按钮");
        }
    };
}
