package com.jiangxk.flutterdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jiangxk.flutterdemo.flutter.FlutterPageActivity;


/**
 * @author jiangxk
 */
public class MainActivity extends AppCompatActivity {
    private int pageId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView).setOnClickListener(v -> {
            openFlutterPage();
        });
    }

    private void openFlutterPage() {
        Intent intent = new Intent(MainActivity.this, FlutterPageActivity.class);
        intent.putExtra("pageId", pageId + "");
        pageId++;
        intent.putExtra("url", "https://img2.baidu.com/it/u=463599133,3602275510&fm=26&fmt=auto&gp=0.jpg");
        startActivity(intent);
    }
}