package phonealarm.iss.com.alarm.bean.modifyimg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("userInfo")
public class AllUserInfo {

    @XStreamAlias("user_userid")
    private String user_userid;

    @XStreamAlias("user_username")
    private String user_username;

    @XStreamAlias("user_picture")
    private String user_picture;

    public String getUser_userid() {
        return user_userid;
    }

    public void setUser_userid(String user_userid) {
        this.user_userid = user_userid;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "AllUserInfo{" +
                "user_userid='" + user_userid + '\'' +
                ", user_username='" + user_username + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }
}
