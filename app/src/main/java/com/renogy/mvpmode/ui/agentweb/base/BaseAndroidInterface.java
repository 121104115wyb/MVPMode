package com.renogy.mvpmode.ui.agentweb.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.just.agentweb.AgentWeb;

/**
 * @author wyb
 * Date :2019/12/30 0030 21:48
 * Description:定义你的业务交互接口
 */
public class BaseAndroidInterface {

    protected AgentWeb mAgent;
    protected Context mContext;
    protected String s;
    protected final static String TAG = "js_bridge";
    protected Handler mDeliver = new Handler(Looper.getMainLooper());

    public BaseAndroidInterface(AgentWeb agent, Context context) {
        this(agent, context, "");
    }

    public BaseAndroidInterface(AgentWeb agent, Context context, String s) {
        this.mAgent = agent;
        this.mContext = context;
        this.s = s;
    }

    @Deprecated
    @JavascriptInterface
    public void hideSoftInput() {
        try {
            if (mContext != null) {
                boolean softInput = KeyboardUtils.isSoftInputVisible((Activity) mContext);
                if (softInput) {
                    KeyboardUtils.hideSoftInput((Activity) mContext);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void pageFinish() {
        try {
            if (mContext != null) {
                boolean softInput = KeyboardUtils.isSoftInputVisible((Activity) mContext);
                if (softInput) {
                    KeyboardUtils.hideSoftInput((Activity) mContext);
                }
                ((Activity) mContext).finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void sendDebugMsg(String s) {
        mDeliver.post(() -> {
            LogUtils.eTag(TAG, "打印前端发送给移动端的数据：" + s);
        });
    }
}
