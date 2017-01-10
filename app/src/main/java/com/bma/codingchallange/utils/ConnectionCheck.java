package com.bma.codingchallange.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class ConnectionCheck {

    public static boolean isConnectionAvailable(Context context) {
        if (context != null) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileNetWork = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return wifi.isConnected() || mobileNetWork.isConnected();
        } else {
            return false;
        }
    }
}