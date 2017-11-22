package wang.tyrael.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import wang.tyrael.eventbus.event.Status401Event;
import wang.tyrael.eventbus.event.Status403Event;

/**
 * 需要响应登录失效的acitivity继承该类
 * Created by 王超 on 2017/11/13.
 */

public abstract class Base40xActivity extends LibBaseActivity {
    private Object eventListener = new Object(){
        @Subscribe
        public void on401(Status401Event status401Event){
            finish();
        }

        @Subscribe
        public void on403(Status403Event status403Event){
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(eventListener);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(eventListener);
        super.onDestroy();
    }
}
