package com.csmall.android;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.csmall.android.application.ApplicationHolder;
import com.csmall.json.individual.Device;
import com.csmall.log.LogHelper;

import java.util.UUID;

/**
 * Created by wangchao on 2016/6/13.
 */
public class DeviceHelper {
    private static final Context context = ApplicationHolder.getApplication();
    private static final String TAG = "DeviceHelper";

    public static Device getDevice(){
        Device device = new Device();
          /*
   * 唯一的设备ID：
   * GSM手机的 IMEI 和 CDMA手机的 MEID.
   * Return null if device ID is not available.
   */
        device.imei = getImei();//String

        device.mac = getMac();
        return device;
    }

    public static String getMac(){
        return null;
    }

    /**
     * 计算一个设备id
     * 防止山寨机imei相同的情况
     * @return
     */
    public static String getDeviceId(){
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String tmDevice, tmSerial, androidId;
        tmDevice = "" + getImei();
        try {
            tmSerial = "" + tm.getSimSerialNumber();
        }catch (SecurityException se){
            LogHelper.w(TAG, se);
            tmSerial = "";

        }

        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    public static String getImei(){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            return tm.getDeviceId();
        }catch (NullPointerException npe){
            LogHelper.w(TAG, npe);
            return "";
        }catch (SecurityException se){
            LogHelper.w(TAG, se);
            return "";
        }
    }
}
