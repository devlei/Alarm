package com.iss.phonealarm.uploadalarm;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.BaiduMapTestActivity;
import com.iss.phonealarm.LoadingDialog;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.uploadalarm.UpLoadAlarmInfo;
import com.iss.phonealarm.bean.uploadalarm.UpLoadAttrConverter;
import com.iss.phonealarm.bean.uploadalarm.UpLoadFileBean;
import com.iss.phonealarm.bean.uploadalarm.UploadFileList;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.personal.HeaderDialog;
import com.iss.phonealarm.utils.FileUtils;
import com.thoughtworks.xstream.XStream;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FastAlarmActivity extends Activity implements View.OnClickListener, HeaderDialog.OnHeaderDismissListener {

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
    private ImageView video_btn, video_delete, video_record, imgadd;
    private TextView video_local, voice_time;
    private ZzHorizontalProgressBar video_seekBar;
    private LinearLayout imgarray;
    //录音
    private MediaRecorder mediaRecorder;
    private File recordFile;
    private RecordPlayer player;

    public static final int TEXT = 121;
    public static final int PROGRESS = 122;
    private int duration = 0;
    private int totalLength = 0;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TEXT) {
                if (null != voice_time) {
                    if (totalLength < 20) {
                        if (totalLength < 10) {
                            voice_time.setText("0:0" + totalLength);
                        } else {
                            voice_time.setText("0:" + totalLength);
                        }
                        totalLength++;
                        mHandler.sendEmptyMessageDelayed(TEXT, 1000);
                    } else {
                        mHandler.removeMessages(TEXT);
                        stopRecording();
                        checkTimeLength();
                    }
                }
            } else if (msg.what == PROGRESS) {
                if (null != video_seekBar) {
                    try {
                        int amrDuration = getAmrDuration(recordFile);
                        video_seekBar.setMax(amrDuration);
                        if (amrDuration < 20) {
                            mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                            video_seekBar.setProgress(duration++);
                        } else {
                            duration = 0;
                            video_seekBar.setProgress(20);
                            mHandler.removeMessages(PROGRESS);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mHandler) {
            mHandler.removeMessages(PROGRESS);
            mHandler.removeMessages(TEXT);
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
            duration = 0;
        }
    }

    private TextView title;
    private RelativeLayout recore_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_alarm);
        recordFile = new File(Environment.getExternalStorageDirectory().getPath(), "kk.amr");
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
        recore_ll = (RelativeLayout) findViewById(R.id.recore_ll);
        findViewById(R.id.voice_icon).setOnClickListener(this);

        video_local = (TextView) findViewById(R.id.video_local);
        voice_time = (TextView) findViewById(R.id.voice_time);

        findViewById(R.id.location_ll).setOnClickListener(this);

        video_delete.setOnClickListener(this);
        video_btn.setOnClickListener(this);

        video_seekBar = (ZzHorizontalProgressBar) findViewById(R.id.video_seekBar);
        video_seekBar.setEnabled(false);
        video_seekBar.setPadding(0);
        video_seekBar.setMax(60);

        imgadd = (ImageView) findViewById(R.id.imgadd);
        imgadd.setOnClickListener(this);
        imgarray = (LinearLayout) findViewById(R.id.imgarray);

        video_local.setText(AlarmApplication.address);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != recore_ll)
                    recore_ll.setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.recore_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.root_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!(v.getId() == R.id.recore_ll || v.getId() == R.id.voice_icon)) {
                        if (null != recore_ll) {
                            if (recore_ll.getVisibility() == View.VISIBLE) {
                                recore_ll.setVisibility(View.INVISIBLE);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });


    }

    private void init() {
        Intent intent = getIntent();
        if (null != intent) {
            typeResId = intent.getIntExtra(getString(R.string.key_type), 0);
        }

        title = (TextView) findViewById(R.id.title_name);
        TextView titleOther = (TextView) findViewById(R.id.title_other);
        titleOther.setOnClickListener(this);
        findViewById(R.id.title_back).setOnClickListener(this);

        if (typeResId == R.integer.type_alarm) {
            title.setText(R.string.alarm);
            titleOther.setText("报警");
        } else if (typeResId == R.integer.type_eager_report) {
            title.setText(R.string.eager_report);
            titleOther.setText("举报");
        } else if (typeResId == R.integer.type_vehicle_track) {
            title.setText(R.string.vehicle_track);
            titleOther.setText("报警");
        } else if (typeResId == R.integer.type_suspect_track) {
            title.setText(R.string.suspect_track);
            titleOther.setText("报警");
        } else if (typeResId == R.integer.type_people_lost) {
            title.setText(R.string.people_lost);
            titleOther.setText("报警");
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
                        totalLength = 0;
                        startRecording();//开始录制
                        mHandler.removeMessages(TEXT);
                        mHandler.sendEmptyMessage(TEXT);
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println("===up===");
                        stopRecording();
                        checkTimeLength();
                        mHandler.removeMessages(TEXT);
                        return true;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private final static int READ_PHONE_STATE_REQUEST_CODE = 300;
    private final static String DANGEROUS_PERMISSION[] = new String[]{Manifest.permission.RECORD_AUDIO
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限授予成功,初始化
                recore_ll.setVisibility(View.VISIBLE);
            } else {

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void permissionRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED || ActivityCompat
                    .checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat
                    .checkSelfPermission(
                            this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                ActivityCompat.requestPermissions(this, DANGEROUS_PERMISSION, READ_PHONE_STATE_REQUEST_CODE);
            } else {
                //权限已经授予,直接初始化
                recore_ll.setVisibility(View.VISIBLE);
            }
        }
    }

    private void checkTimeLength() {
        buttonHandle(true);
    }

    public static int getAmrDuration(File file) throws IOException {
        long duration = -1;
        int[] packedSize = {12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0};
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = file.length();// 文件的长度
            int pos = 6;// 设置初始位置
            int frameCount = 0;// 初始帧数
            int packedPos = -1;

            byte[] datas = new byte[1];// 初始数据值
            while (pos <= length) {
                randomAccessFile.seek(pos);
                if (randomAccessFile.read(datas, 0, 1) != 1) {
                    duration = length > 0 ? ((length - 6) / 650) : 0;
                    break;
                }
                packedPos = (datas[0] >> 3) & 0x0F;
                pos += packedSize[packedPos] + 1;
                frameCount++;
            }

            duration += frameCount * 20;// 帧数*20
        } finally {
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        }
        return (int) ((duration / 1000) + 1);
    }

    private void buttonHandle(boolean isRecord) {
        video_record.setVisibility(isRecord ? View.GONE : View.VISIBLE);
        video_btn.setVisibility(isRecord ? View.VISIBLE : View.GONE);
        video_delete.setVisibility(isRecord ? View.VISIBLE : View.GONE);
    }

    private ArrayList<File> arrayList = new ArrayList<>();

    private void upLoad() {
        //一键报警 xml构建
        UpLoadAlarmInfo upLoadAlarmInfo = new UpLoadAlarmInfo();
        upLoadAlarmInfo.setAlarm_addres(AlarmApplication.address);
        upLoadAlarmInfo.setAlarm_content(mEditText.getText().toString());
        upLoadAlarmInfo.setAlarm_id(UUID.randomUUID().toString());
        upLoadAlarmInfo.setAlarm_latitude(weidu + "");
        upLoadAlarmInfo.setAlarm_longtitude(jingdu + "");
        if (title.getText().equals("报警")) {
            upLoadAlarmInfo.setInfo_type("1");
        } else {
            upLoadAlarmInfo.setInfo_type("2");
        }
        upLoadAlarmInfo.setAlarm_phone(AlarmApplication.mAlarmApplication.getUserId());
        upLoadAlarmInfo.setAlarm_type("1");

        List<UpLoadFileBean> list = new ArrayList<>();
        if (null != imgarray && imgarray.getChildCount() > 1) {
            for (int i = 0; i < imgarray.getChildCount() - 1; i++) {
                View childAt = imgarray.getChildAt(i);
                UpLoadFileBean upLoadFileBean = new UpLoadFileBean();
                upLoadFileBean.setType("jpg");
                upLoadFileBean.setFilename(UUID.randomUUID().toString() + ".jpg");
                if (null != childAt) {
                    String tag = (String) childAt.getTag(R.id.imageid);
                    upLoadFileBean.setValue(FileUtils.getimage(tag));
                    list.add(upLoadFileBean);
                }
            }
        }
        if (recordFile != null && recordFile.exists()) {
            System.out.println("----path----" + recordFile.getAbsolutePath());
            UpLoadFileBean upLoadFileBean = new UpLoadFileBean();
            upLoadFileBean.setType("amr");
            upLoadFileBean.setFilename(UUID.randomUUID().toString() + ".amr");
            upLoadFileBean.setValue(FileUtils.getFileStr(recordFile.getAbsolutePath()));
            list.add(upLoadFileBean);
        }
        UploadFileList uploadFileList = new UploadFileList();
        uploadFileList.setFile(list);
        upLoadAlarmInfo.setFilelist(uploadFileList);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new UpLoadAttrConverter());
        String xmlString = xStream.toXML(upLoadAlarmInfo).replace("__", "_");
        System.out.println("===xmlString==" + xmlString);
        LoadingDialog.show(this);
        OkHttpUtils.postBuilder()
                .url(UrlSet.YIJIAN_BAOJING)
                .addParam("userid",
                        AlarmApplication.mAlarmApplication.getUserId())//AlarmApplication.mAlarmApplication.getUserId()
                .addParam("value", xmlString)
                .build()
                .buildRequestCall()
                .execute(new CallBack<ResponseMessageBean>() {//ResponseMessageBean InterQueryBean

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onNext(ResponseMessageBean postBean) {
                        LoadingDialog.dismissSelf();
                        finish();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LoadingDialog.dismissSelf();
                        System.out.println("=====error====" + e);
                    }
                });

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
                if (canSelect()) {
                    HeaderDialog headerDialog = (HeaderDialog) HeaderDialog.show(this, this);
                    headerDialog.setFirstText("摄像");
                    headerDialog.setSecontText("拍照");
                    headerDialog.setThirdText("从相册选择");
                }
                break;
            case R.id.location_ll:
                setLocation();
                break;
            case R.id.voice_icon:
                permissionRequest();
                break;
        }
    }

    private void setLocation() {
        if (null != recore_ll)
            recore_ll.setVisibility(View.INVISIBLE);
        if (null != title) {
            BaiduMapTestActivity.open(this, title.getText().toString(), MAP_REQUEST_CODE);
        }
    }

    private boolean canSelect() {
        if (null != recore_ll)
            recore_ll.setVisibility(View.INVISIBLE);
        if (null != imgarray && imgarray.getChildCount() < 5) {
            return true;
        } else {
            Toast.makeText(this, "已经不能添加更多了", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 2000;
    public static final int REQ_IMAGE = 2001;
    public static final int MAP_REQUEST_CODE = 2003;
    public static double weidu, jingdu;

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
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//video/*;image/*
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
                if (picpath != null && (picpath.endsWith(".png") || picpath.endsWith(".PNG") || picpath.endsWith(
                        ".jpg"))) {
                    addImage(picpath);
                } else {
                    Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }

        } else if (requestCode == REQ_IMAGE) {
            try {
                Uri uri = data.getData();
                addImage(getRealFilePath(this, uri));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == MAP_REQUEST_CODE) {
            if (null != data) {
                String address = data.getStringExtra("Address");
                video_local.setText(address);
                weidu = data.getDoubleExtra("WEIDU", 0.0);
                jingdu = data.getDoubleExtra("JINGDU", 0.0);
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
                    getResources().getDimensionPixelSize(R.dimen.s_50), LinearLayout.LayoutParams.MATCH_PARENT);
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
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != recore_ll)
                        recore_ll.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver()
                    .query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
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
        if (null != recordFile && recordFile.exists()) {
            stopplayer();
            recordFile.delete();
            mHandler.removeMessages(TEXT);
            voice_time.setText("0:00");
            duration = 0;
            totalLength = 0;
        }
    }


    private void handleVieo() {
        if (player.isPlaying()) {
            pauseplayer();
            mHandler.removeMessages(PROGRESS);
            video_btn.setImageResource(R.drawable.video_pause);
        } else {
            playRecording();
            video_seekBar.setProgress(0);
            mHandler.removeMessages(PROGRESS);
            mHandler.sendEmptyMessage(PROGRESS);
            duration = 0;
            video_btn.setImageResource(R.drawable.video_play);
        }
    }

    private void startRecording() {
        // 判断，若当前文件已存在，则删除
        mediaRecorder = new MediaRecorder();
        if (recordFile.exists()) {
            System.out.println("---delete--->");
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
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (Exception e) {
            }
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
        if (null != player && player.isPlaying()) {
            player.stopPalyer();
        }
    }

    @Override
    public void onDismiss(int type) {
        if (HeaderDialog.TYPE_CAMERA == type) {
            takeVideo();
        } else if (HeaderDialog.TYPE_ALBUM == type) {
            takePhoto();
        } else if (HeaderDialog.TYPE_VIDEO == type) {
            pickPhoto();
        }
    }

    private void takeVideo() {

    }
}
