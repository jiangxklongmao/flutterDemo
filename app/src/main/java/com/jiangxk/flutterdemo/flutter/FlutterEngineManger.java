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

    private Map<String, FlutterEngine> mEngineMap = new HashMap<>();

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
        DartExecutor.DartEntrypoint dartEntrypoint = new DartExecutor.DartEntrypoint(FlutterInjector.instance().flutterLoader().findAppBundlePath(), "main");
        FlutterEngine flutterEngine = engineGroup.createAndRunEngine(DemoApplication.sContext, dartEntrypoint);
        return flutterEngine;
    }

    public FlutterEngine getFlutterEngine(String pageId) {
        return mEngineMap.get(pageId);
    }

    public void putEngine(String pageId, FlutterEngine engine) {
        mEngineMap.put(pageId, engine);
    }

    public void removeEngine(String pageId) {
        mEngineMap.remove(pageId);
    }

}
