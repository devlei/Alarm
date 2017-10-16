package com.kymjs.rxvolley.net;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.kymjs.rxvolley.client.FileRequest;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.RequestConfig;
import com.kymjs.rxvolley.http.NetworkResponse;
import com.kymjs.rxvolley.http.Response;
import com.kymjs.rxvolley.http.URLHttpResponse;
import com.kymjs.rxvolley.http.VolleyError;
import com.kymjs.rxvolley.net.dowload.FileInfo.FileType;
import com.kymjs.rxvolley.toolbox.HttpParamsEntry;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by jczhang on 16-3-10.
 */
public class CustomFileRequest extends FileRequest {
    private FileType mFileType;

    public CustomFileRequest(FileType fileType, String filePath, RequestConfig config, HttpCallback callback) {
        super(filePath, config, callback);
        mFileType = fileType;
    }

    @Override
    public String getCacheKey() {
        return getUrl();
    }

    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        if (FileType.img == mFileType) {
            return Response.success(response.data, response.headers, null);
        } else {
            return super.parseNetworkResponse(response);
        }
    }

    @Override
    protected void deliverResponse(ArrayList<HttpParamsEntry> headers, byte[] response) {
        if (FileType.img == mFileType) {
            mCallback.onSuccess(null, getSuccessState());
        } else {
            super.deliverResponse(headers, response);
        }
    }

    private boolean isHasEnoughSpace(long fileSize) {
        File file = getStoreFile();
        if (null != file) {
            String parentPah = file.getParent();
            if (!TextUtils.isEmpty(parentPah)) {
                File parentDir = new File(parentPah);
                long freeSpace = parentDir.getFreeSpace();
                if (fileSize < freeSpace - 1024 * 1024) {
                    return true;
                }
            }
        }
        return false;
    }

    public byte[] handleResponse(URLHttpResponse response) throws IOException {
        long fileSize = response.getContentLength();
        boolean isHasEnoughSpace = isHasEnoughSpace(fileSize);
        if (!isHasEnoughSpace) {
            throw new IOException("no have enoughSpace!");
        }
        if (FileType.img == mFileType) {
            byte[] result;
            try {
                result = entityToBytes(response);
            } catch (VolleyError e) {
                throw new IOException(e.getMessage());
            }
            return result;
        } else {
            super.handleResponse(response);
            return getSuccessState();
        }
    }

    private byte[] getSuccessState() {
        byte[] result = null;
        try {
            String jsonStr = new Gson().toJson("success");
            result = jsonStr.getBytes(getConfig().mEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateProgress(long downloadedSize, long totalSize) {
        Log.d("jaunce", "progress:" + (downloadedSize * 100 / totalSize));
        if (mProgressListener != null) {
            mRequestQueue.getDelivery().postProgress(mProgressListener, downloadedSize, totalSize);
        }
    }
}
