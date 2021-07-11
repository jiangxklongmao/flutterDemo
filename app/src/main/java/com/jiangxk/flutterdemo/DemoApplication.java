package com.jiangxk.flutterdemo;

import android.app.Application;
import android.content.Context;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/219:24 PM
 * desc   :
 * version: 1.0
 * @author jiangxk
 */
public class DemoApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
