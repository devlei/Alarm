package phonealarm.iss.com.alarm.network.http.builder;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import phonealarm.iss.com.alarm.network.http.file.FileMapping;
import phonealarm.iss.com.alarm.network.http.request.FileUploadHttpRequest;
import phonealarm.iss.com.alarm.network.http.request.OkHttpRequest;


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
