package com.csmall.android;

import android.os.Build;

/**
 * TODO
 * 系统提供的api太简单，不够用。
 * 查询某个权限的状态
 * https://developer.android.google.cn/guide/topics/security/permissions.html#normal-dangerous
 * Created by wangchao on 2017/3/30.
 */

public class PermissionManager {
    public static final int PERMISSION_UNKNOWN = 0;
    public static final int PERMISSION_GRANTED = 1;
    public static final int PERMISSION_DENIED = -1;

    public static int getInitPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return PERMISSION_UNKNOWN;
        }else{
            //manifest中已声明，直接返回同意
            return PERMISSION_GRANTED;
        }
    }

//    private Map<String, Integer> map = new HashMap<>();
//
//    /**
//     *
//     * @param permission {@link android.Manifest.permission_group}
//     * @param status
//     */
//    public void put(String permission, int status){
//        map.put(permission, status);
//    }
//
//    /**
//     * 注意只能处理manifest中声明过的权限。
//     * @param permission
//     */
//    public int get(String permission){
//        if(Build.VERSION.SDK_INT >= 23){
//
//        }else{
//            //manifest中已声明，直接返回同意
//            return PERMISSION_GRANTED;
//        }
//    }


}
