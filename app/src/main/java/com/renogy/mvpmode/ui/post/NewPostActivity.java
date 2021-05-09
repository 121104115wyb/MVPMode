package com.renogy.mvpmode.ui.post;


import android.Manifest;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityNewPostBinding;
import com.renogy.mvpmode.ui.dialog.NoticeDialog;
import com.renogy.mvpmode.ui.post.adapter.PostImgAdapter;
import com.renogy.mvpmode.utils.ApplyPermissionUtils;
import com.renogy.mvpmode.utils.MediaResultUtils;
import com.renogy.mvpmode.utils.XPopUtils;
import com.zhihu.matisse.Matisse;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.renogy.mvpmode.ui.post.adapter.PostImgAdapter.MAX_IMG_COUNTS;
import static com.renogy.mvpmode.utils.ApplyPermissionUtils.REQUEST_IMG;
import static com.renogy.mvpmode.utils.ApplyPermissionUtils.REQUEST_VIDEO;

/**
 * 发布帖子的页面
 * 1.测试学习使用，easyPermission 适配Android10，11
 * 2.测试使用知乎的图片选择框架
 */
public class NewPostActivity extends BaseActivity<BasePresenter, ActivityNewPostBinding> implements EasyPermissions.PermissionCallbacks {

    private final static String[] REQUEST_MEDIA = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private final static int REQUEST_MEDIA_CODE = 0x999;

    private final static String[] REQUEST_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};
    private final static int REQUEST_LOCATION_CODE = REQUEST_MEDIA_CODE + 1;


    private PostImgAdapter postImgAdapter;
    private int maxSelect = MAX_IMG_COUNTS;
    private boolean singleShow = false;

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityNewPostBinding getViewBinding() {
        return ActivityNewPostBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_post;
    }

    @Override
    protected void onViewCreate() {

    }

    @Override
    protected void initData() {
        postImgAdapter = new PostImgAdapter();
        viewBinding.selectRev.setLayoutManager(new GridLayoutManager(this, 3));
        postImgAdapter.setDefault();
        viewBinding.selectRev.setAdapter(postImgAdapter);
        viewBinding.changeType.setOnClickListener(v ->
                singleShow = !singleShow
        );
        viewBinding.takePhoto.setOnClickListener(v -> requestMedia());
        viewBinding.selectMedia.setOnClickListener(v -> requestMedia());
        viewBinding.requestLocation.setOnClickListener(v -> requestLocation());

        postImgAdapter.setOnItemClickListener((adapter, view, position) -> {
            showToast("点击了第" + position + "个");
            String url = postImgAdapter.getItem(position).getPath();
            //查看大图的弹窗
            if (postImgAdapter.getItem(position).isAdd()) {
                requestMedia();
            } else {
                if (singleShow) {
                    MediaResultUtils.getInstance().showImage(NewPostActivity.this, view.findViewById(R.id.postItemImg), url);
                } else {
                    if (postImgAdapter.getImages() != null) {
                        MediaResultUtils.getInstance().showImageList(NewPostActivity.this, view.findViewById(R.id.postItemImg), position, postImgAdapter.getImages(), viewBinding.selectRev);
                    } else {
                        showToast("没有图片");
                    }
                }
            }
        });
    }

    //请求权限
    private void requestMedia() {
        boolean hasCamera = EasyPermissions.hasPermissions(this, REQUEST_MEDIA);
        if (hasCamera) {
            ApplyPermissionUtils.requestImage(NewPostActivity.this, maxSelect);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_ask), REQUEST_MEDIA_CODE, REQUEST_MEDIA);
        }
    }

    //请求定位权限
    private void requestLocation() {
        boolean hasLocation = EasyPermissions.hasPermissions(this, REQUEST_LOCATION);
        if (hasLocation) {
            showSnackBar("您已经获取了定位权限");
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_ask), REQUEST_LOCATION_CODE, REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        for (String s : perms) {
            LogUtils.eTag("TAG", "--授权了权限 --:" + s);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        for (String s : perms) {
            LogUtils.eTag("TAG", "--拒绝了权限 --:" + s);
        }

        if (requestCode == REQUEST_MEDIA_CODE) {
            //如果有一些权限被永久的拒绝, 就需要转跳到 设置-->应用-->对应的App下去开启权限
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                new AppSettingsDialog.Builder(this)
                        .setTitle(R.string.tx_permission)
                        .setRationale(R.string.tx_permission_if)
                        //用于onActivityResult回调做其它对应相关的操作
                        .setRequestCode(10001)
                        .build()
                        .show();
            }
        } else if (requestCode == REQUEST_LOCATION_CODE) {
            XPopUtils.showLocationNotice(locationClick);
        } else {

        }
    }

    private NoticeDialog.OnClickListener locationClick = new NoticeDialog.OnClickListener() {
        @Override
        public void onConfirmClick(View view) {
            Intent intent = IntentUtils.getLaunchAppDetailsSettingsIntent(getPackageName());
            startActivity(intent);
        }

        @Override
        public void onCancelClick(View view) {
            showSnackBar("您点击了取消,将不能使用该功能");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMG:
                if (data != null) {
                    List<String> path = Matisse.obtainPathResult(data);
                    postImgAdapter.addImages(path);
                    maxSelect = MAX_IMG_COUNTS - postImgAdapter.getImgCounts();
//                    startCompress(path);
                }

                break;
            case REQUEST_VIDEO:
                break;
            default:
                break;
        }
    }

    private void startCompress(List<String> list) {
        MediaResultUtils.getInstance().startImgCompress(list, new MediaResultUtils.CompressTask.OnCompressCallBack() {
            @Override
            public void onCompressSuccess(List<String> list) {
//                dismissLoading(200);
//                topicImageAdapter.addImgPath(list);
            }

            @Override
            public void onCompressFailed(String msg) {
//                dismissLoading(200);
            }
        });
    }
}