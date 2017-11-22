package wang.tyrael.storage;

import wang.tyrael.util.FileUtil;

import java.io.File;

/**
 * 缓存清除器，注意重要数据不要清除
 * Created by 王超 on 2017/9/7.
 */

public class CacheCleaner {
    /**
     * 系统的外部缓存
     */
    public static void cleanExternalCache(){
        File dir = DirSurpport.getCacheDir();
        FileUtil.delFileByDir(dir);
    }
}
