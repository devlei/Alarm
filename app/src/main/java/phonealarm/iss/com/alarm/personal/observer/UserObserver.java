package phonealarm.iss.com.alarm.personal.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by weizhilei on 2017/9/27.
 */
public abstract class UserObserver implements Observer {

    /**
     * 用户信息变化
     */
    public abstract void onUserInfoChange();

    @Override
    public void update(Observable o, Object arg) {
        onUserInfoChange();
    }

}
