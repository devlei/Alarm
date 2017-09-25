package phonealarm.iss.com.alarm.network;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class UrlSet {

    //basic url
    private static final String URL_BASIC = "http://ip:port/WebService.action?Service=";

    /**
     * login url
     */
    public static final String URL_LOGIN = URL_BASIC + "Login";

    /**
     * registered url
     */
    public static final String URL_REGISTERED = URL_BASIC + "Registered";
}
