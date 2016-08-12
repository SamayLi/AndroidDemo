package com.samay.commonlib.storage;


/**
 * Created by shaohua.li on 8/10/16.
 */
public class SDStorage {
    private static SDStorage instance = null;

    private static SDStorage getInstance() {
        if (instance == null) {
            synchronized (SDStorage.class) {
                if (instance == null) {
                    instance = new SDStorage();
                }
            }
        }
        return instance;
    }

    private final static int SD_NOT_INSERT = 1;
    private final static int SD_NOT_IDENTIFY = 2;
    private final static int SD_IS_INTERNAL = 3;
    private final static int SD_IS_EXTERNAL = 4;
    private final static int SD_INSERT = 5;

//    public int getSDStatus(Context mContext) {
//        int status = SD_NOT_INSERT;
//        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
//        final List<VolumeInfo> volumeInfos = mStorageManager.getVolumes();
//        final List<DiskInfo> diskInfos = mStorageManager.getDisks();
//        for (DiskInfo diskInfo : diskInfos) {
//            if (diskInfo.volumeCount == 0 && diskInfo.size > 0) {
//                if (diskInfo != null && diskInfo.isSd()) {
//                    status = SD_INSERT;
//                }
//            }
//        }
//        if (status != SD_INSERT) {
//            for (VolumeInfo volumeInfo : volumeInfos) {
//                if (volumeInfo.getType() == VolumeInfo.TYPE_PRIVATE) {
//                    if (volumeInfo.getDisk() != null && volumeInfo.getDisk().isSd()) {
//                        status = SD_IS_INTERNAL;
//                    }
//                } else if (volumeInfo.getType() == VolumeInfo.TYPE_PUBLIC) {
//                    if (volumeInfo.getDisk() != null && volumeInfo.getDisk().isSd()) {
//                        status = SD_IS_EXTERNAL;
//                    }
//                }
//            }
//        }
//
//        if (status == SD_INSERT) {
//            status = SD_NOT_IDENTIFY;
//        }
//        return status;
//    }
//
//    private static String getExternalSDCardInfo(Context context) {
//        StorageManager storagemanager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
//        List<VolumeInfo> vols = storagemanager.getVolumes();
//        for (VolumeInfo vol : vols) {
//            if (vol.getType() == VolumeInfo.TYPE_PUBLIC) {
//                if (vol.getDisk() != null && vol.getDisk().isSd()) {
//                    final File path = vol.getPath();
//                    if (path != null) {
//                        final long freeBytes = path.getFreeSpace();
//                        final long totalBytes = path.getTotalSpace();
//                        long usedBytes = totalBytes - freeBytes;
//                        final String used = CommonUtils.formatFileSize(usedBytes);
//                        final String total = CommonUtils.formatFileSize(totalBytes);
//                        return "used : " + used + " total : " + total;
//                    } else {
//                        return "";
//                    }
//                }
//            }
//        }
//        return "";
//    }
//
//    private static String getInternalSDCardInfo(Context mContext, long max_time) {
//        StorageManager storageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
//        List<VolumeInfo> vols = storageManager.getVolumes();
//        for (VolumeInfo vol : vols) {
//            if (vol.getType() == VolumeInfo.TYPE_PRIVATE) {
//                if (vol.getDisk() != null && vol.getDisk().isSd()) {
//                    final File path = vol.getPath();
//                    if (path != null) {
//                        final long freeBytes = path.getFreeSpace();
//                        final long totalBytes = path.getTotalSpace();
//                        long usedBytes = totalBytes - freeBytes;
//                        final String used = CommonUtils.formatFileSize(usedBytes);
//                        final String total = CommonUtils.formatFileSize(totalBytes);
//                        return "used : " + used + " total : " + total;
//                    } else {
//                        return "";
//                    }
//                }
//            }
//        }
//        return "";
//    }
}
