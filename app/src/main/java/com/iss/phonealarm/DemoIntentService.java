package com.iss.phonealarm;

import android.content.Context;

import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.iss.phonealarm.bean.PushBean;
import com.iss.phonealarm.utils.IntentUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by zhaocuilong on 2017/10/31.
 */

public class DemoIntentService extends GTIntentService {

    public static final String CASE = "CASE";//要案
    public static final String VEHICLE_TRACK = "VEHICLE_TRACK";//车辆追踪
    public static final String SUSPECT_TRACK = "SUSPECT_TRACK";//疑犯追踪
    public static final String PEOPLE_LOST = "PEOPLE_LOST";//人员走失
    public static final String LOST_FOUND = "LOST_FOUND";//遗物招领

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        System.out.println("=====?onReceiveServicePid======");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        byte[] payload = msg.getPayload();
        if (null != payload && payload.length > 0) {
            String type = new String(payload);
            try {
                Gson gson = new Gson();
                PushBean pushBean = gson.fromJson(type, PushBean.class);
                String typeJump = pushBean.getType();
                if (CASE.equals(typeJump)) {
                    IntentUtils.openCommonSearch(context, R.integer.type_cases);
                }
                if (VEHICLE_TRACK.equals(typeJump)) {
                    IntentUtils.openCommonSearch(context, R.integer.type_vehicle_track);
                }
                if (SUSPECT_TRACK.equals(typeJump)) {
                    IntentUtils.openCommonSearch(context, R.integer.type_suspect_track);
                }
                if (PEOPLE_LOST.equals(typeJump)) {
                    IntentUtils.openCommonSearch(context, R.integer.type_people_lost);
                }
                if (LOST_FOUND.equals(typeJump)) {
                    IntentUtils.openCommonSearch(context, R.integer.type_lost_found);
                }
            } catch (Exception e) {
                System.out.println("====typeJump==Exception==" + e);
                e.printStackTrace();
            }
        }
        //TODO 此处书写跳转逻辑
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        System.out.println("=====?onReceiveClientId======");
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        System.out.println("=====?onReceiveOnlineState======");
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        System.out.println("=====?onReceiveCommandResult======");
    }


}
