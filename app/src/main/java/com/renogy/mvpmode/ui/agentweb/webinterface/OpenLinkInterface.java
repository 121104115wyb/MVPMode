package com.renogy.mvpmode.ui.agentweb.webinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.just.agentweb.AgentWeb;
import com.renogy.mvpmode.ui.agentweb.base.BaseAndroidInterface;


/**
 * @author wyb
 * Date :2020/3/27 0027 19:09
 * Description:卡券包的接口
 */
public class OpenLinkInterface extends BaseAndroidInterface {

    public OpenLinkInterface(AgentWeb agent, Context context) {
        super(agent, context);
    }

    @JavascriptInterface
    public void jumpStore() {
//        BusUtils.postSticky(Internallink_Acvty_Get_Link, GsonUtils.toJson(new IntentLinkBean(AppConstants.APP_STORE_BASE_URL, TYPE_OFFICAIL_MAIL)));
//        ActivityUtils.startActivity(new Intent(mContext, InternalLinkActivity.class));




    }




}
