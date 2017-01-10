package com.bma.codingchallange.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bma.codingchallange.BuildConfig;
import com.bma.codingchallange.application.App;
import com.bma.codingchallange.log.LPLog;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class Utils {

    private static final String VERSION_CODE_PREFS_KEY = "VCPK";

    public static boolean isVersionCodeUpdated() {
        SharedPreferences prefs = App.getAppContext().getSharedPreferences(LPLog.LPLogTAG,
                Context
                        .MODE_PRIVATE);
        int storedVersionCode = prefs.getInt(VERSION_CODE_PREFS_KEY, -1);

        if (storedVersionCode != BuildConfig.VERSION_CODE) {
            prefs.edit().putInt(VERSION_CODE_PREFS_KEY, BuildConfig.VERSION_CODE).apply();
            return true;
        } else {
            return false;
        }
    }

}
