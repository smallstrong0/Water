package com.smallstrong.water.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.smallstrong.ss_common.SSLog;

/**
 * Created by smallstrong on 2017/6/16.
 */

public class AppContext extends MultiDexApplication {
    private static Context appContext = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        appContext = this;
        AppEnvEnum.getAppEnvEnum(getApplicationContext());//切换环境
        SSLog.setPrintLog(true);//log初始化
    }

    public static Context getAppContext() {
        return appContext;
    }

}
