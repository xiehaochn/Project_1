package Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/14.
 */
public class InviteRecord implements Serializable{
    private String userid;
    private String time;
    private String ifSuccessed;

    public String getUserid() {
        return userid;
    }

    public String getTime() {
        return time;
    }

    public String getIfSuccessed() {
        return ifSuccessed;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIfSuccessed(String ifSuccessed) {
        this.ifSuccessed = ifSuccessed;
    }
}
