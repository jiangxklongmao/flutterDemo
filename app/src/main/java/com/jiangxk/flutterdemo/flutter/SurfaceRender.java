package com.jiangxk.flutterdemo.flutter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;

import androidx.annotation.NonNull;

import io.flutter.view.TextureRegistry;

/**
 * author : jiangxk
 * e-mail : jxklongmao@gmai.com
 * date   : 7/11/219:32 PM
 * desc   :
 * version: 1.0
 */
class SurfaceRender {
    private static final String TAG = "SurfaceRender";
    public static final int WHAT_DRAW = 1;
    private TextureRegistry.SurfaceTextureEntry mEntry;
    private SurfaceTexture mTexture;
    private Surface mSurface;
    private Bitmap mBitmap;
    private Paint mPaint;

    private HandlerThread mHandlerThread;
    private Handler mHandler;


    public SurfaceRender(TextureRegistry.SurfaceTextureEntry entry, Bitmap bitmap) {
        mEntry = entry;
        mTexture = entry.surfaceTexture();
        mSurface = new Surface(mTexture);
        mBitmap = bitmap;
        mPaint = new Paint();

        mHandlerThread = new HandlerThread("SurfaceRender");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case WHAT_DRAW:
                        innerDrawTexture();
                        break;
                    default:
                        break;
                }
            }
        };

    }

    public void drawTexture() {
        mHandler.sendEmptyMessage(WHAT_DRAW);

    }

    private void innerDrawTexture() {
        Canvas canvas = null;
        try {
            canvas = mSurface.lockCanvas(null);
            if (mBitmap != null) {
                canvas.drawBitmap(mBitmap, (mBitmap.getWidth() - 100) / 2, (mBitmap.getHeight() - 100) / 2, mPaint);
            }
        } catch (Exception e) {
            Log.e(TAG, "innerDrawTexture: ", e);
        } finally {
            if (canvas != null) {
                mSurface.unlockCanvasAndPost(canvas);
            }
        }
    }

}
