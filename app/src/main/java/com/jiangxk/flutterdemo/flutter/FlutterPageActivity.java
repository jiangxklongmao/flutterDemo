package com.jiangxk.flutterdemo.flutter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/2111:56 AM
 * desc   :
 * version: 1.0
 *
 * @author jiangxk
 */
public class FlutterPageActivity extends FlutterActivity {
    private static final String TAG = "FlutterPageActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String pageId = getIntent().getStringExtra("pageId");
        String url = getIntent().getStringExtra("url");

        FlutterChannel.getInstance().registerChannel(pageId, getFlutterEngine());

        Map<String, String> stringMap = new HashMap<>(1);
        stringMap.put("pageId", pageId);
        stringMap.put("url", url);
        FlutterChannel.getInstance().getChannelByPageId(pageId).invokeMethod("init", stringMap, new MethodChannel.Result() {
            @Override
            public void success(@Nullable Object result) {
                Log.i(TAG, "success: " + result.toString());
            }

            @Override
            public void error(String errorCode, @Nullable String errorMessage, @Nullable Object errorDetails) {
                Log.i(TAG, "error: " + errorMessage);
            }

            @Override
            public void notImplemented() {
                Log.i(TAG, "notImplemented: ");
            }
        });
    }
}
