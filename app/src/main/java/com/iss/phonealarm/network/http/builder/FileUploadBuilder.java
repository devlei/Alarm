package com.iss.phonealarm.network.http.builder;

import java.io.File;
import java.util.HashMap;

import com.iss.phonealarm.network.http.file.FileMapping;
import com.iss.phonealarm.network.http.request.OkHttpRequest;
import okhttp3.MediaType;
import com.iss.phonealarm.network.http.request.FileUploadHttpRequest;


/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class FileUploadBuilder extends OkHttpRequestBuilder<FileUploadBuilder> {

    private HashMap<String, FileMapping<MediaType, File>> fileParams;


    public FileUploadBuilder addFile(String key, MediaType mediaType, File file){

        if(fileParams == null){
            fileParams = new HashMap<String, FileMapping<MediaType, File>>();
        }
        this.fileParams.put(key, new FileMapping<MediaType, File>(mediaType, file));

        return this;
    }

    @Override
    public OkHttpRequest build() {
        return new FileUploadHttpRequest(this.url, this.params, this.fileParams);
    }
}
