package com.example.wutai.wutaimoutain.Utils;

import android.util.Log;

/**
 * Created by zhangwei on 2018/8/5.
 */

public class MyLogUtils {
    static final boolean debug = true;
    static  String TAG = "MyDebug";
    public static void e(String msg){
        if(debug){
            Log.d(TAG,msg);
        }
    }
}
