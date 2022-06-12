package com.jiangxk.flutterdemo.flutter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


import com.jiangxk.flutterdemo.R;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterSurfaceView;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.TransparencyMode;
import io.flutter.embedding.engine.FlutterEngine;
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
public class FlutterPageActivity extends Activity {
    private static final String TAG = "FlutterPageActivity";

    FrameLayout root;
    FlutterEngine flutterEngine;
    FlutterView flutterView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        flutterEngine = FlutterEngineManger.getInstance().getFlutterEngine();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);

        root = findViewById(R.id.root);

        FlutterSurfaceView flutterSurfaceView = new FlutterSurfaceView(this, true);
        flutterView = new FlutterView(this, flutterSurfaceView);
        flutterView.attachToFlutterEngine(flutterEngine);
        root.addView(flutterView, new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flutterEngine.getLifecycleChannel().appIsResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flutterEngine.getLifecycleChannel().appIsInactive();
    }

    @Override
    protected void onStop() {
        super.onStop();
        flutterEngine.getLifecycleChannel().appIsPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (flutterEngine != null) {
            flutterView.detachFromFlutterEngine();
            flutterEngine.destroy();
        }
    }
}
