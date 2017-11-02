package com.csmall.library.statistics.individual;

import com.csmall.json.individual.User;

/**
 * 前台接待,外部接口类
 *
 * 1.外部接口，接收时间
 * 2. 写入日志
 * 3. 上报日志
 *
 * //TODO 如何启动上报
 * Created by wangchao on 2016/6/7.
 */
public class IndividualFacade {
    public void reportClick(String id){
        Event event = new Event();
        event.name =id;
        report(event);
    }

    public void report(Event event){
        //写入文件
        IndividualLog.getInstance().onNewEvent(event);
    }

    public void ifGetConfig(User user){
        ReportConfig.getInstance().ifGetServerConfig(user);
    }

    /**
     * 本地日志文件上传给服务器处理.
     * 这个任务和获取配置任务没有直接的关系。可以并行这行。
     */
    public void upload(){
        new IndividualReporter().report();
    }
}
