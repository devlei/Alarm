package phonealarm.iss.com.alarm.network.http.file;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class FileMapping<K, V> {

    public final K type;
    public final V file;

    public FileMapping(K k, V v){
        this.type = k;
        this.file = v;
    }
}
