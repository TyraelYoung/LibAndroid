package wang.tyrael.cipher;

import android.util.Base64;

import wang.tyrael.cipher.IBase64;

/**
 *  * 安卓8.0后支持java8 Base64类
 * 不存在谁好谁坏吧.这里就用安卓api 8开始存在的类.
 * Created by 王超 on 2017/11/21.
 */

public class AndroidBase64 implements IBase64 {
    @Override
    public String encodeToString(byte[] input) {
        return Base64.encodeToString(input, Base64.DEFAULT);
    }

    @Override
    public byte[] decode(String str) {
        return Base64.decode(str, Base64.DEFAULT);
    }
}
