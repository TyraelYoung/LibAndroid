package wang.tyrael;

/**
 * Created by wangchao on 2015/9/15.
 */
public interface DataListener<T> {
    void onFailure(int code, String msg);
    void onSucess(boolean isLocal, T result);

    int ERROR_CODE_NET = -1;
    int ERROR_CODE_DATA = -2;
}
