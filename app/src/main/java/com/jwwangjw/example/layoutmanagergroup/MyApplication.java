package com.jwwangjw.example.layoutmanagergroup;

import android.app.Application;
import android.content.Context;


/**
 * Created by wjw
 * github: https://github.com/jwwangjw
 * email: 1508417398@qq.com
 */

public class MyApplication extends Application{
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
