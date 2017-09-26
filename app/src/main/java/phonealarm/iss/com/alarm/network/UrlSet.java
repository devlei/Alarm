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
    public static String getAlarmHistoryUrl(String userId) {
        return URL_BASIC + "GetAlarmService&userid=" + userId;
    }

    /**
     * 修改昵称
     *
     * @param userId
     * @return
     */
    public static String getChangeNickNameUrl(String userId) {
        return URL_BASIC + "ResetUsername&userid=" + userId;
    }

    /**
     * 处警评价
     *
     * @param userId
     * @return
     */
    public static String getAlarmEvaluateUrl(String userId) {
        return URL_BASIC + "AssessAlarm&userid=" + userId;
    }

    /**
     * 紧急联系人
     *
     * @param userId
     * @return
     */
    public static String getEmergencyContactUrl(String userId) {
        return URL_BASIC + "GetContacts&userid=" + userId;
    }

    /**
     * 添加联系人
     *
     * @param userId
     * @return
     */
    public static String getAddContactUrl(String userId) {
        return URL_BASIC + "AddContacts&userid=" + userId;
    }

}
