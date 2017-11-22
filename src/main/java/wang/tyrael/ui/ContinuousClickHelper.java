package wang.tyrael.ui;

import wang.tyrael.log.LogHelper;

/**
 * Created by wangchao on 2015/9/4.
 */
public class ContinuousClickHelper {
    private static final int CLICK_INTERVAL = 1000;
    private static final String TAG = "ContinuousClickHelper";
    /**
     * 默认5次
     */
    private int mClickThreshold = 5;
    private int mClickCount;
    private long mLastTime;
    private final ContinuousClickListener mListener;

    public ContinuousClickHelper(ContinuousClickListener mListener) {
        this.mListener = mListener;
    }

    public void click(){
        LogHelper.i(TAG, "click");
        long now = System.currentTimeMillis();
        if(now - mLastTime < CLICK_INTERVAL){
            //连击
            mClickCount ++;
            if(mClickCount >= mClickThreshold){
                //触发连击
                if(mListener != null){
                    mListener.onContinuousClick();
                }
                mClickCount = 0;
            }
        }else{
            //与上次间隔长
            mClickCount = 0;
        }
        mLastTime = now;
    }

    public interface ContinuousClickListener{
        void onContinuousClick();
    }
}
