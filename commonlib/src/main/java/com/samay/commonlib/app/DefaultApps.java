package com.samay.commonlib.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.provider.Telephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class DefaultApps {

    private static DefaultApps instance=null;

    private static DefaultApps getInstance(){
        if(instance==null){
            synchronized (DefaultApps.class){
                if(instance==null){
                    instance=new DefaultApps();
                }
            }
        }
        return instance;
    }

    private String getDefaultCamera(Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
        ResolveInfo resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo == null) {
            return "";
        }
        if (resolveInfo.activityInfo.packageName.equals("android")) {
            return "";
        } else {
            return resolveInfo.activityInfo.packageName;
        }
    }

    private String DefaultLauncher(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PackageManager packageManager = context.getPackageManager();
        ArrayList<ResolveInfo> homeActivities = new ArrayList<ResolveInfo>();
        Class packageManagerClass = Class.forName("android.content.pm.PackageManager");
        Method method = packageManagerClass.getMethod("getHomeActivities", ArrayList.class);
        ComponentName currentDefaultHome = (ComponentName) method.invoke(packageManager, homeActivities);
        if (currentDefaultHome == null) {
            //Defect 2104410 modify by shaohuali@tcl.com begin
            if (homeActivities.size() == 1) {
                ResolveInfo defaultLauncherResolveInfo = homeActivities.get(0);
                ActivityInfo defaultLauncherActivityInfo = defaultLauncherResolveInfo.activityInfo;
                return defaultLauncherActivityInfo.packageName;
            } else {
                return "";
            }
            //Defect 2104410 modify by shaohuali@tcl.com end
        } else {
            return currentDefaultHome.getPackageName() == null ? "" : currentDefaultHome.getPackageName();
        }
    }


    private String DefaultPhone(Context context) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class defaultDialerManagerClass=Class.forName("android.telecom.DefaultDialerManager");
        Method method=defaultDialerManagerClass.getMethod("getDefaultDialerApplication",Context.class);
        return (String) method.invoke(defaultDialerManagerClass.newInstance(),context);

//        return DefaultDialerManager.getDefaultDialerApplication(context) == null ? "" : DefaultDialerManager.getDefaultDialerApplication(context);
    }

    private String DefaultBrowser(Context context) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        PackageManager packageManager = context.getPackageManager();
        Class packageManagerClass = Class.forName("android.content.pm.PackageManager");
        Method method=packageManagerClass.getMethod("getDefaultBrowserPackageName", int.class);
        Class userHandleClass=Class.forName("android.os.UserHandle");
        Method method1=userHandleClass.getMethod("myUserId");
        int userId= (int) method1.invoke(userHandleClass.newInstance());
        return (String) method.invoke(packageManager,userId);
    }

    private String DefaultSMS(Context context) {
        String name = Telephony.Sms.getDefaultSmsPackage(context);
        if (name == null) {
            name = "";
        }
        return name;
    }
}
