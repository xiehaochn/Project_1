package Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/14.
 */
public class PayData implements Serializable{
    private String count;
    private String time;

    public String getCount() {
        return count;
    }

    public String getTime() {
        return time;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
