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

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.TextureRegistry;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/219:09 PM
 * desc   :
 * version: 1.0
 */
class FlutterChannel implements MethodChannel.MethodCallHandler {
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
                if (TextUtils.isEmpty(url)) {
                    Log.e(TAG, "onMethodCall: " + "url isEmpty");
                    result.error("1002", "url isEmpty", "");
                    return;
                }

                FlutterEngine engine = FlutterEngineManger.getInstance().getFlutterEngine(url);
                if (engine == null) {
                    result.error("1003", "FlutterEngine == null", "");
                    return;
                }

                TextureRegistry.SurfaceTextureEntry entry = engine.getRenderer().createSurfaceTexture();

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

                                SurfaceRender surfaceRender = new SurfaceRender(entry, resource);
                                surfaceRender.drawTexture();

                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                Log.i(TAG, "onLoadCleared: ");
                            }

                            @Override
                            public void getSize(@NonNull SizeReadyCallback cb) {
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
