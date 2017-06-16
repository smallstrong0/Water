package com.smallstrong.water.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import static android.content.Context.CONTEXT_IGNORE_SECURITY;

public class AppEnvEnum {
    public final static int DEBUG = 1;
    public final static int ONLINE = 2;
    public static int appEnvEnum = ONLINE; //版本环境
    private final static String key = "AppEnvEnum";
    private final static String UTILNAME = "beargryllsUtil";

    /**
     * 环境切换获取
     */
    public static void getAppEnvEnum(Context context) {
        try {
            Context beargryllsUtil = context.createPackageContext("com.joojia.beargrylls.util", CONTEXT_IGNORE_SECURITY);
            if (beargryllsUtil == null)
                return;
            SharedPreferences sharedPreferences = beargryllsUtil.getSharedPreferences(UTILNAME, Context.MODE_WORLD_READABLE);
            int checkEnum = sharedPreferences.getInt(key, ONLINE);
            switch (checkEnum) {
                case 1:
                    appEnvEnum = DEBUG;//测试
                case 2:
                    appEnvEnum = DEBUG;//预发版本（预留）
                    break;
                case 3:
                    appEnvEnum = ONLINE;//线上
                    break;
            }

        } catch (PackageManager.NameNotFoundException e) {

        }

    }
}
