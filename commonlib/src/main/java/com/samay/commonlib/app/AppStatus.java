package com.samay.commonlib.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class AppStatus {
    private static AppStatus instance;

    public static AppStatus getInstance() {
        if (instance == null) {
            synchronized (AppStatus.class) {
                if (instance == null) {
                    instance = new AppStatus();
                }
            }
        }
        return instance;
    }

    private List<ResolveInfo> mLauncherAppList = null;

    public boolean isSystemApp(ApplicationInfo info) {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLauncherApp(PackageManager packageManager, ApplicationInfo info) {
        if (mLauncherAppList == null) {
            getLauncherApp(packageManager);
        }

        final int N = mLauncherAppList.size();
        for (int j = 0; j < N; j++) {
            String packageName = mLauncherAppList.get(j).activityInfo.packageName;
            if (info.packageName.equals(packageName)) {
//                AppLog.d(packageName + " is Launcher App");
                return true;
            }
        }
        return false;
    }

    public boolean isUserApp(ApplicationInfo info) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class applicationInfoClass = Class.forName("android.content.pm.ApplicationInfo");
        Method method = applicationInfoClass.getMethod("getCodePath");
        String codePath = (String) method.invoke(info);
        if (codePath.contains("/system/priv-app") || codePath.contains("/system/app") || codePath.contains("/system/custpack")) {
            return false;
        } else {
            return true;
        }
    }


    public void getLauncherApp(PackageManager packageManager) {
        Intent launchIntent = new Intent(Intent.ACTION_MAIN, null)
                .addCategory(Intent.CATEGORY_LAUNCHER);
        mLauncherAppList = packageManager.queryIntentActivities(launchIntent,
                PackageManager.GET_DISABLED_COMPONENTS);
    }

    public static boolean isPackageExist(Context context, String packageName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }
}
