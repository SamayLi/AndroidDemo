package com.samay.commonlib.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by shaohua.li on 8/11/16.
 */
public abstract class PermissionActivity extends AppCompatActivity
{

    protected abstract int getLayoutId();
    protected abstract ArrayList<String> getPermissionList();
    protected abstract void finishPermissions();

    private ArrayList<String> permissions=null;
    private final static int CHECK_REQUEST_PERMISSION_RESULT=0X3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        permissions=getPermissionList();
        requestLocationPermission();
    }

    private void requestLocationPermission(){
        if (permissions.size() > 0) {
            Log.d("LSH","samamy permission size is "+permissions.size());
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]),
                    CHECK_REQUEST_PERMISSION_RESULT);
        } else {
            Log.d("LSH","samamy permission size is 0");
            finishPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CHECK_REQUEST_PERMISSION_RESULT) {
            Log.d("LSH","samamy onRequestPermissionsResult");
            finishPermissions();
        }
    }
}
