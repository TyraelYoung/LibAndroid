package wang.tyrael.image;

import android.content.Context;
import android.widget.ImageView;

import wang.tyrael.android.application.ApplicationHolder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import wang.tyrael.library.http.okhttpapi.ClientApi;

/**
 * 图像加载适配器，底层可以换用各种库，不影响业务变化
 * <p>
 * Created by 王超 on 2017/10/12.
 */

public class ImageLoader {
    private static Context context = ApplicationHolder.getApplication();

    static {
        OkHttpClient client = ClientApi.getClientNoConfig();
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    public static void display(ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }
}
