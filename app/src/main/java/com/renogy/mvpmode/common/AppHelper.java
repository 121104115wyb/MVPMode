package com.renogy.mvpmode.common;

import android.text.TextUtils;

import com.renogy.mvpmode.utils.MkvEditorUtils;

import static com.renogy.mvpmode.common.AppConstants.APP_TOKEN;
import static com.renogy.mvpmode.common.AppConstants.APP_USERNAME;
import static com.renogy.mvpmode.utils.LanguageUtils.LANGUAGE_TYPE;
import static com.renogy.mvpmode.utils.LanguageUtils.LANGUAGE_default;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：应用的辅助类
 */
public class AppHelper {

    private final static class LazyHolder {
        private final static AppHelper INSTANCE = new AppHelper();
    }

    public static AppHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 设置token
     *
     * @param token 你的token
     */
    public void setToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            MkvEditorUtils.encode(APP_USERNAME, token);
        }
    }

    //获取app的token
    public String getToken() {
        return MkvEditorUtils.decodeString(APP_TOKEN);
    }

    //获取语言类型
    public String getLanguageType() {
        String type = LANGUAGE_default;
        if (MkvEditorUtils.containsKey(LANGUAGE_TYPE)) {
            type = MkvEditorUtils.decodeString(LANGUAGE_TYPE);
        }
        return TextUtils.isEmpty(type) ? LANGUAGE_default : type;
    }


}
