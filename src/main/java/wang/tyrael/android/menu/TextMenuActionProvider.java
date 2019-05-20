package wang.tyrael.android.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.ActionProvider;

import wang.tyrael.library.R;
import wang.tyrael.log.LogHelper;

/**
 * Created by 王超 on 2017/10/18.
 */

public class TextMenuActionProvider extends ActionProvider {
    private static final java.lang.String TAG = "TextMenuActionProvider";
    private Context context;

    private TextView mTvBadge, tvProvider;
    private View root;

    private View.OnClickListener clickListener;
    private int countRed;

    public TextMenuActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        throw new UnsupportedOperationException("onCreateActionView");
    }

    @Override
    public View onCreateActionView(MenuItem forItem) {
        LogHelper.d(TAG, "onCreateActionView");
//        int size = context.getResources().getDimensionPixelSize(
//                android.support.design.R.dimen.abc_action_bar_default_height_material);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, size);
        root = LayoutInflater.from(context)
                .inflate(R.layout.menu_provider_text, null, false);

//        root.setLayoutParams(layoutParams);
        tvProvider = root.findViewById(R.id.tv_provider);
        mTvBadge = root.findViewById(R.id.tv_badge);

        setCount(countRed);
        tvProvider.setText(forItem.getTitle());
//        root.setId(forItem.getItemId());
        root.setOnClickListener(clickListener);

        return root;
    }

    // 设置显示的数字。
    public void setCount(int i) {
        countRed = i;
        if(mTvBadge == null){
            return;
        }
        if(i == 0){
            mTvBadge.setVisibility(View.GONE);
        }else{
            mTvBadge.setVisibility(View.VISIBLE);
//            mTvBadge.setText(Integer.toString(i));
        }

    }

    public void setOnClickListener(View.OnClickListener clickListener){
        //onCreateActionView 的调用时机非常晚
        this.clickListener = clickListener;
        if(root != null){
            root.setOnClickListener(clickListener);
        }

    }

}
