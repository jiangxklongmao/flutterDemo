package com.jiangxk.flutterdemo.flutter;

import android.util.Log;

import com.jiangxk.flutterdemo.DemoApplication;

import java.util.HashMap;
import java.util.Map;

import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineGroup;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/219:47 PM
 * desc   :
 * version: 1.0
 */
public class FlutterEngineManger {
    private static final String TAG = "FlutterEngineManger";

    private FlutterEngineGroup engineGroup;

    private FlutterEngineManger() {
        engineGroup = new FlutterEngineGroup(DemoApplication.sContext);
    }

    public static FlutterEngineManger getInstance() {
        return FlutterEngineMangerHolder.instance;
    }

    private static class FlutterEngineMangerHolder {
        private static FlutterEngineManger instance = new FlutterEngineManger();
    }

    public FlutterEngine getFlutterEngine() {
//        //Use default mode     success
//        DartExecutor.DartEntrypoint dartEntrypoint = new DartExecutor.DartEntrypoint(
//                FlutterInjector.instance().flutterLoader().findAppBundlePath(),
//                "main");

        //Use Custom  dartEntrypointLibrary  path     failure
        DartExecutor.DartEntrypoint dartEntrypoint = new DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(),
                "package:flutter_module/others.dart", "others");

        FlutterEngine flutterEngine = engineGroup.createAndRunEngine(DemoApplication.sContext, dartEntrypoint);
        return flutterEngine;
    }

}
