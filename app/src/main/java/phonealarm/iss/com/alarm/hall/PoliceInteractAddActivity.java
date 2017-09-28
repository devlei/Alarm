package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.interact.AllInterAct;
import phonealarm.iss.com.alarm.bean.interact.InterActBean;
import phonealarm.iss.com.alarm.bean.interact.InterAttrConverter;
import phonealarm.iss.com.alarm.bean.interact.InteractFile;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PoliceInteractAddActivity extends Activity implements OnClickListener {

    private EditText mDescTv;
    private LinearLayout imgarray;
    private ImageView imgadd;
    private Uri photoUri;
    public static final int SELECT_PIC_BY_TACK_PHOTO = 2000;
    public static final int REQ_IMAGE = 2001;

    /**
     * open
     *
     * @param activity
     * @param requestCode
     */
    public static void openForResult(Activity activity, int requestCode) {
        if (activity != null) {
            Intent intent = new Intent(activity, PoliceInteractAddActivity.class);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_interact_add);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.police_interact);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.send);
        imgarray = (LinearLayout) findViewById(R.id.imgarray);
        imgadd = (ImageView) findViewById(R.id.imgadd);
        imgadd.setOnClickListener(this);
        mDescTv = (EditText) findViewById(R.id.pia_desc);


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
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                upLoad();
                break;
            case R.id.imgadd:
                dialog();
                break;
        }
    }

    private void upLoad() {
        //警民互动 xml构建
        InterActBean upLoadAlarmInfo = new InterActBean();
        upLoadAlarmInfo.setFk_appuser(AlarmApplication.mAlarmApplication.getUserId());
        upLoadAlarmInfo.setFk_telenum(AlarmApplication.mAlarmApplication.getUserId());
        upLoadAlarmInfo.setFk_nickname(AlarmApplication.mAlarmApplication.getUserId());
        upLoadAlarmInfo.setFk_date(System.currentTimeMillis() + "");
        upLoadAlarmInfo.setFk_content(mDescTv.getText().toString());

        List<InteractFile> list = new ArrayList<>();


        if (null != imgarray && imgarray.getChildCount() > 1) {
            for (int i = 0; i < imgarray.getChildCount() - 1; i++) {
                View childAt = imgarray.getChildAt(i);
                InteractFile upLoadFileBean = new InteractFile();
                upLoadFileBean.setType("jpg");
                upLoadFileBean.setFilename(UUID.randomUUID().toString() + ".jpg");
                if (null != childAt) {
                    String tag = (String) childAt.getTag(R.id.imageid);
                    upLoadFileBean.setValue(FileUtils.getimage(tag));
                    list.add(upLoadFileBean);
                }
            }
        }
        AllInterAct uploadFileList = new AllInterAct();
        uploadFileList.setFilelist(list);
        upLoadAlarmInfo.setFilelist(uploadFileList);

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterAttrConverter());
        String xmlString = xStream.toXML(upLoadAlarmInfo).replace("__", "_");
        System.out.println("===xmlString==" + xmlString);
        OkHttpUtils.postBuilder()
                .url(UrlSet.URL_JINMINHUDONG_ADD)
                .addParam("userid",
                        AlarmApplication.mAlarmApplication.getUserId())//AlarmApplication.mAlarmApplication.getUserId()
                .addParam("value", xmlString)
                .build()
                .buildRequestCall()
                .execute(new CallBack<ResponseMessageBean>() {//ResponseMessageBean

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onNext(ResponseMessageBean postBean) {
                        if (postBean.getResult() == 1) {
                            setResult(200);
                            finish();
                        } else {
                            Toast.makeText(PoliceInteractAddActivity.this, "服务器出错，请稍后重试", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        System.out.println("=====error====" + e);
                    }
                });

    }
}
