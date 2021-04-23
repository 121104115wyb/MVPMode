package com.renogy.mvpmode.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.renogy.mvpmode.common.AppHelper;

import java.util.Locale;

/**
 * https://blankj.com/2019/06/29/only-one-code-change-language/
 * 语言切换
 * Describe:语言切换
 * 怎样使用在基类的中添加 重载方法 attachBaseContext
 * <p>
 * protected void attachBaseContext(Context newBase) {
 * super.attachBaseContext(LanguageUtils.initLanguage(newBase));
 * }
 * </p>
 * 默认跟随系统语言
 */
public class LanguageUtils {

    public static final String LANGUAGE_TYPE = "language_type";
    public static final String LANGUAGE_china = "language_china";
    public static final String LANGUAGE_japan = "language_japan";
    public static final String LANGUAGE_english = "language_english";
    public static final String LANGUAGE_default = "language_default";
    private static final String TAG = "LanguageUtils";

    public static Context initLanguage(Context context) {
        String type = AppHelper.getInstance().getLanguageType();
        Locale myLocale;
        MkvEditorUtils.encode(LANGUAGE_TYPE, type);
        if (type.equals(LANGUAGE_china)) {
            myLocale = new Locale("zh", Locale.CHINESE.getCountry());
        } else if (type.equals(LANGUAGE_japan)) {
            myLocale = new Locale("ja", Locale.JAPANESE.getCountry());
        } else if (type.equals(LANGUAGE_english)) {
            myLocale = new Locale("en", Locale.ENGLISH.getCountry());
        } else {
            MkvEditorUtils.encode(LANGUAGE_TYPE, LANGUAGE_english);
            myLocale = new Locale("en", Locale.ENGLISH.getCountry());
//            myLocale = getSysPreferredLocale();
        }
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(myLocale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            Locale.setDefault(myLocale);
            return context.createConfigurationContext(config);
        } else {
            config.locale = myLocale;
            resources.updateConfiguration(config, dm);
        }
        return context;
    }


    /**
     * 获取系统首选语言
     * <p>
     * 注意：该方法获取的是用户实际设置的不经API调整的系统首选语言
     *
     * @return
     */
    public static Locale getSysPreferredLocale() {
        Locale locale;
        //7.0以下直接获取系统默认语言
        if (Build.VERSION.SDK_INT < 24) {
            // 等同于context.getResources().getConfiguration().locale;
            locale = Locale.getDefault();
            // 7.0以上获取系统首选语言
        } else {
            /*
             * 以下两种方法等价，都是获取经API调整过的系统语言列表（可能与用户实际设置的不同）
             * 1.context.getResources().getConfiguration().getLocales()
             * 2.LocaleList.getAdjustedDefault()
             */
            // 获取用户实际设置的语言列表
            locale = LocaleList.getDefault().get(0);
        }
        return locale;
    }

    /**
     * 切换语言
     *
     * @param type 语言类型
     */
    public static void changeLanguage(String type) {
        if (TextUtils.isEmpty(type)) return;
        String localType = MkvEditorUtils.decodeString(LANGUAGE_TYPE);
        if (!localType.equals(type)) {
            MkvEditorUtils.encode(LANGUAGE_TYPE, type);
            AppUtils.relaunchApp(true);
            LogUtils.eTag(TAG, "切换语言类型: --原来为：" + localType + "---现在为：" + type);
        }
    }

}