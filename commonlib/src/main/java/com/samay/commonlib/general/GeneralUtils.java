package com.samay.commonlib.general;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import java.util.Locale;

/**
 * Created by shaohua.li on 8/11/16.
 */
public class GeneralUtils {
    private static volatile GeneralUtils instance = null;

    public static GeneralUtils getInstance() {
        if (instance == null) {
            synchronized (GeneralUtils.class) {
                if (instance == null) {
                    instance = new GeneralUtils();
                }
            }
        }
        return instance;
    }

    private String getWifiMac(Context mContext) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        final WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }


    private String getAndroidId(Context mContext) {
        String id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }

    public String getLanguage() {
        String mLanguage = Locale.getDefault().getLanguage();
        return mLanguage;
    }

    public String getAndroidVersion() {
        String mAndroidVersion = android.os.Build.VERSION.RELEASE;
        return mAndroidVersion;
    }
}
