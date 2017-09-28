package com.iss.phonealarm.network.http.request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.iss.phonealarm.network.http.file.FileMapping;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class FileUploadHttpRequest extends OkHttpRequest {
    private HashMap<String, FileMapping<MediaType, File>> fileParams;

    public FileUploadHttpRequest(String url, HashMap<String, String> params,
                                 HashMap<String, FileMapping<MediaType, File>> fileParams){
        super(url, params);
        this.fileParams = fileParams;
    }

    @Override
    public void initBuilder() {
        this.builder.url(this.url);
    }

    @Override
    public Request generateRequest() {
        initBuilder();

        MultipartBody.Builder bodyBuilder  = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);
        if(this.params != null){
            for(Map.Entry<String, String> entry : params.entrySet()){
               bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if(this.fileParams != null){
            for(Map.Entry<String, FileMapping<MediaType, File>> entry : fileParams.entrySet()){
               bodyBuilder.addFormDataPart(entry.getKey(),entry.getValue().file.getName(),
                       RequestBody.create(entry.getValue().type, entry.getValue().file));
            }
        }
        MultipartBody body = bodyBuilder.build();
        Request request = this.builder.post(body).build();
        return request;
    }
}
