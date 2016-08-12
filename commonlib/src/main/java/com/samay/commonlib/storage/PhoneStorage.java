package com.samay.commonlib.storage;

import android.content.Context;
import android.os.storage.StorageManager;
//import android.os.storage.VolumeInfo;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

/**
 * Created by shaohua.li on 8/10/16.
 */
public class PhoneStorage {
    private static PhoneStorage instance = null;

    public static PhoneStorage getInstance() {
        if (instance == null) {
            synchronized (PhoneStorage.class) {
                if (instance == null) {
                    instance = new PhoneStorage();
                }
            }
        }
        return instance;
    }

//    private String getPhoneStorageInfo(Context context, long max_time) {
//        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
//        final List<VolumeInfo> volumes = mStorageManager.getVolumes();
//        for (VolumeInfo vol : volumes) {
//            if (vol.getType() == VolumeInfo.TYPE_PRIVATE) {
//                if (VolumeInfo.ID_PRIVATE_INTERNAL.equals(vol.getId())) {
//                    if (vol.isMountedReadable() && vol.getPath() != null) {//Defect 1533513 modify by shaohuali@tcl.com
//                        final File path = vol.getPath();
//                        final long freeBytes = path.getFreeSpace();
//                        long totalBytes = path.getTotalSpace();
//                        long usedBytes = totalBytes - freeBytes;
//                        if (TextUtils.equals(INTERSTORAGEPATH, vol
//                                .getPath().getPath())) {
//                            usedBytes += getSystemMemorySpace(vol);
//                            totalBytes += getSystemMemorySpace(vol);
//                        }
//                        final String used = CommonUtils.formatFileSize(usedBytes);
//                        final String total = CommonUtils.formatFileSize(totalBytes);
//                        return "used : " + used + " total : " + total;
//                    }
//                }
//            }
//        }
//        return "";
//    }

//    public static long getSystemMemorySpace(VolumeInfo mVolume) {
//        long systemMermorySpace = 0;
//        final File file = mVolume.getPath();
//        final long totalBytes = file.getTotalSpace();
//        final long freeBytes = file.getFreeSpace();
//        final long usedBytes = totalBytes - freeBytes;
//        long nominalTotalSpace = 0;// The total space declared to users.
//        if (TextUtils.equals(INTERSTORAGEPATH, mVolume
//                .getPath().getPath())) {
//            long totalGBytes = totalBytes / GB;
//            if (totalGBytes < SIZE_16) {
//                nominalTotalSpace = SIZE_16;
//            } else if (totalGBytes > SIZE_16
//                    && totalGBytes < SIZE_32) {
//                nominalTotalSpace = SIZE_32;
//            }
//            //Defect 2474240 add by shaohuali@tcl.com begin
//            else if (totalGBytes > SIZE_32 && totalGBytes <= SIZE_64) {
//                nominalTotalSpace = 64;
//            }
//            //Defect 2474240 add by shaohuali@tcl.com end
//            long memory = nominalTotalSpace - totalGBytes;
//            systemMermorySpace = memory * GB;
//        }
//        return systemMermorySpace;
//    }

    static final int SIZE_16 = 16;
    static final int SIZE_32 = 32;
    static final int SIZE_64 = 64;
    static final String INTERSTORAGEPATH = "/data";
    static long KB = 1024;
    static long MB = KB * 1024;
    static long GB = MB * 1024;
}
