package com.iss.phonealarm;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iss.phonealarm.uploadalarm.RecordPlayer;

import java.io.File;
import java.io.IOException;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

}
