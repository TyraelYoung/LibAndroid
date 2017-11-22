package wang.tyrael.ui;

import android.view.View;

import wang.tyrael.android.ToastUtil;

/**
 * Created by wangchao on 2015/9/4.
 */
public class NoDoubleClick {
    private static final int CLICK_INTERVAL = 1000;
    private long mInterval = CLICK_INTERVAL;
    private long mLastTime;
    private View mView;
    private View.OnClickListener mListener;

    public NoDoubleClick(final View.OnClickListener mListener, View mView) {
        this.mListener = mListener;
        this.mView = mView;

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now = System.currentTimeMillis();
                if(now - mLastTime < mInterval){
                    //时间未到
                    ToastUtil.show("请勿连续点击");
                    return;
                }
                //时间到了
                mLastTime = now;
                mListener.onClick(view);
            }
        });
    }

    public void setInterval(long interval){
        mInterval = interval;
    }
}
