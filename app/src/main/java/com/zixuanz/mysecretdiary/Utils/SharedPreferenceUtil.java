package com.zixuanz.mysecretdiary.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Celleria on 2/15/17.
 */

public class SharedPreferenceUtil {

    /**
     * Get corresponding String type value by given key in SharedPreference named prefName.
     * @param prefName
     * @param key
     * @param defValue
     * @param context
     * @return String type value. If key is existed in SharedPreference, then return corresponding value. Otherwise return defValue.
     */
    public static String getValue(String prefName, String key, String defValue, Context context) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static boolean getValue(String prefName, String key, boolean defValue, Context context) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
    /**
     * Input key-value info into SharedPreference named prefName
     * @param prefName
     * @param key
     * @param value
     * @param context
     * @return Boolean type value. If input successfully, reuturn true. Otherwise false;
     */
    public static boolean setValue(String prefName, String key, String value, Context context){
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean setValue(String prefName, String key, boolean value, Context context){
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean(key, value);
        return editor.commit();
    }

}
