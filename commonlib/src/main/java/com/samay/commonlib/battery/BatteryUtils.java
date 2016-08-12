package com.samay.commonlib.battery;

import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;

import com.samay.commonlib.R;

/**
 * Created by shaohua.li on 8/12/16.
 */
public class BatteryUtils {

    public static String getBatteryStatus(Resources res, Intent batteryChangedIntent) {
        final Intent intent = batteryChangedIntent;

        int plugType = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,
                BatteryManager.BATTERY_STATUS_UNKNOWN);
        String statusString;
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            int resId;
            if (plugType == BatteryManager.BATTERY_PLUGGED_AC) {
                resId = R.string.battery_info_status_charging_ac;
            } else if (plugType == BatteryManager.BATTERY_PLUGGED_USB) {
                resId = R.string.battery_info_status_charging_usb;
            } else if (plugType == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                resId = R.string.battery_info_status_charging_wireless;
            } else {
                resId = R.string.battery_info_status_charging;
            }
            statusString = res.getString(resId);
        } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
            statusString = res.getString(R.string.battery_info_status_discharging);
        } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
            statusString = res.getString(R.string.battery_info_status_not_charging);
        } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
            statusString = res.getString(R.string.battery_info_status_full);
        } else {
            statusString = res.getString(R.string.battery_info_status_unknown);
        }

        return statusString;
    }
}
