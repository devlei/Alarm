package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.modifyimg.AllUserInfo;
import phonealarm.iss.com.alarm.personal.observer.UserAdapterObserver;
import phonealarm.iss.com.alarm.personal.observer.UserObserverHelper;
import phonealarm.iss.com.alarm.utils.GlideUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PersonalInfoActivity extends Activity implements OnClickListener {

    private ImageView mHeaderIv;
    private TextView mNickNameTv;
    private TextView mPhoneTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PersonalInfoActivity.class);
            context.startActivity(intent);
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
                ToastUtils.showToast(this, "更换头像");
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

}
