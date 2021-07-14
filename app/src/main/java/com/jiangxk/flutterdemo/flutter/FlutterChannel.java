package com.jiangxk.flutterdemo.flutter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jiangxk.flutterdemo.DemoApplication;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.TextureRegistry;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/12/2110:27 PM
 * desc   :
 * version: 1.0
 */
class FlutterChannel {
    private static final String TAG = "FlutterChannel";
    private Map<String, MethodChannel> mChannelMap = new HashMap<>();

    private FlutterChannel() {
    }

    public static FlutterChannel getInstance() {
        return FlutterChannelHolder.instance;
    }

    static class FlutterChannelHolder {
        private static FlutterChannel instance = new FlutterChannel();
    }


    public void registerChannel(String pageId, FlutterEngine engine) {
        MethodChannel methodChannel = new MethodChannel(engine.getDartExecutor(), "demo_channel");
        methodChannel.setMethodCallHandler(new FlutterChannelHandler());


        engine.addEngineLifecycleListener(new FlutterEngine.EngineLifecycleListener() {
            @Override
            public void onPreEngineRestart() {
                Log.i(TAG, "onPreEngineRestart: ");
            }

            @Override
            public void onEngineWillDestroy() {
                Log.i(TAG, "onEngineWillDestroy: ");
                mChannelMap.remove(pageId);
                FlutterEngineManger.getInstance().removeEngine(pageId);
            }
        });

        mChannelMap.put(pageId, methodChannel);
    }

    public MethodChannel getChannelByPageId(String pageId) {
        return mChannelMap.get(pageId);
    }


    static class FlutterChannelHandler implements MethodChannel.MethodCallHandler {
        private static final String TAG = "FlutterChannel";

        @Override
        public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
            String methodName = call.method;
            if (methodName == null) {
                Log.e(TAG, "onMethodCall: " + "methodName == null");
                result.error("1001", "methodName == null", "");
                return;
            }

            switch (methodName) {
                case "getTexture":
                    String url = call.argument("url");
                    String pageId = call.argument("pageId");
                    if (TextUtils.isEmpty(url)) {
                        Log.e(TAG, "onMethodCall: " + "url isEmpty");
                        result.error("1002", "url isEmpty", "");
                        return;
                    }

                    FlutterEngine engine = FlutterEngineManger.getInstance().getFlutterEngine(pageId);
                    if (engine == null) {
                        result.error("1003", "FlutterEngine == null", "");
                        return;
                    }

                    TextureRegistry.SurfaceTextureEntry entry = engine.getRenderer().createSurfaceTexture();

                    result.success(entry.id());

//                    int size = (int) (DemoApplication.sContext.getResources().getDisplayMetrics().density * 100);
                    int size = 100;

                    Glide.with(DemoApplication.sContext)
                            .asBitmap()
                            .load(url)
                            .into(new Target<Bitmap>() {
                                @Override
                                public void onStart() {
                                    Log.i(TAG, "onStart: ");
                                }

                                @Override
                                public void onStop() {
                                    Log.i(TAG, "onStop: ");
                                }

                                @Override
                                public void onDestroy() {
                                    Log.i(TAG, "onDestroy: ");
                                }

                                @Override
                                public void onLoadStarted(@Nullable Drawable placeholder) {
                                    Log.i(TAG, "onLoadStarted: ");
                                }

                                @Override
                                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                    Log.i(TAG, "onLoadFailed: ");
                                }

                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    Log.i(TAG, "onResourceReady: ");

                                    SurfaceRender surfaceRender = new SurfaceRender(entry, resource, size);
                                    surfaceRender.drawTexture();

                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                    Log.i(TAG, "onLoadCleared: ");
                                }

                                @Override
                                public void getSize(@NonNull SizeReadyCallback cb) {
                                    cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//                                    cb.onSizeReady(size, size);
                                    Log.i(TAG, "getSize: ");
                                }

                                @Override
                                public void removeCallback(@NonNull SizeReadyCallback cb) {

                                }

                                @Override
                                public void setRequest(@Nullable Request request) {

                                }

                                @Nullable
                                @Override
                                public Request getRequest() {
                                    return null;
                                }
                            });
                    break;
                default:
                    break;
            }
        }

    }
}
