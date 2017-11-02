package com.csmall.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.csmall.log.LogHelper;
import com.csmall.presenter.UIInterface;

/**
 * Created by 王超 on 2017/11/2.
 */

public abstract class LibBaseActivity extends AppCompatActivity implements UIInterface {
    private static final String TAG = "LibBaseActivity";
    protected String state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogHelper.i(TAG, "onCreate:" + this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogHelper.i(TAG, "onNewIntent:" + this);
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogHelper.i(TAG, "onSaveInstanceState:" + this);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        state = "resumed";
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = "paused";
    }

    @Override
    protected void onDestroy() {
        LogHelper.i(TAG, "onDestroy:" + this);
        super.onDestroy();
    }
}
