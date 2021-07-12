package com.jiangxk.flutterdemo.flutter;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.engine.FlutterEngine;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/219:47 PM
 * desc   :
 * version: 1.0
 */
class FlutterEngineManger {

    private Map<String, FlutterEngine> mEngineMap = new HashMap<>();

    private FlutterEngineManger() {
    }

    public static FlutterEngineManger getInstance() {
        return FlutterEngineMangerHolder.instance;
    }

    private static class FlutterEngineMangerHolder {
        private static FlutterEngineManger instance = new FlutterEngineManger();
    }

    public FlutterEngine getFlutterEngine(String url) {
        return mEngineMap.get(url);
    }

    public void putEngine(String url, FlutterEngine engine) {

        mEngineMap.put(url, engine);
    }
}
