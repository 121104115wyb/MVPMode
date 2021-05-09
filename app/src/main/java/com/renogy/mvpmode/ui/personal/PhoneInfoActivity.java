package com.renogy.mvpmode.ui.personal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.renogy.mvpmode.R;
import com.renogy.mvpmode.base.activity.BaseActivity;
import com.renogy.mvpmode.base.presenter.BasePresenter;
import com.renogy.mvpmode.databinding.ActivityPhoneInfoBinding;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 手机信息测试
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PhoneInfoActivity extends BaseActivity<BasePresenter, ActivityPhoneInfoBinding> implements EasyPermissions.PermissionCallbacks {

    /**
     * 获取子布局的bindingView
     *
     * @return 子布局的bindingView
     */
    @Override
    protected ActivityPhoneInfoBinding getViewBinding() {
        return ActivityPhoneInfoBinding.inflate(getLayoutInflater(), bindView.getRoot(), true);
    }

    @Override
    protected BasePresenter getMPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_info;
    }

    private final static String[] PHONE_STATE = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS};
    private final static int REQUEST_CODE = 0x123;

    @Override
    protected void onViewCreate() {
        boolean hasPhoneState = EasyPermissions.hasPermissions(PhoneInfoActivity.this, PHONE_STATE);
        if (!hasPhoneState) {
            EasyPermissions.requestPermissions(PhoneInfoActivity.this, getString(R.string.tx_permission), REQUEST_CODE, PHONE_STATE);
        } else {
            getPhoneState();
        }

        viewBinding.callPhone.setOnClickListener(v -> {
            callPhone();
        });
        viewBinding.sendMsg.setOnClickListener(v -> {
            sendMsg();
        });
    }

    private void callPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = IntentUtils.getCallIntent("15606139735");
            ActivityUtils.startActivity(intent);
        } else {
            EasyPermissions.requestPermissions(PhoneInfoActivity.this, getString(R.string.tx_permission), REQUEST_CODE, PHONE_STATE);
        }
    }

    private void sendMsg() {
        Intent intent = IntentUtils.getSendSmsIntent("15606139735", "尊敬的中国联通用户您好,恭喜您中奖了!");
        ActivityUtils.startActivity(intent);
    }

    private void getPhoneState() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String tel = tm.getLine1Number();
            boolean hasCarrierPrivileges = tm.hasCarrierPrivileges();

            String text = "手机类型:" + PhoneUtils.getPhoneType()
                    + "\n getIMSI:" + PhoneUtils.getIMSI()
                    + "\n getMEID:" + PhoneUtils.getMEID()
                    + "\n getSerial:" + PhoneUtils.getSerial()
                    + "\n getSimOperatorByMnc:" + PhoneUtils.getSimOperatorByMnc()
                    + "\n getSimOperatorName:" + PhoneUtils.getSimOperatorName()
                    + "\n 手机设备id" + PhoneUtils.getDeviceId()
                    + "\n getImeiOrMeid  true" + PhoneUtils.getImeiOrMeid(true)
                    + "\n 获取本机号码:" + tel
                    +"\n hasCarrierPrivileges:" +hasCarrierPrivileges;
            viewBinding.phoneText.setText(text);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        getPhoneState();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}