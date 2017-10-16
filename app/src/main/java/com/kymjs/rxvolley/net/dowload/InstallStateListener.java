package com.kymjs.rxvolley.net.dowload;

public interface InstallStateListener {
    public void installSuccess(String packName);

    public void installFailed(String packName, String exceptionMsg);
}
