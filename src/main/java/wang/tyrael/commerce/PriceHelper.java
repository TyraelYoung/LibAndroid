package wang.tyrael.commerce;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Created by wangchao on 2015/11/18.
 */
public class PriceHelper {
    public static final DecimalFormat DF_NORMAL = new DecimalFormat("0.00");
    private static final String TAG = "PriceHelper";

    public static int equals(String price1, String price2){
        try {
            double d1 = Double.parseDouble(price1);
            double d2 = Double.parseDouble(price2);
            if(d1 -d2 < 0){
                return -1;
            }else if(d1 -d2 == 0){
                return 0;
            }else{
                return 1;
            }
        }catch (NumberFormatException nfe){
            return 0;
        }
    }

    /**
     * 检查商品价格是否有效
     * @param price
     * @return
     */
    public static boolean checkPrice(String price){
        if(TextUtils.isEmpty(price)){
            return false;
        }
        try{
            double p = Double.parseDouble(price);
            if(p - 0 < 0.01){
                return false;
            }else{
                //价格要大于等于0.01
                return true;
            }
        }catch (NumberFormatException nfe){
//            LogHelper.w(TAG, nfe);
            return false;
        }
    }
}
