package com.example.wutai.wutaimoutain.Utils;

import android.util.Log;

public class LogUtils {
	
	private final static boolean IS_DEBUG = true;
	
	public static void e(String tag, String msg) {
		if(IS_DEBUG) {
			Log.e(tag, checkMsg(msg));
		}
	}
	
	public static void i(String tag, String msg) {
		if(IS_DEBUG) {
			Log.i(tag, checkMsg(msg));
		}
	}
	
	public static void d(String tag, String msg) {
		if(IS_DEBUG) {
			Log.d(tag, checkMsg(msg));
		}
	}
	
	public static void e(String tag, String msg, Throwable t) {
		if(IS_DEBUG) {
			Log.e(tag, checkMsg(msg), checkThrowable(t));
		}
	}
	
	public static void  d(String tag, String msg, Throwable t) {
		if(IS_DEBUG) {
			Log.d(tag, checkMsg(msg), checkThrowable(t));
		}
	}
	
	public static void i(String tag, String msg, Throwable t) {
		if(IS_DEBUG) {
			Log.d(tag, checkMsg(msg), checkThrowable(t));
		}
	}
	
	public static String checkMsg(String msg) {
		return StringUtils.isNullOrEmpty(msg) ? "" : msg;
	} 
	
	public static Throwable checkThrowable (Throwable t) {
		return null == t ? new Throwable("") : t;
	}
}
