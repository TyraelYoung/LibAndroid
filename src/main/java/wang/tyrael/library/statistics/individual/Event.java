package wang.tyrael.statistics.individual;

import java.sql.Timestamp;

/**
 * Created by wangchao on 2016/6/7.
 */
public class Event implements Logable{
    public String type;
    public String name;
    public String detail;
    public Timestamp time = new Timestamp(System.currentTimeMillis());

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(",");
        sb.append(name);
        sb.append(",");
        sb.append(detail);
        sb.append(",");
        sb.append(time.getTime());
        return sb.toString();
    }

}
