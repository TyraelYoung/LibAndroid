package wang.tyrael.storage;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StatFs;

import androidx.annotation.NonNull;

public class StorageUtils {
    @SuppressLint("NewApi")
    public static long getAvailableMemSize(@NonNull String path) {
        StatFs stat = null;
        try {
            stat = new StatFs(path);
        } catch (Exception e) {
            return 0;
        }

        if (stat == null)
            return 0;

        long availableBytes = 0;
        if (Build.VERSION.SDK_INT < 18) {
            availableBytes = ((long) stat.getAvailableBlocks()) * stat.getBlockSize();
        } else {
            availableBytes = stat.getAvailableBytes();
        }
        return availableBytes;
    }
}
