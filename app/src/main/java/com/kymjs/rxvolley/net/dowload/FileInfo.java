package com.kymjs.rxvolley.net.dowload;

import com.iss.phonealarm.utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileInfo {

    private String path;

    private String absolutePath;

    public enum FileType {
        img, apk, commonFile
    }

    public FileType type;

    public String url;

    private FileInfo(FileType fileType, String suffix) {
        type = fileType;
        String pathStr = new StringBuffer(DownLoadManager.getInstance().getRootPath()).append("/")
                .append(suffix)
                .toString();
        path = creatFile(pathStr) ? pathStr : "";
    }

    public static FileInfo getApkFileInfo() {
        FileInfo fileInfo = new FileInfo(FileType.apk, "apk_cache");
        return fileInfo;
    }

    public static FileInfo getImgFileInfo() {
        FileInfo fileInfo = new FileInfo(FileType.img, "image_cache");
        return fileInfo;
    }

    public static FileInfo getCommonFileInfo() {
        FileInfo fileInfo = new FileInfo(FileType.commonFile, "commonfile_cache");
        return fileInfo;
    }

    public String getPath() {
        return path;
    }

    private static boolean creatFile(String path) {
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            if (pathFile.mkdir()) {
                try {
                    String command = "chmod " + "777" + " " + path;
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String urlStr) {
        url = urlStr;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String name) {
        absolutePath = FileUtils.getFileAbsolutePath(path, name);
    }

}
