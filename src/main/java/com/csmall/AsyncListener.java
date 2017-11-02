package com.csmall;

/**
 * Created by wangchao on 2015/9/30.
 */
public interface AsyncListener {
    void onSucceed();
    void onFail(int errorCode, String msg);


}
