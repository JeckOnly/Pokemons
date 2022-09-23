package com.jeckonly.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * 打印工具类
 * 可自动把调用位置所在的类名和方法名作为tag，并可设置打印级别
 * dway
 */
public class LogUtil {

    //以下为打印级别，级别从低到高
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_WARN = 4;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_NOLOG = 6;

    private static String AppName = "";
    private static boolean PrintLine = false;
    private static int LogLevel = LOG_LEVEL_VERBOSE;

    /**
     * 可在打印的TAG前添加应用名标识，不设置则不输出
     */
    public static void setAppName(String appName){
        AppName = appName;
    }

    /**
     * 是否输出打印所在的行数，默认不输出
     */
    public static void setPrintLine(boolean enable){
        PrintLine = enable;
    }

    /**
     * 设置打印级别，且只有等于或高于该级别的打印才会输出
     */
    public static void setLogLevel(int logLevel){
        LogLevel = logLevel;
    }

    public static void v(){
        log(LOG_LEVEL_VERBOSE, "");
    }

    public static void d(){
        log(LOG_LEVEL_DEBUG, "");
    }

    public static void i(){
        log(LOG_LEVEL_INFO, "");
    }

    public static void w(){
        log(LOG_LEVEL_WARN, "");
    }

    public static void e(){
        log(LOG_LEVEL_ERROR, "");
    }

    public static void v(String msg){
        if(LogLevel <= LOG_LEVEL_VERBOSE) {
            log(LOG_LEVEL_VERBOSE, msg);
        }
    }

    public static void d(String msg){
        if(LogLevel <= LOG_LEVEL_DEBUG) {
            log(LOG_LEVEL_DEBUG, msg);
        }
    }

    public static void i(String msg){
        if(LogLevel <= LOG_LEVEL_INFO) {
            log(LOG_LEVEL_INFO, msg);
        }
    }

    public static void w(String msg){
        if(LogLevel <= LOG_LEVEL_WARN) {
            log(LOG_LEVEL_WARN, msg);
        }
    }

    public static void e(String msg){
        if(LogLevel <= LOG_LEVEL_ERROR) {
            log(LOG_LEVEL_ERROR, msg);
        }
    }

    private static void log(int logLevel, String msg) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = caller.getClassName();
        if(callerClazzName.contains(".")) {
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        }
        if(callerClazzName.contains("$")){
            callerClazzName = callerClazzName.substring(0, callerClazzName.indexOf("$"));
        }
        String tag = callerClazzName;
        if(!TextUtils.isEmpty(AppName)){
            tag = AppName + "_" + tag;
        }
        if(PrintLine) {
            tag += "(Line:%d)";
            tag = String.format(tag, caller.getLineNumber());
        }
        tag = String.format(tag, callerClazzName);
        String message = "---"+caller.getMethodName()+"---"+msg;
        switch (logLevel){
            case LOG_LEVEL_VERBOSE:
                Log.v(tag, message);
                break;
            case LOG_LEVEL_DEBUG:
                Log.d(tag, message);
                break;
            case LOG_LEVEL_INFO:
                Log.i(tag, message);
                break;
            case LOG_LEVEL_WARN:
                Log.w(tag, message);
                break;
            case LOG_LEVEL_ERROR:
                Log.e(tag, message);
                break;
            case LOG_LEVEL_NOLOG:
                break;
        }
    }

}