package com.smallstrong.ss_common;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * log 全局管理
 * Created by smallstrong on 2017/6/17.
 */

public class SSLog {
    private static final String TAG = "SSLog";
    private static boolean printLog = true;// 二层开关

    public static void setPrintLog(final boolean printLog) {
        Log.i(TAG, "set SSLog Switch=" + printLog);
        SSLog.printLog = printLog;

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(TAG)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return printLog;//一层开关
            }
        });
    }

    public static boolean isPrintLog() {
        return printLog;
    }


    public static void d(Object object) {
        if (printLog) {
            Logger.d(object);
        }
    }

    public static void e(String message) {
        if (printLog) {
            Logger.e(message);
        }
    }


    public static void i(String message) {
        if (printLog) {
            Logger.i(message);
        }
    }

    public static void v(String message) {
        if (printLog) {
            Logger.v(message);
        }
    }

    public static void w(String message) {
        if (printLog) {
            Logger.w(message);
        }
    }

    public static void json(String json) {
        if (printLog) {
            Logger.json(json);
        }
    }

    public static void xml(String xml) {
        if (printLog) {
            Logger.xml(xml);
        }
    }

    public static void d(String tag, Object object) {
        if (printLog) {
            Logger.t(tag).d(object);
        }
    }

    public static void e(String tag, String message) {
        if (printLog) {
            Logger.t(tag).e(message);
        }
    }


    public static void i(String tag, String message) {
        if (printLog) {
            Logger.t(tag).i(message);
        }
    }

    public static void v(String tag, String message) {
        if (printLog) {
            Logger.t(tag).v(message);
        }
    }

    public static void w(String tag, String message) {
        if (printLog) {
            Logger.t(tag).w(message);
        }
    }

    public static void json(String tag, String json) {
        if (printLog) {
            Logger.t(tag).json(json);
        }
    }

    public static void xml(String tag, String xml) {
        if (printLog) {
            Logger.t(tag).xml(xml);
        }
    }


}
