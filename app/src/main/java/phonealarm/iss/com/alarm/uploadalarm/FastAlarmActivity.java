package phonealarm.iss.com.alarm.uploadalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;
import phonealarm.iss.com.alarm.R;

public class FastAlarmActivity extends Activity implements View.OnClickListener {

    /**
     * open
     *
     * @param context
     * @param typeResId
     */
    public static void open(Context context, int typeResId) {
        if (context != null) {
            Intent intent = new Intent(context, FastAlarmActivity.class);
            intent.putExtra(context.getString(R.string.key_type), typeResId);
            context.startActivity(intent);
        }
    }

    private static final int TIMR = 60;
    private int typeResId;
    private EditText mEditText;
    private ImageView video_btn, video_delete, video_record;
    private TextView video_local, voice_time;
    private ZzHorizontalProgressBar video_seekBar;
    //录音
    private MediaRecorder mediaRecorder;
    private File recordFile;
    private RecordPlayer player;

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_alarm);
        recordFile = new File("/mnt/sdcard", "kk.amr");
        player = new RecordPlayer(this);
        initView();
        init();
        eventHandle();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.video_edit);
        video_btn = (ImageView) findViewById(R.id.video_btn);
        video_delete = (ImageView) findViewById(R.id.video_delete);
        video_record = (ImageView) findViewById(R.id.video_record);

        video_local = (TextView) findViewById(R.id.video_local);
        voice_time = (TextView) findViewById(R.id.voice_time);

        video_delete.setOnClickListener(this);
        video_btn.setOnClickListener(this);

        video_seekBar = (ZzHorizontalProgressBar) findViewById(R.id.video_seekBar);
        video_seekBar.setEnabled(false);
        video_seekBar.setPadding(0);


    }

    private void init() {
        Intent intent = getIntent();
        if (null != intent) {
            typeResId = intent.getIntExtra(getString(R.string.key_type), 0);
        }

        TextView title = (TextView) findViewById(R.id.title_name);
        TextView titleOther = (TextView) findViewById(R.id.title_other);
        titleOther.setOnClickListener(this);
        findViewById(R.id.title_back).setOnClickListener(this);

        if (typeResId == R.integer.type_alarm) {
            title.setText(R.string.alarm);
            titleOther.setText("报警");
        } else if (typeResId == R.integer.type_eager_report) {
            title.setText(R.string.eager_report);
            titleOther.setText("举报");
        }

    }

    private void eventHandle() {
        video_record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("===down===");
                        startRecording();//开始录制
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println("===up===");
                        stopRecording();
                        checkTimeLength();
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void checkTimeLength() {
        //TODO
        buttonHandle(true);
    }

    private void buttonHandle(boolean isRecord) {
        video_record.setVisibility(isRecord ? View.GONE : View.VISIBLE);
        video_btn.setVisibility(isRecord ? View.VISIBLE : View.GONE);
        video_delete.setVisibility(isRecord ? View.VISIBLE : View.GONE);
    }

    private void upLoad() {
        this.finish();
        //TODO UPLOAD
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                upLoad();
                break;
            case R.id.video_btn:
                handleVieo();
                break;
            case R.id.video_delete:
                deleteHandle();
                break;
        }
    }

    private void deleteHandle() {
        buttonHandle(false);
        if (recordFile.exists()) {
            recordFile.delete();
        }
    }


    private void handleVieo() {
        if (player.isPlaying()) {
            pauseplayer();
            video_btn.setImageResource(R.drawable.video_pause);
        } else {
            playRecording();
            video_btn.setImageResource(R.drawable.video_play);
        }
    }

    private void startRecording() {
        // 判断，若当前文件已存在，则删除
        mediaRecorder = new MediaRecorder();
        if (recordFile.exists()) {
            recordFile.delete();
        }
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(recordFile.getAbsolutePath());
        try {
            // 准备好开始录音
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recordFile != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
        }
    }

    private void playRecording() {
        if (null != recordFile) {
            player.playRecordFile(recordFile);
        }
    }

    private void pauseplayer() {
        if (null != player) {
            player.pausePalyer();
        }
    }

    private void stopplayer() {
        if (null != player) {
            player.stopPalyer();
        }
    }

}
