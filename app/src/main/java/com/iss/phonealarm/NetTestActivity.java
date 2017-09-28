package com.iss.phonealarm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.carinfo.InformationBean;
import com.iss.phonealarm.bean.searchalarm.CheckAlarmMessage;
import com.iss.phonealarm.bean.uploadalarm.UpLoadAlarmInfo;
import com.iss.phonealarm.bean.uploadalarm.UpLoadAttrConverter;
import com.iss.phonealarm.bean.uploadalarm.UpLoadFileBean;
import com.iss.phonealarm.bean.uploadalarm.UploadFileList;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.utils.FileUtils;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class NetTestActivity extends Activity {

    public static final String IMAHEPATH1 = "/storage/emulated/0/DCIM/Camera/IMG_20170925_193023_BURST001_COVER.jpg";
    public static final String IMAGEPATH1 = "/storage/emulated/0/Pictures/1506399245916.jpg";
    public static final String IMAGEPATH3 = "/storage/emulated/0/image/icon/jfsc.png/jfsc.png";
    public static final String IMP4EPATH3 = "/storage/emulated/0/DCIM/Camera/VID_20170925_193039.mp4";


    //查询报警记录  OK OK
    //public static final String CHECK_ALARM_LIST = "http://192.168.0.106:8085/WebService
    // .action?Service=GetAlarmService";

    //车辆列表接口  OK OK
    //public static final String queryAllCarInfo = "http://192.168.0.106:8085/WebService
    // .action?Service=queryAllCarInfo";

    //处警评价   OK OK
    //public static final String CHUJING_PINGJIA = "http://218.241.189.52:8089/WebService.action?Service=AssessAlarm";

    //多媒体报警 OK OK
//    public static final String YIJIAN_BAOJING = "http://218.241.189.52:8089/WebService.action?Service=CallAlarmService";

    //报警记录 OK OK
    //public static final String BAOJING_JILU = "http://218.241.189.52:8089/WebService.action?Service=GetAlarmService";

    //注册 OK OK
    //public static final String REGIST = "http://192.168.0.106:8085/WebService.action?Service=Registered";

    //登录 OK OK
    //public static final String LOGIN = "http://192.168.0.106:8085/WebService.action?Service=Login";

    //重置密码 OK OK
    //public static final String REPWD = "http://192.168.0.106:8085/WebService.action?Service=ResetPassword";

    //添加联系人 OK OK
    //public static final String ADDPERSON = "http://192.168.0.106:8085/WebService.action?Service=AddContacts";

    //获取联系人 OK OK
    //public static final String GETPERSON = "http://192.168.0.106:8085/WebService.action?Service=GetContacts";

    //删除联系人 最后在测
    public static final String DELETDPERSON = "http://192.168.0.106:8085/WebService.action?Service=DeleteContacts";

    //信息采集 OK OK
    //public static final String XINXICAIJI = "http://192.168.0.106:8085/WebService.action?Service=addNewInfo";

    //旅馆采集 OK OK
    //public static final String LVGUANCAIJI = "http://192.168.0.106:8085/WebService.action?Service=addHotelInfo";

    //查看个人信息 OK OK
    //public static final String CHAKANGERENXINXI = "http://192.168.0.106:8085/WebService.action?Service=queryUserInfo";

    //修改个人昵称 OK OK
    //public static final String XIUGAIGERENNICHENG = "http://192.168.0.106:8085/WebService
    // .action?Service=ResetUsername";

    //修改个人头像  未测
//    public static final String XIUGAIGERENTOUXIANG = "http://192.168.0.106:8085/WebService.action?Service=Resetpicture";

    //更换手机号 OK OK
    //public static final String GENGGAISHOUJIHAO = "http://192.168.0.106:8085/WebService.action?Service=ResetTelenum";

    //要案列表 OK OK
    //public static final String YAOANLIEBIAO = "http://192.168.0.106:8085/WebService.action?Service=queryAllCasesInfo";

    //疑犯列表接口 OK OK
    //public static final String XIANYIFANLIST = "http://192.168.0.106:8085/WebService
    // .action?Service=queryAllSuspectInfo";

    //招领列表 OK OK
    //public static final String ZHAOLINGLIST = "http://192.168.0.106:8085/WebService.action?Service=queryAllFoundInfo";

    //走失列表接口 OK OK
    //public static final String ZOUSHILIST = "http://192.168.0.106:8085/WebService.action?Service=queryAllBelostInfo";

    //警民互动添加 OK OK
//    public static final String JINGMINHUDONGTIANJIA = "http://192.168.0.106:8085/WebService.action?Service=addNewFk";

    //警民互动接口 OK OK
    //public static final String JINMINHUDONGCHAXUN = "http://218.241.189.52:8089/WebService
    // .action?Service=queryFkInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);
    }

    public void get(View view) {
        OkHttpUtils.getBuilder().url("").build().buildRequestCall().execute(new CallBack<InformationBean>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onNext(InformationBean getBean) {
                System.out.println("----getBean----" + getBean);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                System.out.println("----onError----" + e);
            }
        });
    }

    public void post(View view) {

        //一键报警
        UpLoadAlarmInfo upLoadAlarmInfo = new UpLoadAlarmInfo();
        upLoadAlarmInfo.setAlarm_addres("地址");
        upLoadAlarmInfo.setAlarm_content("内容");
        upLoadAlarmInfo.setAlarm_id("121313");
        upLoadAlarmInfo.setAlarm_latitude("11.2");
        upLoadAlarmInfo.setAlarm_longtitude("323.3");
        upLoadAlarmInfo.setAlarm_phone("110");
        upLoadAlarmInfo.setAlarm_type("1");


        UpLoadFileBean upLoadFileBean = new UpLoadFileBean();
        upLoadFileBean.setType("jpg");
        upLoadFileBean.setFilename("111.jpg");
        //        upLoadFileBean.setValue(getImageStr(IMAGEPATH3));
        upLoadFileBean.setValue(FileUtils.getimage(IMAHEPATH1));
        UpLoadFileBean upLoadFileBean2 = new UpLoadFileBean();
        upLoadFileBean2.setType("jpg");
        upLoadFileBean2.setFilename("222.jpg");
        //        upLoadFileBean2.setValue(getImageStr(IMAGEPATH3));
        upLoadFileBean.setValue(FileUtils.getimage(IMAGEPATH1));
        List<UpLoadFileBean> list = new ArrayList<>();
        list.add(upLoadFileBean);
        list.add(upLoadFileBean2);


        UploadFileList uploadFileList = new UploadFileList();
        uploadFileList.setFile(list);

        upLoadAlarmInfo.setFilelist(uploadFileList);

        //        //注册,登录  Userid==username==telephone
        //        UserInfoBean userInfoBean = new UserInfoBean();
        //        userInfoBean.setTelephone("15810632322");
        //        userInfoBean.setUserid("15810632322");
        //        userInfoBean.setUsername("昵称");
        //        userInfoBean.setPassword("111111");//old 123 new 321
        //        userInfoBean.setStartadress("海淀区");

        //添加联系人
        //        AddContact addContactn=new AddContact();
        //        addContactn.setContacts_name("lisi");
        //        addContactn.setContacts_bind("fuzi");
        //        addContactn.setContacts_phone("999");

        //信息采集
        //租房
        //        InfoCollectBean infoCollectBean = new InfoCollectBean();
        //        infoCollectBean.setSHANGBAOID("12306");
        //        infoCollectBean.setHOUSEADRESS("安河桥");
        //        infoCollectBean.setFUZENAME("张三");
        //        infoCollectBean.setFUZERENCARD("13028918787987");
        //        infoCollectBean.setSTARTTIME("2015-4-3");
        //        infoCollectBean.setENDTIME("2017-3-5");
        //        //旅馆
        //        infoCollectBean.setHOTEL_ADRESS("高米店");
        //        infoCollectBean.setHOTEL_NAME("天上人间");
        //        infoCollectBean.setHOME_NAME("房间名称");
        //
        //        LiablePerson liablePerson = new LiablePerson();
        //        liablePerson.setPNAME("大小写");
        //        liablePerson.setPIDCARD("2124124121");
        //        LiablePerson liablePerson2 = new LiablePerson();
        //        liablePerson2.setPNAME("大小写");
        //        liablePerson2.setPIDCARD("2124124121");
        //        List<LiablePerson> liablePersonList = new ArrayList<>();
        //        liablePersonList.add(liablePerson);
        //        liablePersonList.add(liablePerson2);
        //
        //        LiablePersonList libs = new LiablePersonList();
        //        libs.setRzeList(liablePersonList);
        //
        //        infoCollectBean.setLiablePersonList(libs);

        //警民互动添加
        //        InterActBean interActBean = new InterActBean();
        //        interActBean.setFk_content("内容");
        //        interActBean.setFk_date("2015-12-3");
        //        interActBean.setFk_nickname("昵称");
        //        interActBean.setFk_telenum("666");
        //        interActBean.setFk_appuser("110");
        //        AllInterAct allInterAct = new AllInterAct();
        //        InteractFile interactFile = new InteractFile();
        //        interactFile.setFilename("111");
        //        interactFile.setType("jpg");
        //        interactFile.setValue(getImageStr(IMAGEPATH3));
        //        InteractFile interactFile2 = new InteractFile();
        //        interactFile2.setFilename("222");
        //        interactFile2.setType("jpg");
        //        interactFile2.setValue(getImageStr(IMAGEPATH3));
        //        ArrayList<InteractFile> interactFiles = new ArrayList<>();
        //        interactFiles.add(interactFile);
        //        interactFiles.add(interactFile2);
        //        allInterAct.setFilelist(interactFiles);
        //
        //        interActBean.setFilelist(allInterAct);


        //        AlarmJudgeBean alarmJudgeBean = new AlarmJudgeBean();
        //        alarmJudgeBean.setAlarm_id("123123");
        //        alarmJudgeBean.setAssess_content("baojingle");
        //        alarmJudgeBean.setAssess_level("0");
        //        alarmJudgeBean.setPhone("15810632322");


        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new UpLoadAttrConverter());
        String xmlString = xStream.toXML(upLoadAlarmInfo).replace("__", "_");
        System.out.println("===>" + xmlString);

        OkHttpUtils.postBuilder()
                .url("")// YIJIAN_BAOJING  CHAKAN_PERSONINFO CHECK_ALARM_LIST
                .addParam("userid", "15810632322")
                .addParam("value", xmlString)
                .build()
                .buildRequestCall()
                .execute(new CallBack<ResponseMessageBean>() {//ResponseMessageBean InterQueryBean

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onNext(ResponseMessageBean postBean) {
                        System.out.println("=====onNext====" + postBean);
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

    public void file(View view) {
        OkHttpUtils.postBuilder().url("")//
                .addParam("userid", "11231231").build().buildRequestCall().execute(new CallBack<CheckAlarmMessage>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onNext(CheckAlarmMessage postBean) {
                System.out.println("=====onNext====" + postBean);
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

    public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //String imgFile = "d:\\111.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码

        //        BASE64Encoder encoder = new BASE64Encoder();
        //        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

}
