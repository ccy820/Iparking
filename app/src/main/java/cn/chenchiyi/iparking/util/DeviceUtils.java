package cn.chenchiyi.iparking.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by ccy820 on 2017/5/10.
 */

public class DeviceUtils {
    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }
}
