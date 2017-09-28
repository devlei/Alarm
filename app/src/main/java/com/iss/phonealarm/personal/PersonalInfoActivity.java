package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.modifyimg.AllUserInfo;
import com.iss.phonealarm.bean.modifyimg.HeaderAttrConverter;
import com.iss.phonealarm.bean.modifyimg.HeaderFile;
import com.iss.phonealarm.bean.modifyimg.ModifyHeaderImage;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.personal.HeaderDialog.OnHeaderDismissListener;
import com.iss.phonealarm.personal.observer.UserAdapterObserver;
import com.iss.phonealarm.personal.observer.UserObserverHelper;
import com.iss.phonealarm.utils.FileUtils;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.IntentUtils;
import com.iss.phonealarm.utils.ToastUtils;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.util.UUID;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PersonalInfoActivity extends Activity implements OnClickListener, OnHeaderDismissListener {

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROP = 3;

    private ImageView mHeaderIv;
    private TextView mNickNameTv;
    private TextView mPhoneTv;

    /**
     * open
     *
     * @param activity
     * @param requestCode
     */
    public static void openForResult(Activity activity, int requestCode) {
        if (activity != null) {
            Intent intent = new Intent(activity, PersonalInfoActivity.class);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        init();
        setData(AlarmApplication.mUserInfo);
        UserObserverHelper.getInstance().addUserObserver(mUserAdapterObserver);
    }

    private void init() {
        mHeaderIv = (ImageView) findViewById(R.id.pi_header);
        mHeaderIv.setOnClickListener(this);
        mNickNameTv = (TextView) findViewById(R.id.pi_nickname);
        findViewById(R.id.pi_nickname_container).setOnClickListener(this);
        mPhoneTv = (TextView) findViewById(R.id.pi_phone);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.people_info);
    }

    private void setData(AllUserInfo userInfo) {
        if (userInfo != null) {
            GlideUtils.loadImage(this, userInfo.getUser_picture(), R.drawable.icon_header_default, mHeaderIv);
            mNickNameTv.setText(userInfo.getUser_username());
            mPhoneTv.setText(userInfo.getUser_userid());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.pi_header:
                HeaderDialog.show(this, this);
                break;
            case R.id.pi_nickname_container:
                IntentUtils.openChangeNickName(this);
                break;
        }
    }

    private UserAdapterObserver mUserAdapterObserver = new UserAdapterObserver() {
        @Override
        public void onUserInfoChange() {
            setData(AlarmApplication.mUserInfo);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserObserverHelper.getInstance().removeUserObserver(mUserAdapterObserver);
    }

    @Override
    public void onDismiss(int type) {
        switch (type) {
            case HeaderDialog.TYPE_CAMERA:
                toCamera();
                break;
            case HeaderDialog.TYPE_ALBUM:
                toAlbum();
                break;
        }
    }

    /**
     * 打开照相机
     */
    private void toCamera() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //图片存储路径
            File tempFile = new File(Environment.getExternalStorageDirectory(), "alarm_header.jpg");
            Uri tempUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        } else {
            ToastUtils.showToast(this, "sd卡不存在");
        }
    }

    /**
     * 打开相册
     */
    private void toAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                File tempFile = new File(Environment.getExternalStorageDirectory(), "alarm_header.jpg");
                cropPhoto(Uri.fromFile(tempFile));
                break;
            case REQUEST_CODE_ALBUM:
                cropPhoto(data.getData());
                break;
            case REQUEST_CODE_CROP:
                setImageToView(data);
                break;
        }
    }

    private void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
            String string = FileUtils.compressImage(photo);

            ModifyHeaderImage modifyHeaderImage = new ModifyHeaderImage();
            modifyHeaderImage.setUserid(AlarmApplication.mAlarmApplication.getUserId());
            HeaderFile headerFile = new HeaderFile();
            headerFile.setFilename(UUID.randomUUID().toString() + ".jpg");
            headerFile.setType("jpg");
            headerFile.setValue(string);
            modifyHeaderImage.setHeaderFile(headerFile);

            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new HeaderAttrConverter());
            String xmlString = xStream.toXML(modifyHeaderImage).replace("__", "_");

            System.out.println("===xmlString==" + xmlString);
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_HEADER_MODIFY)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())//AlarmApplication.mAlarmApplication.getUserId()
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {//ResponseMessageBean InterQueryBean

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean.getResult() == 1) {
                                mHeaderIv.setImageBitmap(photo);
                                setResult(200);
                                finish();
                            } else {
                                Toast.makeText(PersonalInfoActivity.this, "服务器请求出错,请重新尝试", Toast.LENGTH_SHORT).show();
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

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

}
