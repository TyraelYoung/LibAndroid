package wang.tyrael.presenter;

/**
 * Created by 王超 on 2017/9/1.
 */

public class Event {
    public String type;
    public Object data;

    public Event() {
    }

    public Event(String type) {
        this.type = type;
    }

    public Event(String type, Object data) {
        this.type = type;
        this.data = data;
    }
}
