package com.jiangxk.flutterdemo.flutter;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.flutter.embedding.android.FlutterActivity;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = "";
        FlutterEngineManger.getInstance().putEngine(url, getFlutterEngine());

    }
}
