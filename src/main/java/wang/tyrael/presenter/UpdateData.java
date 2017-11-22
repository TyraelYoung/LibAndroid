package wang.tyrael.presenter;

/**
 * Created by 王超 on 2017/9/1.
 */

public class UpdateData {
    public String type;
    public Object data;

    public UpdateData() {
    }

    public UpdateData(String type) {
        this.type = type;
    }

    public UpdateData(String type, Object data) {
        this.type = type;
        this.data = data;
    }
}
