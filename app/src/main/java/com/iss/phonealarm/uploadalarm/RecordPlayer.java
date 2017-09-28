package com.iss.phonealarm.uploadalarm;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhaocuilong on 2017/9/25.
 */

public class RecordPlayer {

    private static MediaPlayer mediaPlayer;

    private Context mcontext;

    public RecordPlayer(Context context) {
        this.mcontext = context;
    }

    // 播放本地录音文件
    public void playRecordFile(File file) {
        if (file.exists() && file != null) {
            Uri uri = Uri.fromFile(file);
            mediaPlayer = MediaPlayer.create(mcontext, uri);
            mediaPlayer.start();
            //监听MediaPlayer播放完成
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer paramMediaPlayer) {

                }
            });
        }
    }


    // 播放网络在线录音文件
    public void playNetAudio(String resUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(resUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停播放录音
    public void pausePalyer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

    }

    public boolean isPlaying() {
        if (null != mediaPlayer) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    // 停止播放录音
    public void stopPalyer() {
        // 这里不调用stop()，调用seekto(0),把播放进度还原到最开始
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }
}
