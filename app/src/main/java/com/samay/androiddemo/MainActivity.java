package com.samay.androiddemo;

import android.Manifest;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.samay.commonlib.permission.PermissionActivity;

import java.sql.Struct;
import java.util.ArrayList;

public class MainActivity extends PermissionActivity {

    public static final String SCHEME = "content";
    public static final String AUTHORITY = "com.tct.diagnostics.provider.diagnosticsinfo";
    public static final String TABLE_NAME = "diagnostics";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY).path(TABLE_NAME).build();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    protected ArrayList<String> getPermissionList() {
        ArrayList<String> permissions = new ArrayList();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        return permissions;
    }

    @Override
    protected void finishPermissions() {
        Toast.makeText(this,"finish permission",Toast.LENGTH_LONG).show();
        ContentValues values=new ContentValues();
        values.put("action","SPLICE");
        values.put("com.tct.launcher",String.valueOf(1000));
        values.put("com.tct.contact",String.valueOf(3000));
        ContentProviderClient provider   = getContentResolver().acquireUnstableContentProviderClient(AUTHORITY);
        try {
            if(provider!=null){
                provider.update(CONTENT_URI, values, null, null);
            }
        } catch (IllegalArgumentException e) {
            Log.d("LSH","llegalArgumentException Exception: " + e);
        } catch (RemoteException e) {
            Log.d("LSH", "write2DB() RemoteException Exception: " + e);
        } finally {
            if(provider!=null){
                provider.release();
            }
        }

    }

}
