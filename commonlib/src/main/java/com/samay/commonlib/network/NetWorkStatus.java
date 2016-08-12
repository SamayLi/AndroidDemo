package com.samay.commonlib.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class NetWorkStatus {
    private static volatile NetWorkStatus instance = null;
    public static NetWorkStatus getInstance(){
        if(instance==null){
            synchronized (NetWorkStatus.class){
                if(instance==null){
                    instance=new NetWorkStatus();
                }
            }
        }
        return instance;
    }

    public boolean isWifiConnected(Context context) {
        boolean isConnected = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            isConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return isConnected;
    }

    public boolean isNetworkAvailable(Context context) {
        boolean isConnected = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }

    public boolean isMobileConnected(Context context){
        boolean isConnected = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            isConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return isConnected;
    }

}
