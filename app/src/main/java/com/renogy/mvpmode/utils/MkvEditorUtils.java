package com.renogy.mvpmode.utils;

import android.os.Parcelable;

import com.renogy.mvpmode.MVPApp;
import com.tencent.mmkv.MMKV;

import java.util.Collections;
import java.util.Set;

/**
 * @author Create by 17474 on 2021/3/15.
 * Email： lishuwentimor1994@163.com
 * Describe：mkv工具类
 */
public class MkvEditorUtils {

    private static MMKV MKV_EDITOR = null;

    private final static class LazyHolder {
        public final static MkvEditorUtils INSTANCE = new MkvEditorUtils();
    }

    public static MkvEditorUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init() {
        MMKV.initialize(MVPApp.getInstance());
        MKV_EDITOR = MMKV.defaultMMKV();
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key your keys
     * @param object your data
     */
    public static void encode(String key, Object object) {
        if (object instanceof String) {
            MKV_EDITOR.encode(key, (String) object);
        } else if (object instanceof Integer) {
            MKV_EDITOR.encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            MKV_EDITOR.encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            MKV_EDITOR.encode(key, (Float) object);
        } else if (object instanceof Long) {
            MKV_EDITOR.encode(key, (Long) object);
        } else if (object instanceof Double) {
            MKV_EDITOR.encode(key, (Double) object);
        } else if (object instanceof byte[]) {
            MKV_EDITOR.encode(key, (byte[]) object);
        } else {
            MKV_EDITOR.encode(key, object.toString());
        }
    }

    public static void encodeSet(String key, Set<String> sets) {
        MKV_EDITOR.encode(key, sets);
    }

    public static void encodeParcelable(String key, Parcelable obj) {
        MKV_EDITOR.encode(key, obj);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Integer decodeInt(String key) {
        return MKV_EDITOR.decodeInt(key, 0);
    }

    public static Double decodeDouble(String key) {
        return MKV_EDITOR.decodeDouble(key, 0.00);
    }

    public static Long decodeLong(String key) {
        return MKV_EDITOR.decodeLong(key, 0L);
    }

    public static Boolean decodeBoolean(String key) {
        return decodeBoolean(key, false);
    }

    public static Boolean decodeBoolean(String key, boolean value) {
        return MKV_EDITOR.decodeBool(key, value);
    }

    public static Float decodeFloat(String key) {
        return MKV_EDITOR.decodeFloat(key, 0F);
    }

    public static byte[] decodeBytes(String key) {
        return MKV_EDITOR.decodeBytes(key);
    }

    public static String decodeString(String key) {
        return MKV_EDITOR.decodeString(key, "");
    }

    public static Set<String> decodeStringSet(String key) {
        return MKV_EDITOR.decodeStringSet(key, Collections.<String>emptySet());
    }

    public static Parcelable decodeParcelable(String key) {
        return MKV_EDITOR.decodeParcelable(key, null);
    }


    /**
     * 包含key
     *
     * @param key 键值
     * @return 包含返回true 否则返回false
     */
    public static boolean containsKey(String key) {
        return MKV_EDITOR.containsKey(key);
    }


    /**
     * 删除key的集合
     *
     * @param strings key
     */
    public static void removeKeys(String... strings) {
        MKV_EDITOR.removeValuesForKeys(strings);
    }

    /**
     * 移除某个key对
     *
     * @param key 键值
     */
    public static void removeKey(String key) {
        MKV_EDITOR.removeValueForKey(key);
    }

    /**
     * 清除所有key
     */
    public static void clearAll() {
        MKV_EDITOR.clearAll();
    }

}
