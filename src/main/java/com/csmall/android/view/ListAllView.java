package com.csmall.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.csmall.log.LogHelper;

/**
 * Created by wangchao on 2015/9/8.
 */
public class ListAllView extends LinearLayout{
    private static final String TAG = "DisplayAllListView";

    private BaseAdapter adapter;
    private MyOnItemClickListener onItemClickListener;

    /**
     * 通知更新listview
     */
    public void notifyChange() {
        this.removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            final int index = i;
            View v = adapter.getView(i, null, this);
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogHelper.d(TAG, "onClick:" + index);
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ListAllView.this,
                                v, index, adapter.getItem(index));
                    }
                }
            });
            addView(v, index);
            LogHelper.d(TAG, "addView:" + v + "  " + index);
        }
    }

    public ListAllView(Context context) {
        super(context);
    }

    public ListAllView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public BaseAdapter getAdapter() {
        return adapter;
    }

    /**
     * 设置adapter并模拟listview添加数据
     *
     * @param adpater
     */
    public void setAdapter(BaseAdapter adpater) {
        this.adapter = adpater;
        notifyChange();
    }

    /**
     * 设置条目监听事件
     *
     * @param onClickListener
     */
    public void setOnItemClickListener(MyOnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    /**
     * 点击事件监听
     *
     * @author JustMe
     */
    public interface MyOnItemClickListener {
        void onItemClick(ViewGroup parent, View view, int position,
                         Object o);
    }
}


