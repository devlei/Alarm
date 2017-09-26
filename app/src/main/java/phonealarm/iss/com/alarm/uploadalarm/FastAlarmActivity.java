package phonealarm.iss.com.alarm.uploadalarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

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
    private ImageView video_btn, video_delete, video_record,
            imgadd;
    private TextView video_local, voice_time;
    private ZzHorizontalProgressBar video_seekBar;
    private LinearLayout imgarray;
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

        imgadd = (ImageView) findViewById(R.id.imgadd);
        imgadd.setOnClickListener(this);
        imgarray = (LinearLayout) findViewById(R.id.imgarray);


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

    private ArrayList<File> arrayList = new ArrayList<>();

    private void upLoad() {
        this.finish();
        //TODO UPLOAD
        if (null != imgarray) {
        }
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
            case R.id.imgadd:
                dialog();
                break;
        }
    }

    private void dialog() {
        if (null != imgarray && imgarray.getChildCount() < 5) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("请选择添加图片方式");
            //调用相机拍照
            builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    takePhoto();
                }
            });
            //从相册里面取照片
            builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    pickPhoto();
                }
            });
            builder.create().show();
        } else {
            Toast.makeText(this, "已经不能添加更多了", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 2000;
    public static final int REQ_IMAGE = 2001;

    private Uri photoUri;

    private void takePhoto() {
        //执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"
            ContentValues values = new ContentValues();
            photoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        } else {
            Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        //AndroidImagePicker.getInstance().setSelectMode(AndroidImagePicker.Select_Mode.MODE_MULTI);
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        //通过Intent 筛选所有的图片
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*;image/*");
        startActivityForResult(intent, REQ_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PIC_BY_TACK_PHOTO) {
            String[] pojo = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.TITLE, MediaStore.Images.Media.SIZE};
            Cursor cursor = getContentResolver().query(photoUri, pojo, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String picpath = cursor.getString(cursor.getColumnIndexOrThrow(pojo[0]));
                if (picpath != null &&
                        (picpath.endsWith(".png") || picpath.endsWith(".PNG") || picpath.endsWith(".jpg"))) {
                    addImage(picpath);
                } else {
                    Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }

        } else {
            try {
                Uri uri = data.getData();
                addImage(getRealFilePath(this, uri));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addImage(String imgUri) {
        System.out.println("==imgUri==>" + imgUri);
        if (null != imgarray && imgarray.getChildCount() < 5) {
            File file = new File(imgUri);
            final ImageView img = new ImageView(this);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.s_50),
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.rightMargin = getResources().getDimensionPixelSize(R.dimen.s_21);
            imgarray.addView(img, imgarray.getChildCount() - 1, lp);
            img.setOnClickListener(this);
            img.setTag(R.id.imageid, imgUri);
            Glide.with(this).load(file).into(img);
            img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    imgarray.removeView(img);
                    return true;
                }
            });
        }
    }

    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
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
