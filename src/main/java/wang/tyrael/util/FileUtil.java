package wang.tyrael.util;

import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangchao on 2015/9/3.
 */
public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 解析出文件名
     * @param path 文件路径
     * @return
     */
    public static String parseName(String path){
        int index = path.lastIndexOf("/");
        if(index > 0){
            return path.substring(index + 1);
        }else{
            return path;
        }
    }

    /**

     * 生产文件 如果文件所在路径不存在则生成路径

     *

     * @param fileName

     *            文件名 带路径

     * @param isDirectory 是否为路径

     * @return

     * @author yayagepei

     * @date 2008-8-27

     */

    public static File buildFile(String fileName, boolean isDirectory) {

        File target = new File(fileName);

        if (isDirectory) {

            target.mkdirs();

        } else {

            if (!target.getParentFile().exists()) {

                target.getParentFile().mkdirs();

                target = new File(target.getAbsolutePath());

            }

        }

        return target;

    }

    //删除文件
    public static void delFile(String path) {
        if(path == null){
            return;
        }
        File file = new File(path);
        if (file.isFile()) {
            file.delete();
        }
    }

    public static void delDir(String dir){
        if (dir == null) {
            Log.w(TAG, "dir == null");
            return;
        }
        deleteFilesByDirectory(new File(dir));
        new File(dir).delete();
    }

    public static void delFileByDir(File dir) {
        if (dir == null) {
            Log.w(TAG, "dir == null");
            return;
        }
        deleteFilesByDirectory(dir);
    }

    public static void delFileByDir(String dir) {
        if (dir == null) {
            Log.w(TAG, "dir == null");
            return;
        }
        deleteFilesByDirectory(new File(dir));
    }


    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    public static void deleteFilesByDirectory(File directory) {
        if (directory == null) {
            return;
        }
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files == null) {
                //http://bugly.qq.com/detail?app=900008912&pid=1&ii=860#stack
                return;
            }
            for (File item : files) {
                //Deletes this file. Directories must be empty before they will be deleted.
                item.delete();
            }
        }
    }

    /**
     * TODO 找到当前文件夹下，按时间排序的最后一个文件
     *
     * @param dir
     * @return 文件绝对地址
     */
    public static String lastFile(String dir) {
        List<File> files = getFiles(dir);
        Iterator<File> iterator = files.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        File lastFile = iterator.next();
        File tempFile = null;
        while (iterator.hasNext()) {
            tempFile = iterator.next();
            if (lastFile.lastModified() < tempFile.lastModified()) {
                lastFile = tempFile;
            }
        }
        return lastFile.getAbsolutePath();
    }

    /**
     * 获取目录下所有文件。不递归
     *
     * @param realpath
     * @return
     */

    public static List<File> getFiles(String realpath) {
        List<File> files = new ArrayList<File>();
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {

            File[] subfiles = realFile.listFiles();

            for (File file : subfiles) {

                if (file.isDirectory()) {
                } else {

                    files.add(file);

                }

            }

        }

        return files;

    }

    //******************************文件的大小相关

    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值

    /**
     * 获取文件指定文件的指定单位的大小
     * @param filePath 文件路径
     * @return
     */
    public static long getFileOrDirSize(String filePath) {
        return getFileOrDirSize(new File(filePath));
    }

    /**
     * 获取文件指定文件的指定单位的大小
     * @param file 文件路径
     * @return
     */
    public static long getFileOrDirSize(File file) {
        if (file.isDirectory()) {
            long size = 0;
            File flist[] = file.listFiles();
            for (int i = 0; i < flist.length; i++) {
                if (flist[i].isDirectory()) {
                    size = size + getDirSize(flist[i]);
                } else {
                    size = size + file.length();
                }
            }
            return size;
        } else {
            //递归出口
            return file.length();
        }
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getDirSize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getDirSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }
}
