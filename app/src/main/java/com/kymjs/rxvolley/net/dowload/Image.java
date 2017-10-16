package com.kymjs.rxvolley.net.dowload;

import android.graphics.Bitmap;
import android.view.View;

public class Image {
    private Bitmap mBitmap;

    public View view;

    public Image(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
