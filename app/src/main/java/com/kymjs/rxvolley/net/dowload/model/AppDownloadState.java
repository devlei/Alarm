package com.kymjs.rxvolley.net.dowload.model;

public class AppDownloadState {
    public String packName;

    public AppStateType appState = AppStateType.prepared;

    public String appName;

    public AppFrom appFrom = AppFrom.desktop;

    protected int process;

    /**
     * 失败原因（下载或者安装）
     */
    public String mErrorMsg;

    /**
     * 失败状态码（下载或者安装）
     */
    public int mErrorCode;

    public AppDownloadState() {

    }

    public AppDownloadState(String packName, AppStateType appState, String appName) {
        this.packName = packName;
        this.appState = appState;
        this.appName = appName;
    }

    public void setProcess(int percentProcess) {
        this.process = percentProcess;
    }

    public int getPercentProcess(long progressSize, long total){
        return (int) (progressSize * 100 / total);
    }

    public int getProcess() {
        return process;
    }
}
