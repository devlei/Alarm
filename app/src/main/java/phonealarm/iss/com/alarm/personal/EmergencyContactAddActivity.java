package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.thoughtworks.xstream.XStream;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.contact.AddContact;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryAttrConverter;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactAddActivity extends Activity implements OnClickListener {

    private EditText mNameEt;
    private EditText mRelationEt;
    private EditText mPhoneEt;
    private EditText mAddressEt;

    /**
     * open
     *
     * @param activity
     * @param requestCode
     */
    public static void openForResult(Activity activity, int requestCode) {
        if (activity != null) {
            Intent intent = new Intent(activity, EmergencyContactAddActivity.class);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact_add);
        init();

    }

    private void init() {
        mNameEt = (EditText) findViewById(R.id.eca_name);
        mRelationEt = (EditText) findViewById(R.id.eca_relation);
        mPhoneEt = (EditText) findViewById(R.id.eca_phone);
        mAddressEt = (EditText) findViewById(R.id.eca_address);

        ((TextView) findViewById(R.id.title_name)).setText(R.string.emergency_contact);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        findViewById(R.id.eca_address_book).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                addContact();
                break;
            case R.id.eca_address_book:
                openAddressBook();
                break;
        }
    }

    private void addContact() {
        if (TextUtils.isEmpty(mNameEt.getText())) {
            ToastUtils.showToast(this, "姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(mRelationEt.getText())) {
            ToastUtils.showToast(this, "关系不能为空");
            return;
        }
        if (TextUtils.isEmpty(mPhoneEt.getText())) {
            ToastUtils.showToast(this, "电话不能为空");
            return;
        }
        AddContact addContact = new AddContact();
        addContact.setContacts_name(mNameEt.getText().toString());
        addContact.setContacts_bind(mRelationEt.getText().toString());
        addContact.setContacts_phone(mPhoneEt.getText().toString());
        if (!TextUtils.isEmpty(mAddressEt.getText())) {
            addContact.setContacts_address(mAddressEt.getText().toString());
        } else {
            addContact.setContacts_address("");
        }

        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new InterQueryAttrConverter());
        String xmlString = xStream.toXML(addContact).replace("__", "_");

        if (AlarmApplication.mAlarmApplication.isLogin()) {
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_ADD_CONTACTS)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    ToastUtils.showToast(EmergencyContactAddActivity.this, "添加成功");
                                    finish();
                                    setResult(RESULT_OK);
                                } else {
                                    ToastUtils.showToast(EmergencyContactAddActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
    }

    /**
     * 打开通讯录
     */
    private void openAddressBook() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) getPhoneContact(data);
    }

    /**
     * 获取手机通讯录联系人信息
     **/
    private void getPhoneContact(Intent data) {
        if (data != null) {
            ContentResolver resolver = getContentResolver();
            Cursor phoneCursor = resolver.query(data.getData(), null, null, null, null);
            if (phoneCursor != null) {
                phoneCursor.moveToFirst();
                //获取姓名
                int nameIndex = phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String username = phoneCursor.getString(nameIndex);
                mNameEt.setText(username);
                //获取电话号码
                int idIndex = phoneCursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = phoneCursor.getString(idIndex);
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId;
                Cursor numberCursor = resolver.query(uri, null, selection, null, null);
                if (numberCursor != null) {
                    numberCursor.moveToFirst();
                    int numberIndex = numberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = numberCursor.getString(numberIndex);
                    mPhoneEt.setText(number);
                }
                try {
                    phoneCursor.close();
                    numberCursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
