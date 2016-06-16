package com.samay.silentinstall;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.telecom.Log;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by shaohua.li on 6/16/16.
 */
public class AppInstallService extends IntentService {
    private static final String TAG = "AppInstallService";

    public static final String ACTION_INSTALL = "com.tcl.ota.action.INSTALL";
    public static final String EXTRA_APP_ID = "appId";

    public AppInstallService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SHAOHUALI","action is "+intent.getAction());
        if (intent == null || intent.getAction() != ACTION_INSTALL) {
            return;
        }
        String path="/storage/emulated/0/silentinstall.apk";
        File apkFile = new File(path);
        PackageManager pm = getPackageManager();
        int status = PackageManager.INSTALL_FAILED_INVALID_APK;
        if (apkFile != null && apkFile.exists()) {
            try{
                mInstallObserver.reset();
                pm.installPackage(Uri.fromFile(apkFile), mInstallObserver,
                        PackageManager.INSTALL_REPLACE_EXISTING, null);
                mInstallObserver.waitForCompletion();
                status = mInstallObserver.getResult();
            }finally {
                Log.d("SHAOHUALI","status is "+status);
            }
        }else {
            Log.d("SHAOHUALI","apk is not exist");
        }

    }

    final InstallObserver mInstallObserver = new InstallObserver();

    class InstallObserver extends IPackageInstallObserver.Stub {
        final AtomicBoolean mDone = new AtomicBoolean();
        String mPackageName;
        int mResult;

        public void reset() {
            synchronized (mDone) {
                mDone.set(false);
            }
        }

        public void waitForCompletion() {
            synchronized (mDone) {
                while (mDone.get() == false) {
                    try {
                        mDone.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        int getResult() {
            return mResult;
        }

        @Override
        public void packageInstalled(String packageName, int returnCode)
                throws RemoteException {
            synchronized (mDone) {
                mResult = returnCode;
                mPackageName = packageName;
                mDone.set(true);
                mDone.notifyAll();
            }
        }
    }
}
