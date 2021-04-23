package com.renogy.mvpmode.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.renogy.mvpmode.data.bean.user.UserData;

import org.litepal.LitePal;

import java.util.List;

/**
 * 本地数据的工具类
 */
public class LitePalUtils {
    /**********************************************************************************************************
     *******************************                     查询操作                  ****************************
     **********************************************************************************************************/
    //查询用户数据
    public static List<UserData> selectUserList(String userInnerId) {
        return LitePal.select().where("userInnerId = ?", userInnerId).find(UserData.class);
    }


    /**
     * 查询本地所有的用户信息
     *
     * @return 用户列表
     */
    public static List<UserData> findAllUserData() {
        return LitePal.findAll(UserData.class);
    }


    /**********************************************************************************************************
     *******************************                     删除操作                  ****************************
     **********************************************************************************************************/
    //删除所有的用户信息
    public static void deleteAllUserData() {
        LitePal.deleteAll(UserData.class);
    }

    /**
     * 按照条件删除数据用户信息
     *
     * @param versionCode 版本号
     * @param versionName 版本名称
     *                    如果 不成功返回-1
     */
    public static int deleteUserData(String versionCode, @NonNull String versionName) {
        if (TextUtils.isEmpty(versionCode) || TextUtils.isEmpty(versionName)) {
            return -1;
        }
        return LitePal.deleteAll(UserData.class, "versionCode = ? and versionName = ?", versionCode, versionName);
    }

}
