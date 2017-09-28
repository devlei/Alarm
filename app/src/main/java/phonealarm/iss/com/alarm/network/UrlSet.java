package phonealarm.iss.com.alarm.network;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class UrlSet {

    //basic url
    private static final String URL_BASIC = "http://218.241.189.52:8089/WebService.action?Service=";

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
     */
    public static final String URL_ALARM_HISTORY = URL_BASIC + "GetAlarmService";

    /**
     * 警民互动列表
     */
    public static final String URL_POLICE_INTERACT = URL_BASIC + "queryFkInfo";

    /**
     * 处警评价
     */
    public static final String URL_ALARM_EVALUATE = URL_BASIC + "AssessAlarm";

    /**
     * 获取紧急联系人
     */
    public static final String URL_GET_CONTACTS = URL_BASIC + "GetContacts";

    /**
     * 添加紧急联系人
     */
    public static final String URL_ADD_CONTACTS = URL_BASIC + "AddContacts";

    /**
     * 重置密码
     */
    public static final String URL_REST_PASSWORD = URL_BASIC + "ResetPassword";

    /**
     * 查询用户信息
     */
    public static final String URL_GET_USER = URL_BASIC + "queryUserInfo";

    /**
     * 修改昵称
     */
    public static final String URL_RESET_NICKNAME = URL_BASIC + "ResetUsername";

    /**
     * 修改手机号
     */
    public static final String URL_RESET_PHONE = URL_BASIC + "ResetTelenum";

    //多媒体报警 OK OK
    public static final String YIJIAN_BAOJING = URL_BASIC + "CallAlarmService";

    /**
     * 租房采集
     */
    public static final String URL_RENT_COLLECT = URL_BASIC + "addNewInfo";

    /**
     * 旅馆采集
     */
    public static final String URL_HOTEL_COLLECT = URL_BASIC + "addHotelInfo";


}
