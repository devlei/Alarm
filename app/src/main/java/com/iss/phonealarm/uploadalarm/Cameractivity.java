package com.iss.phonealarm.uploadalarm;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.uploadalarm.view.CircleProgressBar;

import java.io.File;
import java.util.UUID;


public class Cameractivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener {

    public static final String path = Environment.getExternalStorageDirectory().getPath()
            + File.separator + UUID.randomUUID() + ".mp4";
    private SurfaceView mSurfaceview;
    private TextView notice;

    private boolean mStartedFlg = false;//是否正在录像
    private boolean mIsPlay = false;//是否正在播放录像
    private MediaPlayer mediaPlayer;
    private MediaRecorder mRecorder;
    private Camera camera;
    private SurfaceHolder mSurfaceHolder;
    private CircleProgressBar circleProgressBar;
    private ImageView video_exit, video_back, video_over;


    private android.os.Handler handler = new android.os.Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);

        mSurfaceview = (SurfaceView) findViewById(R.id.surfaceview);
        notice = (TextView) findViewById(R.id.notice);
        video_exit = (ImageView) findViewById(R.id.video_exit);
        video_back = (ImageView) findViewById(R.id.video_back);
        video_over = (ImageView) findViewById(R.id.video_over);
        video_exit.setOnClickListener(this);
        video_back.setOnClickListener(this);
        video_over.setOnClickListener(this);
//        video_over.setVisibility(View.INVISIBLE);
        video_back.setVisibility(View.INVISIBLE);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
        circleProgressBar.setLoadingCallBack(new CircleProgressBar.LoadingCallBack() {
            @Override
            public void loadingComplete(View v) {
                stopRecord();
                playRecord();
                circleProgressBar.setProgress(0);
                circleProgressBar.setVisibility(View.INVISIBLE);
                video_over.setVisibility(View.VISIBLE);
                video_exit.setVisibility(View.INVISIBLE);
                video_back.setVisibility(View.VISIBLE);

            }

            @Override
            public void loadingStart() {
                startRecord();
                notice.setVisibility(View.INVISIBLE);
            }
        });
        SurfaceHolder holder = mSurfaceview.getHolder();
        holder.addCallback(this);
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void startRecord() {
        if (mediaPlayer != null) {
            mIsPlay = false;
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (!mStartedFlg) {
            handler.postDelayed(runnable, 1000);
            if (mRecorder == null) {
                mRecorder = new MediaRecorder();
            }
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            if (camera != null) {
                camera.setDisplayOrientation(90);
                camera.unlock();
                mRecorder.setCamera(camera);
            }
            try {
                // 这两项需要放在setOutputFormat之前
                mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                // Set output file format
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                // 这两项需要放在setOutputFormat之后
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                mRecorder.setVideoSize(640, 480);
                mRecorder.setVideoFrameRate(30);
                mRecorder.setVideoEncodingBitRate(524288);//(int) (0.5 * 1024 * 1024)
                mRecorder.setOrientationHint(90);
                //设置记录会话的最大持续时间（毫秒）
                mRecorder.setMaxDuration(30 * 1000);
                mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
                mRecorder.setOutputFile(path);
                mRecorder.prepare();
                mRecorder.start();
                mStartedFlg = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stopRecord() {
        if (mStartedFlg) {
            try {
                handler.removeCallbacks(runnable);
                mRecorder.stop();
                mRecorder.reset();
                mRecorder.release();
                mRecorder = null;
                if (camera != null) {
                    camera.release();
                    camera = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mStartedFlg = false;
    }

    private void playRecord() {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.reset();
            Uri uri = Uri.parse(path);
            mediaPlayer = MediaPlayer.create(Cameractivity.this, uri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            mediaPlayer.setDisplay(mSurfaceHolder);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!mStartedFlg) {
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceview = null;
        mSurfaceHolder = null;
        handler.removeCallbacks(runnable);
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_exit:
                finish();
                break;
            case R.id.video_back:
                resetRecord();
                break;
            case R.id.video_over:
                Intent intent = new Intent();
                intent.putExtra("VideoUrl", path);
                setResult(FastAlarmActivity.SELECT_VIDEO, intent);
                finish();
                break;
        }
    }

    private void resetRecord() {
        notice.setVisibility(View.VISIBLE);
        video_exit.setVisibility(View.VISIBLE);
        circleProgressBar.setVisibility(View.VISIBLE);
        video_back.setVisibility(View.INVISIBLE);
        video_over.setVisibility(View.INVISIBLE);
    }
}
