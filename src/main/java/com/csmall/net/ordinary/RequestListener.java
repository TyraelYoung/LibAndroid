package com.csmall.net.ordinary;

/**
 * Created by wangchao on 2017/3/7.
 */

public interface RequestListener {
    void onError(int code, String msg);
    void onSuccess(ResponseData responseData);
}
