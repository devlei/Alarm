package com.iss.phonealarm;

import android.content.Context;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;


/**
 * Created by zhaocuilong on 2017/10/31.
 */

public class DemoIntentService extends GTIntentService {

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        System.out.println("=====?onReceiveServicePid======");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        System.out.println("=====?onReceiveMessageData===getMessageId===" + msg.getMessageId());
        System.out.println("=====?onReceiveMessageData===getPayloadId===" + msg.getPayloadId());
        System.out.println("=====?onReceiveMessageData===getTaskId===" + msg.getTaskId());
        System.out.println("=====?onReceiveMessageData===getPayload[]===" + msg.getPayload().length);
        System.out.println("=====?onReceiveMessageData===String===" + new String(msg.getPayload()));
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
