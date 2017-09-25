package phonealarm.iss.com.alarm.network;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class UrlSet {

    //basic url
    private static final String URL_BASIC = "http://ip:port/WebService.action?Service=";

    /**
     * 登录
     */
    public static final String URL_LOGIN = URL_BASIC + "Login";

    /**
     * 注册
     */
    public static final String URL_REGISTERED = URL_BASIC + "Registered";

    /**
     * 要案
     */
    public static final String URL_CASES = URL_BASIC + "queryAllCasesInfo";

    /**
     * 车辆追踪
     */
    public static final String URL_VEHICLE_TRACK = URL_BASIC + "queryAllCarInfo";

    /**
     * 疑犯追踪
     */
    public static final String URL_SUSPECT_TRACK = URL_BASIC + "queryAllSuspectInfo";

    /**
     * 人员走失
     */
    public static final String URL_PEOPLE_LOST = URL_BASIC + "queryAllBelostInfo";

    /**
     * 遗失招领
     */
    public static final String URL_LOST_FOUND = URL_BASIC + "queryAllFoundInfo";

    /**
     * 报警历史
     *
     * @param userId
     * @return
     */
    public static String getAlarmHistory(String userId) {
        return URL_BASIC + "GetAlarmService&userid=" + userId;
    }

}
