package wang.tyrael.statistics.individual;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wangchao on 2016/6/7.
 */
public class LogWriter {
    private static final String TAG = "LogWriter";
    private final String mDir;
    private final String mPath;

    public LogWriter(String mDir) {
        this.mDir = mDir;
        mPath = Dir.fileLog(mDir);
    }

    public void write(Logable event){
        try {
            ensureDir();
            FileWriter fileWriter = new FileWriter(mPath, true);
            fileWriter.write(event.toString());
            fileWriter.write("\r\n");
           fileWriter.close();
        } catch (IOException e) {
            Log.i(TAG, "", e);
        }
    }

    //防止用户中途删除文件夹
    private void ensureDir(){
        new File(mDir).mkdirs();
    }
}
