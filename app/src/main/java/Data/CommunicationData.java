package Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/14.
 */
public class CommunicationData implements Serializable {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minite;

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMinite() {
        return minite;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinite(String minite) {
        this.minite = minite;
    }
}
